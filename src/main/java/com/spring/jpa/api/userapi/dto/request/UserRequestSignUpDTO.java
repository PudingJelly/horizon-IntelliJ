package com.spring.jpa.api.userapi.dto.request;

import com.spring.jpa.api.userapi.entity.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter @Getter
@ToString @EqualsAndHashCode(of = "email")
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserRequestSignUpDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String userName;

    @NotNull
    private String postCode;

    @NotBlank
    private String address1;

    @NotBlank
    private String address2;

    public User toEntity() {
        return User.builder()
                .email(this.email)
                .password(this.password)
                .userName(this.userName)
                .postCode(this.postCode)
                .address1(this.address1)
                .address2(this.address2)
                .build();
    }

}