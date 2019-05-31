package com.shouwn.oj.controller.rental;

import java.util.List;
import javax.servlet.http.HttpSession;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.shouwn.oj.exception.rental.RentalException;
import com.shouwn.oj.model.response.ApiResponse;
import com.shouwn.oj.model.response.CommonResponse;
import com.shouwn.oj.model.response.rental.LectureRoom;
import com.shouwn.oj.service.rental.LectureRentalInfoService;
import com.shouwn.oj.service.rental.LectureRoomInfoService;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentalController {
	private final LectureRoomInfoService lectureRoomInfoService;
	private final LectureRentalInfoService lectureRentalInfoService;

	public RentalController(LectureRoomInfoService lectureRoomInfoService, LectureRentalInfoService lectureRentalInfoService) {
		this.lectureRoomInfoService = lectureRoomInfoService;
		this.lectureRentalInfoService = lectureRentalInfoService;
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("buildings/{buildingNumber}/classrooms")
	public ApiResponse<?> getClassRoomList(@PathVariable int buildingNumber, HttpSession session) {

		HtmlPage rentalPage = (HtmlPage) session.getAttribute("rentalPage");
		List<LectureRoom> lectureRooms;

		try {
			rentalPage = lectureRoomInfoService.selectBuilding(rentalPage, buildingNumber);
			lectureRooms = lectureRoomInfoService.classRoomList(rentalPage);
		} catch (RentalException e) {
			return CommonResponse.builder()
					.status(HttpStatus.FORBIDDEN)
					.message(e.getMessage())
					.build();
		}

		session.setAttribute("rentalPage", rentalPage);
		return CommonResponse.builder()
				.status(HttpStatus.OK)
				.message("강의실 목록 조회 성공")
				.data(lectureRooms)
				.build();
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("classrooms/{classroomName}/rentalList")
	public ApiResponse<?> getRentalList(@PathVariable String classroomName, HttpSession session) {

		HtmlPage rentalPage = (HtmlPage) session.getAttribute("rentalPage");

		try {
			rentalPage = lectureRentalInfoService.selectClassRoom(rentalPage, classroomName);
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


