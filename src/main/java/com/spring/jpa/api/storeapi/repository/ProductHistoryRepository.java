package com.spring.jpa.api.storeapi.repository;

import com.spring.jpa.api.storeapi.entity.Product;
import com.spring.jpa.api.storeapi.entity.ProductHistory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@SpringBootApplication
public interface ProductHistoryRepository extends JpaRepository<ProductHistory, String> {

//    @Query("INSERT INTO ")
//    static void insertProductHistory(ProductHistory productName, ProductHistory inventoryCount) {
//
//    }
}
