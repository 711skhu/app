package com.shouwn.oj.controller.rent;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.shouwn.oj.exception.rent.RentException;
import com.shouwn.oj.model.response.ApiResponse;
import com.shouwn.oj.model.response.CommonResponse;
import com.shouwn.oj.model.response.rent.LectureRoom;
import com.shouwn.oj.service.rent.ConnectToRentPageService;
import com.shouwn.oj.service.rent.LectureRoomService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class RentController {
	private final ConnectToRentPageService connectToRentPageService;
	private final LectureRoomService lectureRoomService;

	public RentController(ConnectToRentPageService connectToRentPageService, LectureRoomService lectureRoomService) {
		this.connectToRentPageService = connectToRentPageService;
		this.lectureRoomService = lectureRoomService;
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

	@PreAuthorize("isAuthenticated()")
	@GetMapping("buildings/{buildingNumber}/classrooms")
	public ApiResponse<?> getClassRoomList(@PathVariable int buildingNumber, HttpSession session) {
		HtmlPage rentPage = (HtmlPage) session.getAttribute("rentPage");
		List<LectureRoom> lectureRooms;
		try {
			rentPage = lectureRoomService.selectBuilding(rentPage, buildingNumber);
			Thread.sleep(3000);
			lectureRooms = lectureRoomService.classRoomList(rentPage);
		} catch (RentException e) {
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
		session.setAttribute("rentPage", rentPage);
		return CommonResponse.builder()
				.status(HttpStatus.OK)
				.message("빌딩 클릭 성공")
				.data(lectureRooms)
				.build();
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("classrooms/{classroomName}")
	public ApiResponse<?> getClassRoom(@PathVariable String classroomName, HttpSession session) {
		HtmlPage rentPage = (HtmlPage) session.getAttribute("rentPage");

		try {
			rentPage = lectureRoomService.selectClassRoom(rentPage, classroomName);
		} catch (RentException e) {
			return CommonResponse.builder()
					.status(HttpStatus.FORBIDDEN)
					.message(e.getMessage())
					.build();
		}
		session.setAttribute("rentPage", rentPage);
		return CommonResponse.builder()
				.status(HttpStatus.OK)
				.message("강의실 클릭 성공")
				.build();
	}
}


