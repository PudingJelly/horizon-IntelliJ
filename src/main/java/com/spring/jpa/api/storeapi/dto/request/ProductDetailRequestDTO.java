package com.spring.jpa.api.storeapi.dto.request;
import com.spring.jpa.api.storeapi.entity.ProductDetail;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Setter @Getter
@ToString @EqualsAndHashCode(of = "productName")
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductDetailRequestDTO {

    @NotBlank
    private String productName; // 물품 이름
    @NotBlank
    private String productContent; // 물품 설명

    @NotBlank
    private int price;

    public ProductDetail toEntity() {
        return ProductDetail.builder()
                .productName(this.productName)
                .productContent(this.productContent)
                .price(this.price)
                .build();
    }

}
