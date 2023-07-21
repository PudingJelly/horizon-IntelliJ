//package com.spring.jpa.api.storeapi.repository;
//
//
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import com.spring.jpa.api.storeapi.entity.Basket;
//import com.spring.jpa.api.storeapi.entity.Product;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//
//import java.util.List;
//import java.util.Optional;
//
//@SpringBootTest
//@Transactional
//@Rollback(false)
//class ProductRepositoryTest {
//
//    @Autowired
//    ProductRepository shopproductRepository;
//
//    @Autowired
//    BasketRepository basketRepository;
//
//    @Autowired
//    EntityManager em;
//
//    JPAQueryFactory factory;
//
//    @BeforeEach
//    void settingObject() {
//        factory = new JPAQueryFactory(em);
//    }
//
//        @BeforeEach
//    void testInsertData() {
//
//        Basket userA = Basket.builder()
//                .email("userA@abc.com")
//                .name("userA")
//                .build();
//
//        Basket userB = Basket.builder()
//                .email("userB@abc.com")
//                .name("userB")
//                .build();
//
//        basketRepository.save(userA);
//        basketRepository.save(userB);
//
//        Product product1 = Product.builder()
//                .productName("product1")
//                .productContent("product1입니다.")
//                .inventoryCount(5)
//                .email(userA)
//                .build();
//        Product product2 = Product.builder()
//                .productName("product2")
//                .productContent("product2입니다.")
//                .inventoryCount(2)
//                .email(userA)
//                .build();
//        Product product3 = Product.builder()
//                .productName("product3")
//                .productContent("product3입니다.")
//                .inventoryCount(10)
//                .email(userB)
//                .build();
//        Product product4 = Product.builder()
//                .productName("product4")
//                .productContent("product4입니다.")
//                .inventoryCount(50)
//                .email(userB)
//                .build();
//        Product product5 = Product.builder()
//                .productName("product5")
//                .productContent("product5입니다.")
//                .inventoryCount(5)
//                .email(userA)
//                .build();
//        Product product6 = Product.builder()
//                .productName("product6")
//                .productContent("product6입니다.")
//                .inventoryCount(15)
//                .email(userA)
//                .build();
//        Product product7 = Product.builder()
//                .productName("product7")
//                .productContent("product7입니다.")
//                .inventoryCount(10)
//                .email(userB)
//                .build();
//        Product product8 = Product.builder()
//                .productName("product8")
//                .productContent("product8입니다.")
//                .inventoryCount(8)
//                .email(userB)
//                .build();
//
//        shopproductRepository.save(product1);
//        shopproductRepository.save(product2);
//        shopproductRepository.save(product3);
//        shopproductRepository.save(product4);
//        shopproductRepository.save(product5);
//        shopproductRepository.save(product6);
//        shopproductRepository.save(product7);
//        shopproductRepository.save(product8);
//
//
//    }
//
//    @Test
//    @DisplayName("testJPA")
//    void testJPA() {
//        List<Product> products = shopproductRepository.findAll();
//        products.forEach(System.out::println);
//    }
//
//    @Test
//    @DisplayName("getUser")
//    void getUser() {
//        Optional<Basket> byProducts = basketRepository.findUser("userB@abc.com");
//        System.out.println("getUSer = " + byProducts);
//    }
//
//
//
//}