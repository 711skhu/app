package com.shouwn.oj.model.entity.building;

import com.shouwn.oj.model.enums.Detail;
import com.shouwn.oj.model.enums.RentDate;

public class LectureRoom extends Building{

    String lectureCode;
    int people; //인원 수
    Detail detail;
    RentDate rentDate;

}
