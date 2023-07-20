package com.spring.jpa.api.storeapi.api;

import com.spring.jpa.api.storeapi.dto.request.ProductRequestDTO;
import com.spring.jpa.api.storeapi.dto.response.ProductsListResponseDTO;
import com.spring.jpa.api.storeapi.entity.Basket;
import com.spring.jpa.api.storeapi.entity.Product;
import com.spring.jpa.api.storeapi.service.StoreService;
import com.spring.jpa.auth.TokenUserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/{email}")
    public ResponseEntity<Basket> getUserProducts(@PathVariable("email") String email) {
        Basket basket = storeService.getBasket(email);

        return ResponseEntity.ok(basket);
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

    @PostMapping
    public ResponseEntity<?> createTodo(
            @AuthenticationPrincipal TokenUserInfo userInfo,
            @Validated @RequestBody ProductRequestDTO requestDTO,
            BindingResult result
    ) {
        if(result.hasErrors()) {
            log.warn("DTO 검증 에러 발생: {}", result.getFieldError());
            return ResponseEntity
                    .badRequest()
                    .body(result.getFieldError());
        }

        try {
            ProductsListResponseDTO responseDTO = storeService.create(requestDTO, userInfo);
            return ResponseEntity
                    .ok()
                    .body(responseDTO);
        } catch (IllegalStateException e) {
            // 권한 때문에 발생한 예외.
            log.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .internalServerError()
                    .body(ProductsListResponseDTO.builder().error(e.getMessage()));
        }
    }

}
