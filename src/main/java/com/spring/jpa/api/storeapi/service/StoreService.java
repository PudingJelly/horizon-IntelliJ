package com.spring.jpa.api.storeapi.service;

import com.spring.jpa.api.storeapi.dto.request.BasketRequestDTO;
import com.spring.jpa.api.storeapi.dto.request.ProductRequestDTO;
import com.spring.jpa.api.storeapi.dto.request.ProductDetailRequestDTO;
import com.spring.jpa.api.storeapi.dto.response.BasketResponseDTO;
import com.spring.jpa.api.storeapi.dto.response.ProductResponseDTO;
import com.spring.jpa.api.storeapi.dto.response.ProductsListResponseDTO;
import com.spring.jpa.api.storeapi.dto.response.ProductDetailResponseDTO;
import com.spring.jpa.api.storeapi.entity.Basket;
import com.spring.jpa.api.storeapi.entity.Product;
import com.spring.jpa.api.storeapi.entity.ProductDetail;
import com.spring.jpa.api.storeapi.repository.BasketRepository;
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
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class StoreService {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final ProductDetailRepository productsRepository;

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

    public void createProducts() {}

    public ProductsListResponseDTO retrieve(String userEmail) {
        // 로그인 한 유저의 정보 데이터베이스에서 조회
        Basket email = getBasket(userEmail);

        List<Product> entityList = productRepository.findProducts(email);

        List<ProductResponseDTO> dtoList = entityList.stream()
                /*.map(todo -> new TodoDetailResponseDTO(todo))*/
                .map(ProductResponseDTO::new)
                .collect(Collectors.toList());

        return ProductsListResponseDTO.builder()
                .products(dtoList)
                .build();
    }

    public Basket getBasket(String email) {
        return basketRepository.findUser(email)
                .orElseThrow(() -> new NoSuchElementException("해당하는 바구니가 없습니다."));
    }

    public List<Product> getProducts(Basket email) {
        return productRepository.findProducts(email);
    }

    public ProductsListResponseDTO delete(final String email, String userEmail) {
        productRepository.deleteById(email);
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
        log.info("장바구니 추가 완료! 물품: {}", requestDTO.getProductName());
        return retrieve(userInfo.getEmail());
    }


    public ProductDetailResponseDTO productCreate(final ProductDetailRequestDTO dto) {
        ProductDetail products = dto.toEntity();

        ProductDetail saved = productsRepository.save(products);

        return new ProductDetailResponseDTO(saved);
    }
}
