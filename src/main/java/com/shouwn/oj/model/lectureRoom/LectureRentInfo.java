package com.shouwn.oj.model.lectureRoom;

import com.shouwn.oj.model.rent.RentDate;
import com.shouwn.oj.model.enums.rent.RentStateType;

public class LectureRentInfo {
    // 선택한 시설의 대여 현황 리스트 중 한 명의 대여자에 대한 정보(진행상태, 대여일자)를 받는다.
    // 대여자랑 사유는 취급하지 않음

    private  RentDate rentDate;
    private  RentStateType rentStateType;
}
