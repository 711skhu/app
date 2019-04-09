package com.shouwn.oj.model.entity;


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

