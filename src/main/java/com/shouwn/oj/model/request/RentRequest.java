package com.shouwn.oj.model.request;

import com.shouwn.oj.model.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentRequest extends User {

    private String reason; //대여 이유
    private int number; //대여 인원
    private String  phone;
}
