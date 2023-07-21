package com.spring.jpa.api.storeapi.repository;

import com.spring.jpa.api.storeapi.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, String> {
}
