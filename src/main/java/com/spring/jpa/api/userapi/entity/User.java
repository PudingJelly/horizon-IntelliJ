package com.spring.jpa.api.userapi.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter @Setter
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

    @NotNull
    @Column(nullable = false)
    private int postCode;

    @NotNull
    @Column(nullable = false)
    private String address1;

    @NotNull
    @Column(nullable = false)
    private String address2;

    @CreationTimestamp
    private LocalDateTime joinDate;

}