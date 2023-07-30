package com.spring.jpa.api.storeapi.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter @ToString(exclude = "name")
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name = "product_detail")
public class ProductDetail implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_content")
    private String content; // 물품 설명

    @Column(name = "product_price")
    private int price; //물품 가격

    // 생성자 추가

    public ProductDetail(String name) {
        this.name = name;
    }

    public ProductDetail(String productName, String productContent, int price) {
        this.name = productName;
        this.content = productContent;
        this.price = price;
    }

}
