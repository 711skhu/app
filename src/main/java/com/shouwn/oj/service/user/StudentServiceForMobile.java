package com.shouwn.oj.service.user;

import com.shouwn.oj.exception.member.MemberException;
import com.shouwn.oj.exception.member.PasswordIncorrectException;
import com.shouwn.oj.model.entity.member.Student;
import com.shouwn.oj.service.member.StudentService;

import org.springframework.stereotype.Service;

@Service
public class StudentServiceForMobile {

	private final StudentService studentService;

	public StudentServiceForMobile(StudentService studentService) {
		this.studentService = studentService;
	}

	public Student login(String username, String rawPassword) throws MemberException {
		Student student = studentService.findByUsername(username);

		if (!studentService.isCorrectPassword(student, rawPassword)) {
			throw new PasswordIncorrectException("패스워드가 맞지 않습니다.");
		}

		return student;
	}

	public Student findById(Long id) {
		return studentService.findById(id);
	}
}
