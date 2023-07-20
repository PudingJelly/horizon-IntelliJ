package com.spring.jpa.api.storeapi.dto.response;

import com.spring.jpa.api.storeapi.entity.Product;
import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductResponseDTO {

    private Long id; // 물품 고유 아이디 (숫자)
    private String productName; // 물품 이름
    private String productContent; // 물품 설명
    private int inventoryCount; // 물품 개수

    public ProductResponseDTO(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.productContent = product.getProductContent();
        this.inventoryCount = product.getInventoryCount();
    }

}
