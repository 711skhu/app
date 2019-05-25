package com.shouwn.oj.model.enums.rent;

import lombok.Getter;

@Getter
public enum BuildingType {

	SeungyeonHall("1.승연관","gv건물목록_ctl02_btnSelect"),
	IlmanHall("2.일만관","gv건물목록_ctl03_btnSelect"),
	WoldanggHall("3.월당관","gv건물목록_ctl04_btnSelect"),
	LeechunhwanHall("6.이천환관","gv건물목록_ctl07_btnSelect"),
	SsechunnunHall("7.새천년관","gv건물목록_ctl08_btnSelect"),
	SungmieleHall ("9.성미가엘성당","gv건물목록_ctl09_btnSelect"),
	MieleHall ("11.미가엘관","gv건물목록_ctl10_btnSelect");

	private String buildingName;
	private String buildingButton;

	BuildingType(String buildingName, String buildingButton ) {
		this.buildingName = buildingName;
		this.buildingButton = buildingButton;
	}

}
