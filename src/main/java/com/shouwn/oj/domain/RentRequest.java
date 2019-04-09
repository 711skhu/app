package com.shouwn.oj.domain;

import com.shouwn.oj.model.entity.RentDate;

public class RentRequest {
    //강의실 대여 신청

    String buildingName;
    String code; //시설 코드
    RentDate rentDate;
    String reason; //대여 이유
    int number; //대여 인원
    String  phone;

}
