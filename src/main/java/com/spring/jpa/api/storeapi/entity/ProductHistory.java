package com.spring.jpa.api.storeapi.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter @ToString
@EqualsAndHashCode
@Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="shop_history")
public class ProductHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basket_id")
    private Long id;

    private String productName;
    private int inventoryCount;

    @CreationTimestamp
    private LocalDateTime buyDate;

}
