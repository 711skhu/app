package com.shouwn.oj.service.user;

import java.util.Optional;

import com.shouwn.oj.exception.AuthenticationFailedException;
import com.shouwn.oj.exception.NotFoundException;
import com.shouwn.oj.model.entity.member.Student;
import com.shouwn.oj.service.member.StudentService;

import org.springframework.stereotype.Service;

@Service
public class StudentServiceForMobile {

	private final StudentService studentService;

	public StudentServiceForMobile(StudentService studentService) {
		this.studentService = studentService;
	}

	public Student login(String username, String rawPassword) {
		Optional<Student> student = studentService.findByUsername(username);

		if (!student.isPresent()) {
			throw new NotFoundException(username + "에 해당하는 유저가 없습니다.");
		}

		if (!studentService.isCorrectPassword(student.get(), rawPassword)) {
			throw new AuthenticationFailedException("패스워드가 맞지 않습니다.");
		}

		return student.get();
	}

	public Student findById(Long id) {
		Optional<Student> student = studentService.findById(id);

		if (!student.isPresent()) {
			throw new NotFoundException(id + "에 해당하는 유저가 없습니다.");
		}

		return student.get();
	}
}
