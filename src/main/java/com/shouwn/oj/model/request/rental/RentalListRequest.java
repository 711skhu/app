package com.shouwn.oj.model.request.rental;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RentalListRequest {
	private String lectureCode;
	private String rentalDate;
}
