package com.spring.jpa.api.storeapi.repository;

import com.spring.jpa.api.storeapi.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, String> {
    @Query("SELECT d FROM ProductDetail d")
    List<ProductDetail> findList();
}
