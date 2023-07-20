package com.spring.jpa.api.storeapi.repository;

import com.spring.jpa.api.storeapi.entity.Basket;
import com.spring.jpa.api.storeapi.entity.Product;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@SpringBootApplication
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT p FROM Product p WHERE p.email = :email")
    List<Product> findProducts(Basket email);

//    @Modifying
//    @Query("DELETE FROM Product p WHERE p.email = :email AND p.productName = :productName")
//    void deleteProduct(Basket email, String productName);
}
