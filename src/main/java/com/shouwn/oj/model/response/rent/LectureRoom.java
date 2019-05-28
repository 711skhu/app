package com.shouwn.oj.model.response.rent;

import com.shouwn.oj.model.enums.rent.ClassroomType;
import com.shouwn.oj.model.enums.rent.DetailType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureRoom {

	private ClassroomType classroomType;
	private int people;

	@Builder
	public LectureRoom(ClassroomType classroomType, int people) {
		this.classroomType =classroomType;
		this.people = people;
	}
}
