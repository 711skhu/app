package com.shouwn.oj.model.request.rent;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BuildingRequest {
	private String buildingName; //빌딩 이름 
}
