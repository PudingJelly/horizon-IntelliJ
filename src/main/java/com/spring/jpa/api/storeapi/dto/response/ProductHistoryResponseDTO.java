package com.spring.jpa.api.storeapi.dto.response;

import com.spring.jpa.api.storeapi.entity.Product;
import com.spring.jpa.api.storeapi.entity.ProductHistory;
import lombok.*;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductHistoryResponseDTO {

    private Long id;
    private String name; // 물품 이름
    private int count; // 물품 개수

    public ProductHistoryResponseDTO(ProductHistory product) {
        this.id = product.getId();
        this.name = product.getName();
        this.count = product.getCount();
    }

}
