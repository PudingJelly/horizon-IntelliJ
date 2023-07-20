package com.spring.jpa.api.userapi.dto.request;

import com.spring.jpa.api.userapi.entity.User;
import lombok.*;

import javax.validation.constraints.NotBlank;

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

    public User toEntity() {
        return User.builder()
                .email(this.email)
                .password(this.password)
                .userName(this.userName)
                .build();
    }

}