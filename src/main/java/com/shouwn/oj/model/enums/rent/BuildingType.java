package com.shouwn.oj.model.enums.rent;

import lombok.Getter;

@Getter
public enum BuildingType {

	SeungyeonHall("1.승연관","gv건물목록_ctl02_btnSelect"/*,Arrays.asList("1406","1407","1501","1502","1503","1504","1505","1506","1507","1508")*/),
	IlmanHall("2.일만관","gv건물목록_ctl03_btnSelect"/*,Arrays.asList("B103","B104","B105","B2101","B2102","2401","2402")*/),
	WoldanggHall("3.월당관","gv건물목록_ctl04_btnSelect"/*,Arrays.asList("3301","3302","3501","3502","3503","3504")*/),
	/*YeollimgHall("4.열림관"), 렌트 불가*/
	/*NanumgHall("5.나눔관"), 동아리 방임 일단 보류*/
	LeechunhwanHall("6.이천환관","gv건물목록_ctl07_btnSelect"/*,Arrays.asList("6110","6500")*/),
	SsechunnunHall("7.새천년관","gv건물목록_ctl08_btnSelect"/*,Arrays.asList("7104","7201","7202","7204","7205","7206","7207","7208","7301","7302","7303","7304","7305","7306","7307","7308")*/),
	SungmieleHall ("9.성미가엘성당","gv건물목록_ctl09_btnSelect"/*,Arrays.asList("9101")*/),
	MieleHall ("11.미가엘관","gv건물목록_ctl10_btnSelect"/*,Arrays.asList("M201","M202","M203","M204","M205","M301","M401","M402","M403","M404","M406","M407")*/);
	/*Schoolyard ("20.운동장");렌트 불가*/

	private String buildingName;
	private String buildingButton;

	BuildingType(String buildingName, String buildingButton ) {
		this.buildingName = buildingName;
		this.buildingButton = buildingButton;
	}

}
