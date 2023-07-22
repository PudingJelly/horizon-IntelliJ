package com.spring.jpa.api.storeapi.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;

@Getter @Setter @ToString(exclude = "productName")
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity
@Table(name = "product_detail")
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String productName;

    private String productContent; // 물품 설명

    // 생성자 추가
    public ProductDetail(String productName, String productContent) {
        this.productName = productName;
        this.productContent = productContent;
    }

}
