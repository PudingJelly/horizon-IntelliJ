package com.spring.jpa.api.storeapi.api;

import com.spring.jpa.api.storeapi.dto.request.ProductRequestDTO;
import com.spring.jpa.api.storeapi.dto.request.ProductDetailRequestDTO;
import com.spring.jpa.api.storeapi.dto.response.ProductsListResponseDTO;
import com.spring.jpa.api.storeapi.dto.response.ProductDetailResponseDTO;
import com.spring.jpa.api.storeapi.entity.Basket;
import com.spring.jpa.api.storeapi.entity.Product;
import com.spring.jpa.api.storeapi.service.StoreService;
import com.spring.jpa.auth.TokenUserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/shop")
//@CrossOrigin
@CrossOrigin(origins = "http://localhost:3000")
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public ResponseEntity<?> createProducts(
            @AuthenticationPrincipal TokenUserInfo userInfo,
            @RequestBody ProductRequestDTO requestDTO
    ) {
            ProductsListResponseDTO responseDTO = storeService.create(requestDTO, userInfo);
            return ResponseEntity
                    .ok()
                    .body(responseDTO);
    }

    @PostMapping("/product")
    public ResponseEntity<?> createProductDetail(
            @RequestBody ProductDetailRequestDTO dto
    ) {
        log.info("crateProduct " + dto.getProductContent());
            ProductDetailResponseDTO responseDTO = storeService.productCreate(dto);
            return ResponseEntity.ok()
                    .body(responseDTO);
    }

    @GetMapping("/products/{email}")
    public ResponseEntity<List<Product>> getProducts(@PathVariable("email") Basket email) {
        List<Product> products = storeService.getProducts(email);

        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/products/{email}")
    public ResponseEntity<?> deleteProduct(
            @AuthenticationPrincipal TokenUserInfo userInfo,
            @PathVariable("email") String email
    ) {

        ProductsListResponseDTO responseDTO = storeService.delete(email, userInfo.getEmail());
        return ResponseEntity.ok().body(responseDTO);
    }

}
