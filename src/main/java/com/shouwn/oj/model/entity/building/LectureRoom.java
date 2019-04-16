package com.shouwn.oj.model.entity.building;

import com.shouwn.oj.model.enums.DetailType;
import com.shouwn.oj.model.enums.RentDate;

public class LectureRoom extends Building{

    private String lectureCode;
    private int people; //인원 수
    DetailType detailType;
    RentDate rentDate;

}
