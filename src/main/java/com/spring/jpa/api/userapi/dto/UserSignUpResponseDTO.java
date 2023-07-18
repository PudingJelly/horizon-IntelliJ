package com.spring.jpa.api.userapi.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.jpa.api.userapi.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Setter @Getter
@ToString //@EqualsAndHashCode(of = "email")
//@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserSignUpResponseDTO {

//    private String email;
//
//    private String userName;
//
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime joinDate;
//
//    public UserSignUpResponseDTO(User user) {
//        this.email = user.getEmail();
//        this.userName = user.getUserName();
//        this.joinDate = user.getJoinDate();
//    }
}