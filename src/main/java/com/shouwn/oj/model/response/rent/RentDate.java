package com.shouwn.oj.model.response.rent;

import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RentDate {
	private int startTime;
	private int endTime;
	private LocalDate rentDay;

	@Builder
	public RentDate(int startTime, int endTime, LocalDate rentDay) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.rentDay = rentDay;
	}
}

