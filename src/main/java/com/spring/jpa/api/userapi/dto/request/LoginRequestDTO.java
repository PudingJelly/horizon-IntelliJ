package com.spring.jpa.api.userapi.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {


    @NotBlank
    private String email;

    @NotBlank
    private String password;

}