package com.spring.jpa.api.storeapi.dto.request;

import com.spring.jpa.api.storeapi.entity.Basket;
import com.spring.jpa.api.userapi.entity.User;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
public class BasketRequestDTO {


    @NotBlank
    private String email;

    @NotBlank
    private String name;

    public Basket toEntity() {
        return Basket.builder()
                .email(this.email)
                .name(this.name)
                .build();
    }

}
