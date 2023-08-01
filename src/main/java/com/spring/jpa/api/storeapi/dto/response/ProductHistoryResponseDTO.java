package com.spring.jpa.api.storeapi.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.jpa.api.storeapi.entity.Product;
import com.spring.jpa.api.storeapi.entity.ProductHistory;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductHistoryResponseDTO {

    private Long id;
    private String name; // 물품 이름
    private int count; // 물품 개수
    private int price;
    private String address1;
    private String address2;

    public ProductHistoryResponseDTO(ProductHistory product) {
        this.id = product.getId();
        this.name = product.getName();
        this.count = product.getCount();
        this.price = product.getPrice();
        this.address1 = product.getAddress1();
        this.address2 = product.getAddress2();
    }

}
