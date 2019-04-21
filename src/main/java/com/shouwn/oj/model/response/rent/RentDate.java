package com.shouwn.oj.model.response.rent;


import lombok.Getter;
import java.time.*;

@Getter
public class RentDate {

    private int startTime;
    private int endTime;
    private LocalDate rentDay;
}

