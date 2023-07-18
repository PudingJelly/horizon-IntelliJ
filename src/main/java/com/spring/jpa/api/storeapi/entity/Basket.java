package com.spring.jpa.api.storeapi.entity;

import lombok.*;

import javax.persistence.*;

@Getter @Setter @ToString //(exclude = "team")
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder @Entity
@Table(name = "basket")
public class Basket {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long pId; //장바구니 번호 - 랜더링 순서

    @Column(name = "basket_id")
    private String productName; //장바구니에 담긴물품
    private int count; // 물품이 담긴 숫자




}
