package com.shouwn.oj.model.request.rent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentRequest{
    private String reason; //대여 이유
    private String numberList; //대여 인원 목록
    private String phone;

    private  int startTime;
    private  int endTime;
}
