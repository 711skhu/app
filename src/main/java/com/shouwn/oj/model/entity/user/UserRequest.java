package com.shouwn.oj.model.entity.user;

import com.shouwn.oj.model.enums.RentDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    /*String buildingName;
    String code; //시설 코드*/

    RentDate rentDate;
    String reason; //대여 이유
    int number; //대여 인원
    String  phone;
}
