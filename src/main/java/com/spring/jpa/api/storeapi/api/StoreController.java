package com.spring.jpa.api.storeapi.api;

import com.spring.jpa.api.storeapi.entity.Basket;
import com.spring.jpa.api.storeapi.entity.Product;
import com.spring.jpa.api.storeapi.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/shop")
//@CrossOrigin
//@CrossOrigin(origins = "http://localhost:3000")
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

    @DeleteMapping("/products/{email}/{productName}")
    public void deleteProduct(@PathVariable("email") Basket email,@PathVariable("productName") String productName) {
        storeService.deleteProduct(email, productName);
    }

}
