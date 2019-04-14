package com.shouwn.oj.model.enums;


import lombok.Getter;
import javax.persistence.MappedSuperclass;
import java.time.*;

@Getter
@MappedSuperclass
public class RentDate {

    LocalTime start;
    LocalTime end;
    LocalDate rentDay;

}

