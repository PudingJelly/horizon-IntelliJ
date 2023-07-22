package com.spring.jpa.api.storeapi.dto.request;

import com.spring.jpa.api.storeapi.entity.Basket;
import com.spring.jpa.api.storeapi.entity.Product;
import com.spring.jpa.api.storeapi.entity.ProductDetail;
import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductRequestDTO {

    private String productName; // 물품 이름
    private int inventoryCount; // 물품 개수
    private Basket email; // 장바구니와 조인할 사용자 이메일

    public Product toEntity() {
        return Product.builder()
                .productName(this.productName)
                .inventoryCount(this.inventoryCount)
                .build();
    }

    public Product toEntity(Basket email) {
        return Product.builder()
                .productName(this.productName)
                .inventoryCount(this.inventoryCount)
                .email(email)
                .build();
    }

}
