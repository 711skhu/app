package com.shouwn.oj.controller.user;

import com.shouwn.oj.exception.member.PasswordIncorrectException;
import com.shouwn.oj.exception.member.UsernameNotExistException;
import com.shouwn.oj.model.entity.member.Student;
import com.shouwn.oj.model.request.member.MemberLoginRequest;
import com.shouwn.oj.model.response.ApiDataBuilder;
import com.shouwn.oj.model.response.ApiResponse;
import com.shouwn.oj.model.response.CommonResponse;
import com.shouwn.oj.security.JwtProvider;
import com.shouwn.oj.service.user.StudentServiceForMobileLogin;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("student")
public class StudentController {

	private final JwtProvider jwtProvider;

	private final StudentServiceForMobileLogin studentServiceForMobileLogin;

	public StudentController(JwtProvider jwtProvider, StudentServiceForMobileLogin studentServiceForMobileLogin) {
		this.jwtProvider = jwtProvider;
		this.studentServiceForMobileLogin = studentServiceForMobileLogin;
	}

	@PostMapping("login")
	public ApiResponse<?> login(@RequestBody MemberLoginRequest loginRequest) {
		Student student;

		try {
			student = studentServiceForMobileLogin.login(loginRequest.getUsername(), loginRequest.getPassword());
		} catch (UsernameNotExistException e) {
			return CommonResponse.builder()
					.status(HttpStatus.PRECONDITION_FAILED)
					.message(loginRequest.getUsername() + " 에 해당하는 사용자 아이디가 없습니다.")
					.build();
		} catch (PasswordIncorrectException e) {
			return CommonResponse.builder()
					.status(HttpStatus.FORBIDDEN)
					.message("비밀번호가 다릅니다.")
					.build();
		}

		String jwt = jwtProvider.generateJwt(student.getId());

		return CommonResponse.builder()
				.status(HttpStatus.CREATED)
				.message("로그인 성공")
				.data(new ApiDataBuilder().addData("token", jwt).packaging())
				.build();
	}

}
