package com.shouwn.oj.controller.rent;

import org.springframework.stereotype.Controller;

@Controller

public class RentController {
    // 빌릴 때 사용하는 Controller
	// 삭제를 받는 것을 포함해야된다.
	// building을 선택한 후에 그 building의 강의실 리스트 호출 controller
	//LectureRentInfo의 정보를 맵핑해서 리스트로 만들어준다.
	//사용자의 자신의 rent리스트를 보여준다.

	/* <URI>
	 건물별 강의실 목록 조회 ->rent/{buildingName}
	강의실에 따른 시간별 목록 리스트 -> rent/{lectureCode}/{date}
	사용자가 강의실 대여 신청 ->rent
	사용자의 강의실 대여 목록 리스트 ->rent/rentList */
}
