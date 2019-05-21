package com.shouwn.oj.model.enums.rent;

import lombok.Getter;

@Getter
public enum ClassroomType {

	SeungyeonHall_1406("1406", "gv시설목록_ctl02_btnSelect", DetailType.PROJECTOR, DetailType.SPECIAL),
	SeungyeonHall_1407("1407", "gv시설목록_ctl03_btnSelect", DetailType.PROJECTOR, DetailType.SPECIAL),
	SeungyeonHall_1501("1501", "gv시설목록_ctl04_btnSelect", DetailType.PROJECTOR, DetailType.SMALL),
	SeungyeonHall_1502("1502", "gv시설목록_ctl05_btnSelect", DetailType.PROJECTOR, DetailType.SMALL),
	SeungyeonHall_1503("1503", "gv시설목록_ctl06_btnSelect", DetailType.PROJECTOR, DetailType.MIDDLE),
	SeungyeonHall_1504("1504", "gv시설목록_ctl07_btnSelect", DetailType.PROJECTOR, DetailType.SMALL),
	SeungyeonHall_1505("1505", "gv시설목록_ctl08_btnSelect", DetailType.PROJECTOR, DetailType.MIDDLE),
	SeungyeonHall_1506("1506", "gv시설목록_ctl09_btnSelect", DetailType.PROJECTOR, DetailType.SMALL),
	SeungyeonHall_1507("1507", "gv시설목록_ctl10_btnSelect", DetailType.PROJECTOR, DetailType.SMALL),
	SeungyeonHall_1508("1508", "gv시설목록_ctl11_btnSelect", DetailType.PROJECTOR, DetailType.SMALL),

	IlmanHall_B103("B103", "gv시설목록_ctl02_btnSelect", DetailType.NULL, DetailType.SPECIAL),
	IlmanHall_B104("B104", "gv시설목록_ctl03_btnSelect", DetailType.NULL, DetailType.SPECIAL),
	IlmanHall_B105("B105", "gv시설목록_ctl04_btnSelect", DetailType.PROJECTOR, DetailType.SPECIAL),
	IlmanHall_B2101("B2101", "gv시설목록_ctl05_btnSelect", DetailType.PROJECTOR, DetailType.SPECIAL),
	IlmanHall_B2102("B2102", "gv시설목록_ctl06_btnSelect", DetailType.PROJECTOR, DetailType.SPECIAL),
	IlmanHall_2401("2401", "gv시설목록_ctl07_btnSelect", DetailType.PROJECTOR, DetailType.BIG),
	IlmanHall_2402("2402", "gv시설목록_ctl08_btnSelect", DetailType.PROJECTOR, DetailType.BIG),

	WoldanggHall_3301("3301","gv시설목록_ctl02_btnSelect", DetailType.PROJECTOR, DetailType.BIG),
	WoldanggHall_3302("3302","gv시설목록_ctl03_btnSelect", DetailType.PROJECTOR, DetailType.BIG),
	WoldanggHall_3501("3501","gv시설목록_ctl04_btnSelect", DetailType.PROJECTOR, DetailType.SMALL),
	WoldanggHall_3502("3502","gv시설목록_ctl05_btnSelect", DetailType.PROJECTOR, DetailType.SMALL),
	WoldanggHall_3503("3503","gv시설목록_ctl06_btnSelect", DetailType.PROJECTOR, DetailType.SMALL),
	WoldanggHall_3504("3504","gv시설목록_ctl07_btnSelect", DetailType.PROJECTOR, DetailType.SMALL),

	LeechunhwanHall_6110("6110","gv시설목록_ctl02_btnSelect", DetailType.PROJECTOR, DetailType.SPECIAL),
	LeechunhwanHall_6500("6500","gv시설목록_ctl03_btnSelect", DetailType.PROJECTOR, DetailType.SPECIAL);

	private String classroom;
	private String button;
	private DetailType detailType;
	private DetailType classType;

	ClassroomType(String classroom, String button, DetailType detailType, DetailType classType) {
		this.classroom = classroom;
		this.button = button;
		this.detailType = detailType;
		this.classType = classType;
	}


	}
