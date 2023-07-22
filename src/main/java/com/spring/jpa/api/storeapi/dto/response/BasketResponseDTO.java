package com.spring.jpa.api.storeapi.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.jpa.api.storeapi.entity.Basket;
import com.spring.jpa.api.userapi.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketResponseDTO {

    private String email;

    private String userName;

    public BasketResponseDTO(Basket basket) {
        this.email = basket.getEmail();
        this.userName = basket.getName();
    }

}
