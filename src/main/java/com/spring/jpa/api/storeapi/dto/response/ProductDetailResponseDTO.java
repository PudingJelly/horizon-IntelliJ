package com.spring.jpa.api.storeapi.dto.response;

import com.spring.jpa.api.storeapi.entity.ProductDetail;
import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductDetailResponseDTO {

    private Long id;
    private String productName; // 물품 이름
    private String productContent; // 물품 설명
    private int price;

    public ProductDetailResponseDTO(ProductDetail products) {
        this.id = products.getId();
        this.productName = products.getProductName();
        this.productContent = products.getProductContent();
        this.price = products.getPrice();
    }

}
