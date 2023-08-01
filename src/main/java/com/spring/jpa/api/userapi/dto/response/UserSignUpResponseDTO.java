package com.spring.jpa.api.userapi.dto.response;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.jpa.api.userapi.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter
@ToString @EqualsAndHashCode(of = "email")
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserSignUpResponseDTO {

    private String email;

    private String userName;

    private String postCode;

    private String address1;

    private String address2;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime joinDate;

    public UserSignUpResponseDTO(User user) {
        this.email = user.getEmail();
        this.userName = user.getUserName();
        this.postCode = user.getPostCode();
        this.address1 = user.getAddress1();
        this.address2 = user.getAddress2();
        this.joinDate = user.getJoinDate();
    }
}