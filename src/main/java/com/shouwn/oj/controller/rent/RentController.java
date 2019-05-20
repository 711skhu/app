package com.shouwn.oj.controller.rent;

import javax.servlet.http.HttpSession;

import com.shouwn.oj.model.response.ApiResponse;
import com.shouwn.oj.model.response.CommonResponse;
import com.shouwn.oj.service.rent.ConnectToRentPageService;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rent")
public class RentController {
	private final ConnectToRentPageService connectToRentPageService;

	public RentController(ConnectToRentPageService connectToRentPageService) {
		this.connectToRentPageService = connectToRentPageService;
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("connect")
	public ApiResponse<?> connectToRentPage(HttpSession session) {
		int responseCode = connectToRentPageService.connectToRentPage(session);

		switch (responseCode) {
			case -1:
				return CommonResponse.builder()
						.status(HttpStatus.FORBIDDEN)
						.message("잘못된 접근입니다.")
						.build();
			case 1:
				return CommonResponse.builder()
						.status(HttpStatus.OK)
						.message("연결 성공")
						.build();
			default:
				return CommonResponse.builder()
						.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.message("연결 실패")
						.build();
		}
	}
}
