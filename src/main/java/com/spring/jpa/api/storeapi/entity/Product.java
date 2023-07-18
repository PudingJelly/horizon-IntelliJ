package com.spring.jpa.api.storeapi.entity;

import lombok.*;

import javax.persistence.*;

@Getter @Setter @ToString //(exclude = "team")
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder @Entity
@Table(name = "product")
public class Product {

    //고유번호, 제품이름, 재고갯수, 제품설명

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basket")
    private Basket basket;
}
