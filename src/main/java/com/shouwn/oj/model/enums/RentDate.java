package com.shouwn.oj.model.enums;


import lombok.Getter;
import javax.persistence.MappedSuperclass;
import java.time.*;

@Getter
@MappedSuperclass
public class RentDate {

    int start;
    int end;
    LocalDate rentDay;

}

