package com.shouwn.oj.model.response.rental;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureRentalInfo {
	private RentalDate rentalDate;
	private String rentalState;
	private String lectureCode;

	@Builder
	public LectureRentalInfo(RentalDate rentalDate, String rentalState, String lectureCode) {
		this.rentalDate = rentalDate;
		this.rentalState = rentalState;
		this.lectureCode = lectureCode;
	}
}