package com.spring.jpa.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// QueryDsl 문법을 사용하기 위한 필수 객체인 JPAQueryFactory의 빈 등록을 위한 클래스.
// 나중에 여러개의 repository에서 QueryDsl을 사용하기 위한 빈 등록.
@Configuration
public class QuerydslConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

}
