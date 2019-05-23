package com.shouwn.oj.controller.user;

import java.util.List;
import javax.servlet.http.HttpSession;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.shouwn.oj.exception.rent.RentException;
import com.shouwn.oj.exception.rent.RentListIsEmptyException;
import com.shouwn.oj.exception.user.LoginException;
import com.shouwn.oj.model.request.user.UserLoginRequest;
import com.shouwn.oj.model.response.ApiResponse;
import com.shouwn.oj.model.response.CommonResponse;
import com.shouwn.oj.model.response.rent.LectureRentInfo;
import com.shouwn.oj.service.user.UserRentListService;
import com.shouwn.oj.service.user.UserService;
import org.apache.commons.lang3.StringUtils;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
	private final UserService userService;
	private final UserRentListService userRentListService;

	public UserController(UserService userService, UserRentListService userRentListService) {
		this.userService = userService;
		this.userRentListService = userRentListService;
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("login")
	public ApiResponse<?> login(@RequestBody UserLoginRequest loginRequest, HttpSession session) {

		session.setAttribute("rentPage", null);
		HtmlPage mainPage;

		if (StringUtils.isBlank(loginRequest.getStudentNumber()) || StringUtils.isBlank(loginRequest.getPassword())) {
			return CommonResponse.builder()
					.status(HttpStatus.PRECONDITION_FAILED)
					.message("아이디 혹은 비밀번호를 입력해주세요.")
					.build();
		}

		try {
			mainPage = userService.login(loginRequest);
		} catch (LoginException e) {
			return CommonResponse.builder()
					.status(HttpStatus.FORBIDDEN)
					.message(e.getMessage())
					.build();
		}

		session.setAttribute("rentPage", mainPage);
		return CommonResponse.builder()
				.status(HttpStatus.CREATED)
				.message("로그인 성공")
				.build();
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("rentList")
	public ApiResponse<?> rentList(HttpSession session) {
		HtmlPage rentPage = (HtmlPage) session.getAttribute("rentPage");
		List<LectureRentInfo> rentList;

		try {
			rentList = userRentListService.rentList(rentPage);
		} catch (RentException e) {
			return CommonResponse.builder()
					.status(HttpStatus.FORBIDDEN)
					.message(e.getMessage())
					.build();
		} catch (RentListIsEmptyException e) {
			return CommonResponse.builder()
					.status(HttpStatus.NO_CONTENT)
					.message(e.getMessage())
					.build();
		}

		return CommonResponse.builder()
				.status(HttpStatus.OK)
				.message("사용자 대여 목록 조회 성공")
				.data(rentList)
				.build();
	}

}
