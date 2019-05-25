package com.shouwn.oj.service.rent;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.shouwn.oj.model.request.rent.BuildingRequest;
import com.shouwn.oj.model.request.rent.ClassRoomRequest;
import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import static com.shouwn.oj.model.enums.rent.BuildingType.*;
import static com.shouwn.oj.model.enums.rent.ClassroomType.*;

@Service
public class LectureRoomService {

	public HtmlPage selectBuilding(HtmlPage rentPage, BuildingRequest buildingName) {
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
			System.out.println("buildingName: "+ buildingName.getBuildingName() );
			clickBuilding(rentPage, buildingName);
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
			if (inputName.getBuildingName().equals(SeungyeonHall.getBuildingName())) {
				    str =SeungyeonHall.getBuildingButton();
					link = anchor;
					break;
				}
				if (inputName.getBuildingName().equals(IlmanHall.getBuildingName())) {
					str =IlmanHall.getBuildingButton();
					link = anchor;
					break;
				}
				if(inputName.getBuildingName().equals(WoldanggHall.getBuildingName())) {
					str = WoldanggHall.getBuildingButton();
					link = anchor;
				}
				if (inputName.getBuildingName().equals(LeechunhwanHall.getBuildingName())) {
					str =LeechunhwanHall.getBuildingButton();
					link = anchor;
					break;
				}
				if(inputName.getBuildingName().equals(SsechunnunHall.getBuildingName())) {
					str =SsechunnunHall.getBuildingButton();
					link = anchor;
					break;
				}
				if(inputName.getBuildingName().equals(SungmieleHall.getBuildingName())) {
					str =SungmieleHall.getBuildingButton();
					link = anchor;
					break;
				}
				if(inputName.getBuildingName().equals(MieleHall.getBuildingName())) {
					str =MieleHall.getBuildingButton();
					link = anchor;
					break;
				}
			}
			 link.click();
			return;
		}

	public HtmlPage selectClassRoom(HtmlPage rentPage, ClassRoomRequest classRoomName) {

		try {
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
			clickClassRoom(rentPage, classRoomName);

		}catch (IOException e) {
		return ExceptionUtils.rethrow(e);
	}
		return rentPage;
	}

	private static void clickClassRoom(HtmlPage rentPage, ClassRoomRequest inputName) throws IOException {

		HtmlAnchor link = null;
		List<HtmlAnchor> anchors = rentPage.getAnchors();
		for (HtmlAnchor anchor : anchors) {
			String str = anchor.getId();
			if(inputName.getClassRoomName().equals(SeungyeonHall_1406.getClassroomName())||inputName.getClassRoomName().equals(IlmanHall_B103.getClassroomName())||inputName.getClassRoomName().equals(WoldanggHall_3301.getClassroomName())
					||inputName.getClassRoomName().equals(LeechunhwanHall_6110.getClassroomName())||inputName.getClassRoomName().equals(SsechunnunHall_7104.getClassroomName())||inputName.getClassRoomName().equals(SungmieleHall_9101.getClassroomName())
					||inputName.getClassRoomName().equals(MieleHall_M201.getClassroomName())){
				str ="gv시설목록_ctl02_btnSelect";
				link = anchor;
				break;
			}
			if(inputName.getClassRoomName().equals(SeungyeonHall_1407.getClassroomName())||inputName.getClassRoomName().equals(IlmanHall_B104.getClassroomName())||inputName.getClassRoomName().equals(WoldanggHall_3302.getClassroomName())
					||inputName.getClassRoomName().equals(LeechunhwanHall_6500.getClassroomName())||inputName.getClassRoomName().equals(SsechunnunHall_7201.getClassroomName())
					||inputName.getClassRoomName().equals(MieleHall_M202.getClassroomName())){
				str ="gv시설목록_ctl03_btnSelect";
				link = anchor;
				break;
			}
			if(inputName.getClassRoomName().equals(SeungyeonHall_1501.getClassroomName())||inputName.getClassRoomName().equals(IlmanHall_B105.getClassroomName())||inputName.getClassRoomName().equals(WoldanggHall_3501.getClassroomName())
					||inputName.getClassRoomName().equals(SsechunnunHall_7202.getClassroomName())
					||inputName.getClassRoomName().equals(MieleHall_M203.getClassroomName())){
				str ="gv시설목록_ctl04_btnSelect";
				link = anchor;
				break;
			}
			if(inputName.getClassRoomName().equals(SeungyeonHall_1502.getClassroomName())||inputName.getClassRoomName().equals(IlmanHall_B2101.getClassroomName())||inputName.getClassRoomName().equals(WoldanggHall_3502.getClassroomName())
					||inputName.getClassRoomName().equals(SsechunnunHall_7204.getClassroomName())
					||inputName.getClassRoomName().equals(MieleHall_M204.getClassroomName())){
				str ="gv시설목록_ctl05_btnSelect";
				link = anchor;
				break;
			}
			if(inputName.getClassRoomName().equals(SeungyeonHall_1503.getClassroomName())||inputName.getClassRoomName().equals(IlmanHall_B2102.getClassroomName())||inputName.getClassRoomName().equals(WoldanggHall_3503.getClassroomName())
					||inputName.getClassRoomName().equals(SsechunnunHall_7205.getClassroomName())
					||inputName.getClassRoomName().equals(MieleHall_M205.getClassroomName())){
				str ="gv시설목록_ctl06_btnSelect";
				link = anchor;
				break;
			}
			if(inputName.getClassRoomName().equals(SeungyeonHall_1504.getClassroomName())||inputName.getClassRoomName().equals(IlmanHall_2401.getClassroomName())||inputName.getClassRoomName().equals(WoldanggHall_3504.getClassroomName())
					||inputName.getClassRoomName().equals(SsechunnunHall_7206.getClassroomName())
					||inputName.getClassRoomName().equals(MieleHall_M301.getClassroomName())){
				str ="gv시설목록_ctl07_btnSelect";
				link = anchor;
				break;
			}
			if(inputName.getClassRoomName().equals(SeungyeonHall_1505.getClassroomName())||inputName.getClassRoomName().equals(IlmanHall_2402.getClassroomName())
					||inputName.getClassRoomName().equals(SsechunnunHall_7207.getClassroomName())
					||inputName.getClassRoomName().equals(MieleHall_M401.getClassroomName())){
				str ="gv시설목록_ctl08_btnSelect";
				link = anchor;
				break;
			}
			if(inputName.getClassRoomName().equals(SeungyeonHall_1506.getClassroomName())
					||inputName.getClassRoomName().equals(SsechunnunHall_7208.getClassroomName())
					||inputName.getClassRoomName().equals(MieleHall_M402.getClassroomName())){
				str ="gv시설목록_ctl09_btnSelect";
				link = anchor;
				break;
			}
			if(inputName.getClassRoomName().equals(SeungyeonHall_1507.getClassroomName())
					||inputName.getClassRoomName().equals(SsechunnunHall_7301.getClassroomName())
					||inputName.getClassRoomName().equals(MieleHall_M403.getClassroomName())){
				str ="gv시설목록_ctl10_btnSelect";
				link = anchor;
				break;
			}
			if(inputName.getClassRoomName().equals(SeungyeonHall_1508.getClassroomName())
					||inputName.getClassRoomName().equals(SsechunnunHall_7302.getClassroomName())
					||inputName.getClassRoomName().equals(MieleHall_M404.getClassroomName())){
				str ="gv시설목록_ctl11_btnSelect";
				link = anchor;
				break;
			}
			if(inputName.getClassRoomName().equals(SsechunnunHall_7303.getClassroomName())
					||inputName.getClassRoomName().equals(MieleHall_M406.getClassroomName())){
				str ="gv시설목록_ctl12_btnSelect";
				link = anchor;
				break;
			}
			if(inputName.getClassRoomName().equals(SsechunnunHall_7304.getClassroomName())
					||inputName.getClassRoomName().equals(MieleHall_M407.getClassroomName())){
				str ="gv시설목록_ctl13_btnSelect";
				link = anchor;
				break;
			}
			if(inputName.getClassRoomName().equals(SsechunnunHall_7305.getClassroomName())){
				str ="gv시설목록_ctl14_btnSelect";
				link = anchor;
				break;
			}
			if(inputName.getClassRoomName().equals(SsechunnunHall_7306.getClassroomName())){
				str ="gv시설목록_ctl15_btnSelect";
				link = anchor;
				break;
			}
			if(inputName.getClassRoomName().equals(SsechunnunHall_7307.getClassroomName())){
				str ="gv시설목록_ctl16_btnSelect";
				link = anchor;
				break;
			}
			if(inputName.getClassRoomName().equals(SsechunnunHall_7308.getClassroomName())){
				str ="gv시설목록_ctl17_btnSelect";
				link = anchor;
				break;
			}
			if(inputName.getClassRoomName().equals(SsechunnunHall_7309.getClassroomName())){
				str ="gv시설목록_ctl18_btnSelect";
				link = anchor;
				break;
			}
		}
		link.click();
		return;
	}

}
