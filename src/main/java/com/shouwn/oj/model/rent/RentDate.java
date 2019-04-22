package com.shouwn.oj.model.rent;


import lombok.Getter;
import java.time.*;
import lombok.Setter;

@Getter
@Setter
public class RentDate {

    private int startTime;
    private int endTime;
    LocalDate rentDay;

}

