package com.shouwn.oj.model.lectureRoom;

import com.shouwn.oj.model.building.Building;
import com.shouwn.oj.model.enums.lectureRoom.DetailType;

public class LectureRoom{

    private String lectureCode;
    private int people; //인원 수
    DetailType detailType; // enum처리해서 강의실별 특이상을 지정해줌

}
