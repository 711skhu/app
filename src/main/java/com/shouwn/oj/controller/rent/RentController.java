package com.shouwn.oj.controller.rent;

import javax.servlet.http.HttpSession;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.shouwn.oj.exception.rent.RentException;
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

		HtmlPage htmlPage = (HtmlPage) session.getAttribute("rentPage");

		try {
			htmlPage = connectToRentPageService.connectToRentPage(htmlPage);
		} catch (RentException e) {
			return CommonResponse.builder()
					.status(HttpStatus.FORBIDDEN)
					.message(e.getMessage())
					.build();
		}

		session.setAttribute("rentPage", htmlPage);
		return CommonResponse.builder()
				.status(HttpStatus.OK)
				.message("연결 성공")
				.build();
	}

}
