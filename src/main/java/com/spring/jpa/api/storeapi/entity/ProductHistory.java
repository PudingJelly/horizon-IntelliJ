package com.spring.jpa.api.storeapi.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter @ToString(exclude = "email")
@EqualsAndHashCode
@Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name="shop_history")
public class ProductHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_count")
    private int count;

    @Column(name = "product_price")
    private int price;

    private String address1;
    private String address2;

    @CreationTimestamp
    private LocalDateTime buyDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_email")
    private Basket email; // 장바구니와 조인할 컬럼

}
