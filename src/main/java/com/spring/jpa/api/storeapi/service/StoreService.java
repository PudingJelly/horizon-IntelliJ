package com.spring.jpa.api.storeapi.service;

import com.spring.jpa.api.storeapi.entity.Basket;
import com.spring.jpa.api.storeapi.entity.Product;
import com.spring.jpa.api.storeapi.entity.ProductHistory;
import com.spring.jpa.api.storeapi.repository.BasketRepository;
import com.spring.jpa.api.storeapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class StoreService {

    private final BasketRepository basketRepository;
    private final ProductRepository ProductRepository;

    public Basket getBasket(String email) {
        return basketRepository.findUser(email)
                .orElseThrow(() -> new NoSuchElementException("해당하는 바구니가 없습니다."));
    }

    public List<Product> getProducts(Basket email) {
        return ProductRepository.findProducts(email);
    }

    public void deleteProduct(Basket email, String productName) {
        ProductRepository.deleteProduct(email, productName);
    }

//    public void insertProductHistory(ProductHistory productName, ProductHistory inventoryCount) {
//        ProductHistoryRepository.insertProductHistory(productName, inventoryCount);
//    }

}
