package com.spring.jpa.api.storeapi.api;

import com.spring.jpa.api.storeapi.dto.request.ProductHistoryRequestDTO;
import com.spring.jpa.api.storeapi.dto.request.ProductRequestDTO;
import com.spring.jpa.api.storeapi.dto.request.ProductDetailRequestDTO;
import com.spring.jpa.api.storeapi.dto.response.ProductHistoryListResponseDTO;
import com.spring.jpa.api.storeapi.dto.response.ProductsListResponseDTO;
import com.spring.jpa.api.storeapi.dto.response.ProductDetailResponseDTO;
import com.spring.jpa.api.storeapi.service.StoreService;
import com.spring.jpa.auth.TokenUserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/shop")
//@CrossOrigin
@CrossOrigin(origins = "http://localhost:3000")
public class StoreController {

    private final StoreService storeService;

    // 장바구니에 물품추가
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

    // 히스토리에 물품추가
    @PostMapping("/history")
    public ResponseEntity<?> createHistoryProducts(
            @AuthenticationPrincipal TokenUserInfo userInfo,
            @RequestBody ProductHistoryRequestDTO requestDTO
    ) {
        ProductHistoryListResponseDTO responseDTO = storeService.historyCreate(requestDTO, userInfo);
        return ResponseEntity
                .ok()
                .body(responseDTO);
    }

    //물품 추가
    @PostMapping("/product")
    public ResponseEntity<?> createProductDetail(
            @RequestBody ProductDetailRequestDTO dto
    ) {
        log.info("crateProduct " + dto.getContent());
            ProductDetailResponseDTO responseDTO = storeService.productCreate(dto);
            return ResponseEntity.ok()
                    .body(responseDTO);
    }

    //회원 장바구니 물품삭제
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(
            @AuthenticationPrincipal TokenUserInfo userInfo,
            @PathVariable("id") Long productId
    ) {

        ProductsListResponseDTO responseDTO = storeService.delete(productId, userInfo.getEmail());
        return ResponseEntity.ok().body(responseDTO);
    }

    //회원 장바구니 내용검색
    @GetMapping("/products")
    public ResponseEntity<?> getProducts(
            @AuthenticationPrincipal TokenUserInfo userInfo
    ){
        ProductsListResponseDTO responseDTO = storeService.retrieve(userInfo.getEmail());
        return ResponseEntity.ok().body(responseDTO);
    }




}
