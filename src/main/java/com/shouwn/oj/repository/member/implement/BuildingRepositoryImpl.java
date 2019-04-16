package com.shouwn.oj.repository.member.implement;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shouwn.oj.repository.member.BuildingRepositoryCustom;

public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public BuildingRepositoryImpl(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

}
