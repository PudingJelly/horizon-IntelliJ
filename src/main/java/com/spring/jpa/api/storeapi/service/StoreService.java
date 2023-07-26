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

    public ProductsListResponseDTO create(
            final ProductRequestDTO requestDTO,
            final TokenUserInfo userInfo
    )
            throws RuntimeException, IllegalStateException {

        Basket foundUser = getBasket(userInfo.getEmail());

        Product product = requestDTO.toEntity(foundUser);

        productRepository.save(product);
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

    public ProductsListResponseDTO update(ProductModifyRequestDTO requestDTO, String email) throws RuntimeException{

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
