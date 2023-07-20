package com.spring.jpa.auth;

import lombok.*;

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TokenUserInfo {

    private String email;
    private String userName;
}