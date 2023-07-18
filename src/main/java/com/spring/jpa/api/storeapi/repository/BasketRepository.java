package com.spring.jpa.api.storeapi.repository;

import com.spring.jpa.api.storeapi.entity.Basket;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@SpringBootApplication
public interface BasketRepository extends JpaRepository<Basket, String> {
    @Query("SELECT b FROM Basket b WHERE b.email = :email")
    Optional<Basket> findUser(String email);
}
