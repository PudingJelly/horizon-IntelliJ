package com.spring.jpa.api.userapi.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@ToString @EqualsAndHashCode(of = "email")
@NoArgsConstructor @AllArgsConstructor
@Builder @Entity
@Table(name = "horizon_user")
public class User {

    @Id
    @Column(name = "user_email")
    private String email;

    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    private String userName;

    @CreationTimestamp
    private LocalDateTime joinDate;

}