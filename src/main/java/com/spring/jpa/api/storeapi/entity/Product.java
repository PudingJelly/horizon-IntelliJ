package com.spring.jpa.api.storeapi.entity;

import lombok.*;

import javax.persistence.*;

@Setter @Getter @ToString(exclude = "email")
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name = "user_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String productName; // 물품 이름

    @Column(name = "product_count")
    private int count; // 물품 개수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_email")
    private Basket email; // 장바구니와 조인할 컬럼

}
