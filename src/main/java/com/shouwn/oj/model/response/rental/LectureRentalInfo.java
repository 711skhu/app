package com.shouwn.oj.model.response.rental;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureRentalInfo {
	private int idx;
	private RentalDate rentalDate;
	private String rentalState;
	private String lectureCode;
	private boolean cancel;

	@Builder
	public LectureRentalInfo(int idx, RentalDate rentalDate, String rentalState, String lectureCode, boolean cancel) {
		this.idx = idx;
		this.rentalDate = rentalDate;
		this.rentalState = rentalState;
		this.lectureCode = lectureCode;
		this.cancel = cancel;
	}
}