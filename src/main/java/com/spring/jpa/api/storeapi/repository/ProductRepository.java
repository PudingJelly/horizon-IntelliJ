package com.spring.jpa.api.storeapi.repository;

import com.spring.jpa.api.storeapi.entity.Basket;
import com.spring.jpa.api.storeapi.entity.Product;
import com.spring.jpa.api.storeapi.entity.ProductDetail;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@SpringBootApplication
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p JOIN p.name d WHERE p.email = :email")
    List<Product> findProducts(Basket email);

}
