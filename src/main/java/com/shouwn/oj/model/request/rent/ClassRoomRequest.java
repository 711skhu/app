package com.shouwn.oj.model.request.rent;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClassRoomRequest {
	private String classRoomName; // 강의실 이름
}
