package com.shouwn.oj.repository.user.implement;

        import com.querydsl.jpa.impl.JPAQueryFactory;
        import com.shouwn.oj.repository.user.UserRepositoryCustom;

public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

}
