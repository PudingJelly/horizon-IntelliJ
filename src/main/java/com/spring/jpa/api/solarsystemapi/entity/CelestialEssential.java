package com.spring.jpa.api.solarsystemapi.entity;

import lombok.*;

import javax.persistence.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor @AllArgsConstructor
@Builder
@Entity @Table(name = "celestial_essential")
public class CelestialEssential {

    //태양, 달을 포함하는 천체 entity
    //태양 - 
    //달 - 
    //이외의 행성 not null

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int celestialNumber; //천체 번호

    @Column(unique = true, nullable = false)
    private String celestialName; //천체의 이름(태양, 달 등)

    @Column(nullable = false)
    private boolean earthLike; //지구형 행성(수금지화), 목성형 행성(목토천해)

    @Column(nullable = false)
    private double diameter; //행성 평균 지름(Km)

    @Column(nullable = false)
    private double surface; //행성 표면적(km^2)

    @Column(nullable = false)
    private double mass; //행성 질량

    @Column(nullable = false)
    private double rotation; //행성 자전 주기
}
