package com.spring.jpa.api.storeapi.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.jpa.api.userapi.entity.User;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository{

//    private final JPAQueryFactory queryFactory;
//
//    @Override
//    public List<User> findByName(String name) {
//        return queryFactory
//                .selectFrom(user)
//                .where(member.userName.eq(name))
//                .fetch();
//    }
//    //////////////////////////////////////////////////
//
//    // where절에 Boolean Expression을 리턴하는 매서드를 사용합니다.
//    // nameEq, ageEq에서는 값이 없다면Null리턴, 있다면 값을 반환
//    // where절에서는 null값인 경우 조건을 건너뛰게 됨
//    public List<Member> findUser(String nameParam, Integer ageParam){
//        return queryFactory.selectFrom(member)
//                .where(nameEq(nameParam), ageEq(ageParam))
//                .fetch();
//    }
//
//    private BooleanExpression ageEq(Integer ageParam) {
//        return ageParam != null ? member.age.eq(ageParam) : null;
//    }
//
//    private BooleanExpression nameEq(String nameParam) {
//        return nameParam != null ? member.userName.eq(nameParam) : null;
//    }
}
