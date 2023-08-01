package com.spring.jpa.api.storeapi.dto.request;

import com.spring.jpa.api.storeapi.entity.Basket;
import com.spring.jpa.api.storeapi.entity.Product;
import com.spring.jpa.api.storeapi.entity.ProductDetail;
import com.spring.jpa.api.storeapi.entity.ProductHistory;
import com.spring.jpa.auth.TokenUserInfo;
import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductHistoryRequestDTO {

    private String name; // 물품 이름
    private int count; // 물품 개수
    private int price;
    private String address1;
    private String address2;
    private Basket email; // 장바구니와 조인할 사용자 이메일

    public ProductHistory toEntity() {
        return ProductHistory.builder()
                .name(this.name)
                .count(this.count)
                .price(this.price)
                .address1(this.address1)
                .address2(this.address2)
                .build();
    }

    public ProductHistory toEntity(Basket email) {
        return ProductHistory.builder()
                .name(this.name)
                .count(this.count)
                .price(this.price)
                .address1(this.address1)
                .address2(this.address2)
                .email(email)
                .build();
    }

}
