package com.spring.jpa.api.storeapi.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProductModifyRequestDTO {
    @NotBlank
    private long id;
    private int count;
}
