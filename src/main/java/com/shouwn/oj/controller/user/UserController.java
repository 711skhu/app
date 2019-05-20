package com.shouwn.oj.controller.user;

import javax.servlet.http.HttpSession;

import com.shouwn.oj.model.request.user.UserLoginRequest;
import com.shouwn.oj.model.response.ApiResponse;
import com.shouwn.oj.model.response.CommonResponse;
import com.shouwn.oj.service.user.UserService;
import org.apache.commons.lang3.StringUtils;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("login")
	public ApiResponse<?> login(@RequestBody UserLoginRequest loginRequest, HttpSession session) {

		if (StringUtils.isBlank(loginRequest.getStudentNumber()) || StringUtils.isBlank(loginRequest.getPassword())) {
			return CommonResponse.builder()
					.status(HttpStatus.PRECONDITION_FAILED)
					.message("아이디 혹은 비밀번호를 입력해주세요.")
					.build();
		}

		if (userService.login(loginRequest, session)) {
			return CommonResponse.builder()
					.status(HttpStatus.CREATED)
					.message("로그인 성공")
					.build();
		} else {
			return CommonResponse.builder()
					.status(HttpStatus.FORBIDDEN)
					.message("로그인 실패. 계정 정보 확인 바랍니다.")
					.build();
		}
	}
}
