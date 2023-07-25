package com.spring.jpa.api.storeapi.dto.request;
import com.spring.jpa.api.storeapi.entity.ProductDetail;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Setter @Getter
@ToString @EqualsAndHashCode(of = "name")
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductDetailRequestDTO {

    @NotBlank
    @Column(name = "product_name")
    private String name; // 물품 이름

    @NotBlank
    @Column(name = "product_content")
    private String content; // 물품 설명

    @NotBlank
    @Column(name = "product_price")
    private int price;

    public ProductDetail toEntity() {
        return ProductDetail.builder()
                .name(this.name)
                .content(this.content)
                .price(this.price)
                .build();
    }

}
