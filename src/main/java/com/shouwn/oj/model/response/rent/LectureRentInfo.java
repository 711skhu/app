package com.shouwn.oj.model.response.rent;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureRentInfo {
	private RentDate rentDate;
	private String rentState;
	private String lectureCode;

	@Builder
	public LectureRentInfo(RentDate rentDate, String rentState, String lectureCode) {
		this.rentDate = rentDate;
		this.rentState = rentState;
		this.lectureCode = lectureCode;
	}
}