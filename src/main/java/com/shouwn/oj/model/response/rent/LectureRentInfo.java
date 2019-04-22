package com.shouwn.oj.model.response.rent;

import com.shouwn.oj.model.enums.rent.RentStateType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureRentInfo {
    // 선택한 시설의 대여 현황 리스트 중 한 명의 대여자에 대한 정보(진행상태, 대여일자)를 받는다.
    // 대여자랑 사유는 취급하지 않음
    private RentDate rentDate;
    private RentStateType rentStateType;

    @Builder
    public LectureRentInfo(RentDate rentDate, RentStateType rentStateType) {
        this.rentDate = rentDate;
        this.rentStateType = rentStateType;
    }
}