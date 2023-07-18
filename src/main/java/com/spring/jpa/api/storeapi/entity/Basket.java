package com.spring.jpa.api.storeapi.entity;

import lombok.*;

import javax.persistence.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "shop_basket")
public class Basket {

    @Id
    @Column(name = "user_email")
    private String email;

    @Column(name ="user_name")
    private String name;


}
