package com.shouwn.oj.service.rent;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.shouwn.oj.model.request.rent.BuildingRequest;
import com.shouwn.oj.model.request.rent.ClassRoomRequest;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import static com.shouwn.oj.model.enums.rent.BuildingType.*;

@Service
public class LectureRoomService {
	//건물 목록 출력
	public HtmlPage building(HtmlPage rentPage, BuildingRequest buildingName) {
		List<HtmlElement> buildingList1;
		buildingList1 = rentPage.getByXPath("//table[@id='gv건물목록']/tbody/tr[@class='cssRowStyle']/td[1]");

		List<HtmlElement> buildingList2;
		buildingList2 = rentPage.getByXPath("//table[@id='gv건물목록']/tbody/tr[@class='cssAlternatingRowStyle']/td[1]");

		System.out.println("*******************************************");
		int count = 0;
		for (int j = 0; j < (buildingList1.size() + buildingList2.size()); j++) {
			if (count % 2 == 0) {
				System.out.println(buildingList1.get(j).asText());
				++count;
			}
			else {
				--j;
				System.out.println(buildingList2.get(j).asText());
				++count;
			}
			if (count == 10) {
				break;
			}
		}
		try {
			System.out.println("try가 시작됩니다.");
			System.out.println("buildingName: "+ buildingName );
			clickBuilding(rentPage, buildingName);
			List<HtmlElement> classroomList1;
			classroomList1 = rentPage.getByXPath("//table[@id='gv시설목록']/tbody/tr[@class='cssRowStyle']/td[1]");
			List<HtmlElement> classroomListNum1;
			classroomListNum1 = rentPage.getByXPath("//table[@id='gv시설목록']/tbody/tr[@class='cssRowStyle']/td[3]");

			List<HtmlElement> classroomList2;
			classroomList2 = rentPage.getByXPath("//table[@id='gv시설목록']/tbody/tr[@class='cssAlternatingRowStyle']/td[1]");
			List<HtmlElement> classroomListNum2;
			classroomListNum2 = rentPage.getByXPath("//table[@id='gv시설목록']/tbody/tr[@class='cssAlternatingRowStyle']/td[3]");

			System.out.println("*******************************************");
			int check = 0;
			for (int j = 0; j < (classroomList2.size() + classroomList1.size()); j++) {
				if (check % 2 == 0) {
					System.out.println(classroomList1.get(j).asText() + " 인원수 :" + classroomListNum1.get(j).asText());
					++check;
				}
				else {
					--j;
					System.out.println(classroomList2.get(j).asText() + " 인원수 :" + classroomListNum2.get(j).asText());
					++check;
				}
				if (check == 10) {
					break;
				}
			}
			return rentPage;
		}
		catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		}
	}

	private static void clickBuilding(HtmlPage rentPage, BuildingRequest inputName) throws IOException {

		HtmlAnchor link = null;
		List<HtmlAnchor> anchors = rentPage.getAnchors();
		for (HtmlAnchor anchor : anchors) {
			String str = anchor.getId();
			if (SeungyeonHall.getBuildingName().equals(inputName)) {
				if (anchor.getId().equals(SeungyeonHall.getBuildingButton())) {
					link = anchor;
				}
				if (Objects.equals(IlmanHall.getBuildingName(), inputName)) {
					if (anchor.getId().equals(IlmanHall.getBuildingButton())) {
						link = anchor;
					}
				}
				if (Objects.equals(WoldanggHall.getBuildingName(), inputName)) {
					if (anchor.getId().equals(WoldanggHall.getBuildingButton())) {
						link = anchor;
					}
				}
				if (Objects.equals(LeechunhwanHall.getBuildingName(), inputName)) {
					if (anchor.getId().equals(LeechunhwanHall.getBuildingButton())) {
						link = anchor;
					}
				}
				if (Objects.equals(SsechunnunHall.getBuildingName(), inputName)) {
					if (anchor.getId().equals(SsechunnunHall.getBuildingButton())) {
						link = anchor;
					}
				}
				if (Objects.equals(SungmieleHall.getBuildingName(), inputName)) {
					if (anchor.getId().equals(SungmieleHall.getBuildingButton())) {
						link = anchor;
					}
				}
				if (Objects.equals(MieleHall.getBuildingName(), inputName)) {
					if (anchor.getId().equals(MieleHall.getBuildingButton())) {
						link = anchor;
					}
				}
			}
			System.out.println("클릭할 것이다.");
			link.click();
			System.out.println("클릭했다.");
			return;
		}
	}
	/*public static HtmlPage classRoom(HtmlPage rentPage) {

		try {
			clickClassRoom(rentPage);
			List<HtmlElement> approveList;
			approveList = rentPage.getByXPath("//table[@id='gv시설대여현황']/tbody/tr[@class='cssRowStyle']/td[1]");
			List<HtmlElement> approveTimeList;
			approveTimeList = rentPage.getByXPath("//table[@id='gv시설대여현황']/tbody/tr[@class='cssRowStyle']/td[2]");
			for (int k = 0; k < approveList.size(); k++) {
				System.out.println(" 대여상태 : " + approveList.get(k).asText() + "    대여시간 :" + approveTimeList.get(k).asText());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rentPage;
	}

	private static void clickClassRoom(HtmlPage rentPage) throws IOException {
		HtmlAnchor link = null;
		List<HtmlAnchor> anchors = rentPage.getAnchors(); //강의실 버튼
		for (HtmlAnchor anchor : anchors) {
			String str = anchor.getId();

			if (anchor.getId().equals("gv시설목록_ctl02_btnSelect")){
				link = anchor;
			}
			if (anchor.getId().equals("gv시설목록_ctl03_btnSelect")){
				link = anchor;
			}
			if (anchor.getId().equals("gv시설목록_ctl04_btnSelect")){
				link = anchor;
			}
			if (anchor.getId().equals("gv시설목록_ctl05_btnSelect")){
				link = anchor;
			}
			if (anchor.getId().equals("gv시설목록_ctl06_btnSelect")){
				link = anchor;
			}
			if (anchor.getId().equals("gv시설목록_ctl07_btnSelect")){
				link = anchor;
			}
			if (anchor.getId().equals("gv시설목록_ctl08_btnSelect")){
				link = anchor;
			}
			if (anchor.getId().equals("gv시설목록_ctl09_btnSelect")){
				link = anchor;
			}
			if (anchor.getId().equals("gv시설목록_ctl10_btnSelect")){
				link = anchor;
			}
			if (anchor.getId().equals("gv시설목록_ctl11_btnSelect")){
				link = anchor;
			}
			if (anchor.getId().equals("gv시설목록_ctl12_btnSelect")){
				link = anchor;
			}
			if (anchor.getId().equals("gv시설목록_ctl13_btnSelect")){
				link = anchor;
			}
			if (anchor.getId().equals("gv시설목록_ctl14_btnSelect")){
				link = anchor;
			}
			if (anchor.getId().equals("gv시설목록_ctl15_btnSelect")){
				link = anchor;
			}
			if (anchor.getId().equals("gv시설목록_ctl16_btnSelect")){
				link = anchor;
			}
			if (anchor.getId().equals("gv시설목록_ctl17_btnSelect")){
				link = anchor;
			}
			if (anchor.getId().equals("gv시설목록_ctl18_btnSelect")){
				link = anchor;
			}
		}
		link.click();
		return;
	}*/

}
