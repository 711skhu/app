package com.shouwn.oj.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SuccessResponse<T> implements ApiResponse<T> {
    //대여신청시 성공여부를 status로 알려주기
	private int status;

	private String message;

	private T data;
}
