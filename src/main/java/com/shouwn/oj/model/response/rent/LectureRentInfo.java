package com.shouwn.oj.model.response.rent;

import com.shouwn.oj.model.enums.rent.RentStateType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureRentInfo {
	private RentDate rentDate;
	private RentStateType rentStateType;
	private String lectureCode;

	@Builder
	public LectureRentInfo(RentDate rentDate, RentStateType rentStateType, String lectureCode) {
		this.rentDate = rentDate;
		this.rentStateType = rentStateType;
		this.lectureCode = lectureCode;
	}
}