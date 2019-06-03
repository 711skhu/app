package com.shouwn.oj.model.response.user;

import com.shouwn.oj.model.response.rental.RentalDate;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLectureRentalInfo {
	private int idx;
	private RentalDate rentalDate;
	private String rentalState;
	private String lectureCode;
	private boolean cancel;

	@Builder
	public UserLectureRentalInfo(int idx, RentalDate rentalDate, String rentalState, String lectureCode, boolean cancel) {
		this.idx = idx;
		this.rentalDate = rentalDate;
		this.rentalState = rentalState;
		this.lectureCode = lectureCode;
		this.cancel = cancel;
	}
}