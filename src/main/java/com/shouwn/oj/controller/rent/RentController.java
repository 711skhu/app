package com.shouwn.oj.controller.rent;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.shouwn.oj.exception.rent.RentException;
import com.shouwn.oj.model.request.rent.BuildingRequest;
import com.shouwn.oj.model.request.rent.ClassRoomRequest;
import com.shouwn.oj.model.response.ApiResponse;
import com.shouwn.oj.model.response.CommonResponse;
import com.shouwn.oj.model.response.rent.LectureRentInfo;
import com.shouwn.oj.model.response.rent.LectureRoom;
import com.shouwn.oj.service.rent.ConnectToRentPageService;
import com.shouwn.oj.service.rent.LectureRoomService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class RentController {
	private final ConnectToRentPageService connectToRentPageService;
	private final LectureRoomService lectureRoomService;

	public RentController(ConnectToRentPageService connectToRentPageService, LectureRoomService lectureRoomService) {
		this.connectToRentPageService = connectToRentPageService;
		this.lectureRoomService = lectureRoomService;
	}

	/*@PreAuthorize("isAuthenticated()")*/
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

	/*@PreAuthorize("isAuthenticated()")*/
	@GetMapping("buildings/{buildingNumber}/classrooms")
	public ApiResponse<?> getBuilding(@PathVariable int buildingNumber, HttpSession session) {
		HtmlPage rentPage = (HtmlPage) session.getAttribute("rentPage");

		try {
			rentPage = lectureRoomService.selectBuilding(rentPage, buildingNumber);
		} catch (RentException e) {
			return CommonResponse.builder()
					.status(HttpStatus.FORBIDDEN)
					.message(e.getMessage())
					.build();
		}
		session.setAttribute("rentPage", rentPage);
		return CommonResponse.builder()
				.status(HttpStatus.OK)
				.message("빌딩 클릭 성공")
				/*.data(classRoomList)*/
				.build();
	}

/*	@PreAuthorize("isAuthenticated()")*/
	@GetMapping("classRoomList")
	public ApiResponse<?> classRoomList(HttpSession session) {
		HtmlPage rentPage = (HtmlPage) session.getAttribute("rentPage");
		List<LectureRoom> lectureRooms;

		try {
			lectureRooms = lectureRoomService.classRoomList(rentPage);
		} catch (RentException e) {
			return CommonResponse.builder()
					.status(HttpStatus.FORBIDDEN)
					.message(e.getMessage())
					.build();
		}

		return CommonResponse.builder()
				.status(HttpStatus.OK)
				.message("강의실 목록 조회 성공")
				.data(lectureRooms)
				.build();
	}
	/*@PreAuthorize("isAuthenticated()")*/
	@GetMapping("getClassRoom")
	public ApiResponse<?> getClassRoom(@RequestBody ClassRoomRequest classRoomName, HttpSession session) {
		HtmlPage rentPage = (HtmlPage) session.getAttribute("rentPage");

		try {
			rentPage = (HtmlPage) lectureRoomService.selectClassRoom(rentPage, classRoomName);
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

	/* <URI>
	 건물별 강의실 목록 조회 ->rent/{buildingList}
	강의실에 따른 시간별 목록 리스트 -> rent/{lectureCode}/{date}
	사용자가 강의실 대여 신청 ->rent
	사용자의 강의실 대여 목록 리스트 ->rent/rentList */


