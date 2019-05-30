package com.shouwn.oj.controller.rental;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.shouwn.oj.exception.rental.RentalException;
import com.shouwn.oj.model.response.ApiResponse;
import com.shouwn.oj.model.response.CommonResponse;
import com.shouwn.oj.model.response.rental.LectureRoom;
import com.shouwn.oj.service.rental.ConnectToRentalPageService;
import com.shouwn.oj.service.rental.LectureRoomService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class RentalController {
	private final ConnectToRentalPageService connectToRentalPageService;
	private final LectureRoomService lectureRoomService;

	public RentalController(ConnectToRentalPageService connectToRentalPageService, LectureRoomService lectureRoomService) {
		this.connectToRentalPageService = connectToRentalPageService;
		this.lectureRoomService = lectureRoomService;
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("connection")
	public ApiResponse<?> connectToRentalPage(HttpSession session) {

		HtmlPage htmlPage = (HtmlPage) session.getAttribute("rentalPage");

		try {
			htmlPage = connectToRentalPageService.connectToRentalPage(htmlPage);
		} catch (RentalException e) {
			return CommonResponse.builder()
					.status(HttpStatus.FORBIDDEN)
					.message(e.getMessage())
					.build();
		}

		session.setAttribute("rentalPage", htmlPage);
		return CommonResponse.builder()
				.status(HttpStatus.OK)
				.message("연결 성공")
				.build();
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("buildings/{buildingNumber}/classrooms")
	public ApiResponse<?> getClassRoomList(@PathVariable int buildingNumber, HttpSession session) {
		HtmlPage rentalPage = (HtmlPage) session.getAttribute("rentalPage");
		List<LectureRoom> lectureRooms;
		try {
			rentalPage = lectureRoomService.selectBuilding(rentalPage, buildingNumber);
			Thread.sleep(3000);
			lectureRooms = lectureRoomService.classRoomList(rentalPage);
		} catch (RentalException e) {
			return CommonResponse.builder()
					.status(HttpStatus.FORBIDDEN)
					.message(e.getMessage())
					.build();
		} catch (InterruptedException e) {
			return CommonResponse.builder()
					.status(HttpStatus.FORBIDDEN)
					.message("")
					.build();
		}
		session.setAttribute("rentalPage", rentalPage);
		return CommonResponse.builder()
				.status(HttpStatus.OK)
				.message("빌딩 클릭 성공")
				.data(lectureRooms)
				.build();
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("classrooms/{classroomName}")
	public ApiResponse<?> getClassRoom(@PathVariable String classroomName, HttpSession session) {
		HtmlPage rentalPage = (HtmlPage) session.getAttribute("rentalPage");

		try {
			rentalPage = lectureRoomService.selectClassRoom(rentalPage, classroomName);
		} catch (RentalException e) {
			return CommonResponse.builder()
					.status(HttpStatus.FORBIDDEN)
					.message(e.getMessage())
					.build();
		}
		session.setAttribute("rentalPage", rentalPage);
		return CommonResponse.builder()
				.status(HttpStatus.OK)
				.message("강의실 클릭 성공")
				.build();
	}
}


