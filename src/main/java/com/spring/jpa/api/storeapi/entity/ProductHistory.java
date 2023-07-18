package com.spring.jpa.api.storeapi.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter @Setter @ToString //(exclude = "members")
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder @Entity
@Table(name = "product_history")
public class ProductHistory {
    //구매내역

}
