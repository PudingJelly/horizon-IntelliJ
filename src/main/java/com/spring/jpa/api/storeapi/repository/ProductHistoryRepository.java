package com.spring.jpa.api.storeapi.repository;

import com.spring.jpa.api.storeapi.entity.Basket;
import com.spring.jpa.api.storeapi.entity.Product;
import com.spring.jpa.api.storeapi.entity.ProductHistory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@SpringBootApplication
public interface ProductHistoryRepository extends JpaRepository<ProductHistory, String> {

    @Query("SELECT p FROM ProductHistory p WHERE p.email = :email")
    List<ProductHistory> findProducts(Basket email);

}
