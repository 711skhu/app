package com.shouwn.oj.controller.user;

import java.util.List;
import javax.servlet.http.HttpSession;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.shouwn.oj.exception.InvalidParameterException;
import com.shouwn.oj.model.request.user.UserLoginRequest;
import com.shouwn.oj.model.response.ApiResponse;
import com.shouwn.oj.model.response.CommonResponse;
import com.shouwn.oj.model.response.user.UserLectureRentalInfo;
import com.shouwn.oj.service.rental.ConnectToRentalPageService;
import com.shouwn.oj.service.user.UserRentalListService;
import com.shouwn.oj.service.user.UserService;
import org.apache.commons.lang3.StringUtils;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
	private final UserService userService;
	private final ConnectToRentalPageService connectToRentalPageService;
	private final UserRentalListService userRentalListService;

	public UserController(UserService userService, ConnectToRentalPageService connectToRentalPageService, UserRentalListService userRentalListService) {
		this.userService = userService;
		this.connectToRentalPageService = connectToRentalPageService;
		this.userRentalListService = userRentalListService;
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("login")
	public ApiResponse<?> login(@RequestBody UserLoginRequest loginRequest, HttpSession session) {

		session.setAttribute("rentalPage", null);
		HtmlPage htmlPage;

		if (StringUtils.isBlank(loginRequest.getStudentNumber()) || StringUtils.isBlank(loginRequest.getPassword())) {
			throw new InvalidParameterException("아이디 혹은 비밀번호를 입력해주세요.");
		}

		htmlPage = userService.login(loginRequest);
		htmlPage = connectToRentalPageService.connectToRentalPage(htmlPage);

		session.setAttribute("rentalPage", htmlPage);
		return CommonResponse.builder()
				.status(HttpStatus.CREATED)
				.message("로그인 성공")
				.build();
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("rentalList")
	public ApiResponse<?> rentalList(HttpSession session) {
		HtmlPage rentalPage = (HtmlPage) session.getAttribute("rentalPage");
		List<UserLectureRentalInfo> rentalList;

		rentalList = userRentalListService.rentalList(rentalPage);

		return CommonResponse.builder()
				.status(HttpStatus.OK)
				.message("사용자 대여 목록 조회 성공")
				.data(rentalList)
				.build();
	}

	@GetMapping("rentalCancel/{idx}")
	public ApiResponse<?> rentalCancel(@PathVariable(value = "idx") int idx, HttpSession session) {
		HtmlPage rentalPage = (HtmlPage) session.getAttribute("rentalPage");
		List<UserLectureRentalInfo> rentalList;

		rentalList = userRentalListService.rentalList(rentalPage);
		userRentalListService.rentalCancel(rentalPage,idx);

		return CommonResponse.builder()
				.status(HttpStatus.OK)
				.message("사용자 대여 목록 삭제 성공")
				.data(rentalList)
				.build();
	}
}
