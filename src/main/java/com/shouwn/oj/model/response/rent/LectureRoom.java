package com.shouwn.oj.model.response.rent;

import com.shouwn.oj.model.enums.rent.DetailType;

public class LectureRoom{

    private String lectureCode;
    private int people; //인원 수
    private DetailType detailType; // enum처리해서 강의실별 특이상을 지정해줌
}
