package com.spring.jpa.api.storeapi.dto.response;

import com.spring.jpa.api.storeapi.entity.ProductDetail;
import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductDetailResponseDTO {

    private Long id;
    private String name; // 물품 이름
    private String content; // 물품 설명
    private int price;

    public ProductDetailResponseDTO(ProductDetail products) {
        this.id = products.getId();
        this.name = products.getName();
        this.content = products.getContent();
        this.price = products.getPrice();
    }

}
