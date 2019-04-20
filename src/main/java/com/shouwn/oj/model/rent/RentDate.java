package com.shouwn.oj.model.rent;


import lombok.Getter;
import java.time.*;

@Getter
public class RentDate {

    private int startTime;
    private int endTime;
    LocalDate rentDay;

}

