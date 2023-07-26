package com.spring.jpa.api.storeapi.dto.response;

import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductsListResponseDTO {

    private String error; //에러 발생 시 에러 메세지를 담을 필드
    private List<ProductResponseDTO> products;

    public boolean containsDuplicateNames() {
        Set<String> nameSet = new HashSet<>();
        for (ProductResponseDTO product : products) {
            if(!nameSet.add(product.getName())) {
                return true;
            }
        }
        return false;
    }

}
