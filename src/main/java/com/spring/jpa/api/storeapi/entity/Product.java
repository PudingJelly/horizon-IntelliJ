package com.spring.jpa.api.storeapi.entity;

import lombok.*;

import javax.persistence.*;

@Setter @Getter @ToString(exclude = "email")
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name = "shop_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id; // 물품 고유 아이디 (숫자)

    private String productName; // 물품 이름
    private String productContent; // 물품 설명
    private int inventoryCount; // 물품 개수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_email")
    private Basket email; // 장바구니와 조인할 컬럼

}
