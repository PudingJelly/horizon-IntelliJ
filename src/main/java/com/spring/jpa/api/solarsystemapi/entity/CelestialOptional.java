package com.spring.jpa.api.solarsystemapi.entity;

import lombok.*;

import javax.persistence.*;

//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
@Builder
@Entity
//@Table(name = "celestial_optional")
public class CelestialOptional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int celestialNumber; //천체 번호
//
//    @Column
//    private int revolution; //행성 공전 주기
//
//    @Column
//    private int orbitRadius; //행성 궤도 반지름
//
//    @Column
//    private int satellites; //행성의 위성 수
//
//    @Column
//    private String satellite1; //대표 위성1
//
//    @Column
//    private String satellite2; //대표 위성2
}
