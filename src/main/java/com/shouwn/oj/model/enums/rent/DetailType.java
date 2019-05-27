package com.shouwn.oj.model.enums.rent;

import lombok.Getter;

@Getter
public enum DetailType {

	PROJECTOR("빔프로젝터"),
	COMPUTER("컴퓨터"),
	NULL("없음"),
	SPECIAL("특수강의실"),
	SMALL("소형강의실"),
	MIDDLE("중형강의실"),
	BIG("대형강의실");

	private String detail;

	DetailType(String detail) {
		this.detail = detail;
	}

}
