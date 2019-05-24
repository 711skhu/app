package com.shouwn.oj.controller.user;

import com.shouwn.oj.exception.InvalidParameterException;
import com.shouwn.oj.model.entity.member.Student;
import com.shouwn.oj.model.request.member.MemberLoginRequest;
import com.shouwn.oj.model.response.ApiDataBuilder;
import com.shouwn.oj.model.response.ApiResponse;
import com.shouwn.oj.model.response.CommonResponse;
import com.shouwn.oj.security.JwtProvider;
import com.shouwn.oj.service.user.StudentServiceForMobile;
import org.apache.commons.lang3.StringUtils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
public class StudentController {

	private final JwtProvider jwtProvider;

	private final StudentServiceForMobile studentServiceForMobile;

	public StudentController(JwtProvider jwtProvider, StudentServiceForMobile studentServiceForMobile) {
		this.jwtProvider = jwtProvider;
		this.studentServiceForMobile = studentServiceForMobile;
	}

	@PostMapping("login")
	public ApiResponse<?> login(@RequestBody MemberLoginRequest loginRequest) {
		Student student;

		if (StringUtils.isBlank(loginRequest.getUsername()) || StringUtils.isBlank(loginRequest.getPassword())) {
			throw new InvalidParameterException("아이디 혹은 비밀번호를 입력해주세요.");
		}

		student = studentServiceForMobile.login(loginRequest.getUsername(), loginRequest.getPassword());

		String jwt = jwtProvider.generateJwt(student.getId());

		return CommonResponse.builder()
				.status(HttpStatus.CREATED)
				.message("로그인 성공")
				.data(new ApiDataBuilder().addData("token", jwt).packaging())
				.build();
	}

}
