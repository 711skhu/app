package com.shouwn.oj.model.page;

import com.shouwn.oj.model.enums.rental.ClassroomType;
import lombok.Builder;
import lombok.Data;

@Data
public class Lecture {

	private final String name;

	private final int people;

	private final ClassroomType type;

	@Builder
	public Lecture(String name, int people) {
		this.name = name;
		this.people = people;
		this.type = ClassroomType.valudOfClassroomName(name);
	}
}
