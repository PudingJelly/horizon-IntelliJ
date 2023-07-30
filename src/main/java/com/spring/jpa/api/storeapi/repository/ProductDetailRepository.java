package com.spring.jpa.api.storeapi.repository;

import com.spring.jpa.api.storeapi.entity.Basket;
import com.spring.jpa.api.storeapi.entity.Product;
import com.spring.jpa.api.storeapi.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, String> {

    @Query("SELECT d FROM ProductDetail d")
    List<ProductDetail> findList();

    @Query("SELECT d FROM ProductDetail d WHERE d.name = :name")
    ProductDetail findByName(String name);

}
