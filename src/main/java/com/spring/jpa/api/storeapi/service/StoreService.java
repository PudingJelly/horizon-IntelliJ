package com.spring.jpa.api.storeapi.service;

import com.spring.jpa.api.storeapi.dto.request.*;
import com.spring.jpa.api.storeapi.dto.response.*;
import com.spring.jpa.api.storeapi.entity.Basket;
import com.spring.jpa.api.storeapi.entity.Product;
import com.spring.jpa.api.storeapi.entity.ProductDetail;
import com.spring.jpa.api.storeapi.entity.ProductHistory;
import com.spring.jpa.api.storeapi.repository.BasketRepository;
import com.spring.jpa.api.storeapi.repository.ProductHistoryRepository;
import com.spring.jpa.api.storeapi.repository.ProductRepository;
import com.spring.jpa.api.storeapi.repository.ProductDetailRepository;
import com.spring.jpa.api.userapi.dto.request.UserRequestSignUpDTO;
import com.spring.jpa.auth.TokenUserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class StoreService {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final ProductDetailRepository productsRepository;
    private final ProductHistoryRepository productHistoryRepository;
    private final ProductDetailRepository productDetailRepository;

    //장바구니 생성
    public void createBasket( @Validated UserRequestSignUpDTO dto) {
        String email = dto.getEmail();
        String name = dto.getUserName();

        BasketRequestDTO basketDTO = new BasketRequestDTO();
        basketDTO.setEmail(email);
        basketDTO.setName(name);

        Basket basket = basketDTO.toEntity();

        Basket saved = basketRepository.save(basket);

        new BasketResponseDTO(saved);

    }

    public ProductsListResponseDTO retrieve(String userEmail) {
        // 로그인 한 유저의 정보 데이터베이스에서 조회
        Basket email = getBasket(userEmail);

        List<Product> entityList = productRepository.findProducts(email);

        List<ProductResponseDTO> productsList = entityList.stream()
                .map(ProductResponseDTO::new)
                .collect(Collectors.toList());

        return ProductsListResponseDTO.builder()
                .products(productsList)
                .build();
    }

    public ProductHistoryListResponseDTO historyRetrieve(String userEmail) {
        // 로그인 한 유저의 정보 데이터베이스에서 조회
        Basket email = getBasket(userEmail);

        List<ProductHistory> entityList = productHistoryRepository.findProducts(email);

        List<ProductHistoryResponseDTO> productsList = entityList.stream()
                .map(ProductHistoryResponseDTO::new)
                .collect(Collectors.toList());

        return ProductHistoryListResponseDTO.builder()
                .products(productsList)
                .build();
    }

    public Basket getBasket(String email) {
        return basketRepository.findUser(email)
                .orElseThrow(() -> new NoSuchElementException("해당하는 바구니가 없습니다."));
    }

    public List<Product> getProducts(Basket email) {
        return productRepository.findProducts(email);
    }

    public ProductsListResponseDTO delete(final Long productId, String userEmail) {
        productRepository.deleteById(productId);
        return retrieve(userEmail);
    }

    public ProductsListResponseDTO create(ProductRequestDTO requestDTO, TokenUserInfo userInfo) {
        Basket foundUser = getBasket(userInfo.getEmail());

        String productName = requestDTO.getName().getName();

        // "물품2"라는 이름의 ProductDetail 엔티티를 데이터베이스에서 찾아옴
        ProductDetail productDetail = productDetailRepository.findByName(productName);

        // 만약 "물품2"라는 이름의 ProductDetail 엔티티가 존재하지 않는다면 예외 처리 또는 새로운 엔티티를 생성해야 함

        // 새로운 Product 엔티티 생성 및 설정
        Product newProduct = new Product();
        newProduct.setName(productDetail); // 찾은 ProductDetail 엔티티를 설정
        newProduct.setCount(requestDTO.getCount());
        newProduct.setEmail(foundUser);

        // 생성한 Product 엔티티를 저장
        productRepository.save(newProduct);

        // 수정된 장바구니 정보를 가져와서 반환
        return retrieve(userInfo.getEmail());
    }


    public ProductHistoryListResponseDTO historyCreate(
            ProductHistoryRequestDTO requestDTO,
            TokenUserInfo userInfo
    ) throws RuntimeException, IllegalStateException {

        Basket foundUser = getBasket(userInfo.getEmail());

        ProductHistory product = requestDTO.toEntity(foundUser);

        productHistoryRepository.save(product);
        return historyRetrieve(userInfo.getEmail());
    }


    public ProductDetailResponseDTO productCreate(final ProductDetailRequestDTO dto) {
        ProductDetail products = dto.toEntity();

        ProductDetail saved = productsRepository.save(products);

        return new ProductDetailResponseDTO(saved);
    }

    // 장바구니에서 물품 개수 변경
    public ProductsListResponseDTO update(final ProductModifyRequestDTO requestDTO, String email) throws RuntimeException {

        Optional<Product> targetEntity
                = productRepository.findById(requestDTO.getId());

        targetEntity.ifPresent(entity -> {
            entity.setCount(requestDTO.getCount());

            productRepository.save(entity);
        });

        return retrieve(email);

    }

    public List<ProductDetail> getList() {
        return productDetailRepository.findList();

    }
}
