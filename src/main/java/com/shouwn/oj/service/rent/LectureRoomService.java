package com.shouwn.oj.service.rent;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.shouwn.oj.model.enums.rent.BuildingType;
import com.shouwn.oj.model.request.rent.BuildingRequest;
import com.shouwn.oj.model.request.rent.ClassRoomRequest;
import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

import static com.shouwn.oj.model.enums.rent.BuildingType.*;
import static com.shouwn.oj.model.enums.rent.ClassroomType.*;

@Service
public class LectureRoomService {

	public HtmlPage selectBuilding(HtmlPage rentPage, int buildingNumber) {
			try {
			clickBuilding(rentPage, BuildingType.valueOf(buildingNumber));
			return rentPage;
		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		}
	}

	private static void clickBuilding(HtmlPage rentPage, BuildingType type) throws IOException {
		for (HtmlAnchor anchor : rentPage.getAnchors()) {
			if (StringUtils.equals(type.getBuildingButton(), anchor.getId())) {
				anchor.click();
			}
		}
	}

	public HtmlPage selectClassRoom(HtmlPage rentPage, ClassRoomRequest classRoomName) {

		try {
			clickClassRoom(rentPage, classRoomName);
		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		}
		return rentPage;
	}

	private static void clickClassRoom(HtmlPage rentPage, ClassRoomRequest inputName) throws IOException {

		HtmlAnchor link = null;
		List<HtmlAnchor> anchors = rentPage.getAnchors();
		for (HtmlAnchor anchor : anchors) {
			String str = anchor.getId();
			if (inputName.getClassRoomName().equals(SEUNGYEONHALL_1406.getClassroomName()) || inputName.getClassRoomName().equals(IIMANHALL_B103.getClassroomName()) || inputName.getClassRoomName().equals(WOLDANGGHALL_3301.getClassroomName())
					|| inputName.getClassRoomName().equals(LEECHUNHWANHALL_6110.getClassroomName()) || inputName.getClassRoomName().equals(SSECHUNNUNHALL_7104.getClassroomName()) || inputName.getClassRoomName().equals(SUNGMIELEHALL_9101.getClassroomName())
					|| inputName.getClassRoomName().equals(MIELEHALL_M201.getClassroomName())) {
				str = "gv시설목록_ctl02_btnSelect";
				link = anchor;
				break;
			}
			if (inputName.getClassRoomName().equals(SEUNGYEONHALL_1407.getClassroomName()) || inputName.getClassRoomName().equals(IIMANHALL_B104.getClassroomName()) || inputName.getClassRoomName().equals(WOLDANGGHALL_3302.getClassroomName())
					|| inputName.getClassRoomName().equals(LEECHUNHWANHALL_6500.getClassroomName()) || inputName.getClassRoomName().equals(SSECHUNNUNHALL_7201.getClassroomName())
					|| inputName.getClassRoomName().equals(MIELEHALL_M202.getClassroomName())) {
				str = "gv시설목록_ctl03_btnSelect";
				link = anchor;
				break;
			}
			if (inputName.getClassRoomName().equals(SEUNGYEONHALL_1501.getClassroomName()) || inputName.getClassRoomName().equals(IIMANHALL_B105.getClassroomName()) || inputName.getClassRoomName().equals(WOLDANGGHALL_3501.getClassroomName())
					|| inputName.getClassRoomName().equals(SSECHUNNUNHALL_7202.getClassroomName())
					|| inputName.getClassRoomName().equals(MIELEHALL_M203.getClassroomName())) {
				str = "gv시설목록_ctl04_btnSelect";
				link = anchor;
				break;
			}
			if (inputName.getClassRoomName().equals(SEUNGYEONHALL_1502.getClassroomName()) || inputName.getClassRoomName().equals(IIMANHALL_B2101.getClassroomName()) || inputName.getClassRoomName().equals(WOLDANGGHALL_3502.getClassroomName())
					|| inputName.getClassRoomName().equals(SSECHUNNUNHALL_7204.getClassroomName())
					|| inputName.getClassRoomName().equals(MIELEHALL_M204.getClassroomName())) {
				str = "gv시설목록_ctl05_btnSelect";
				link = anchor;
				break;
			}
			if (inputName.getClassRoomName().equals(SEUNGYEONHALL_1503.getClassroomName()) || inputName.getClassRoomName().equals(IIMANHALL_B2102.getClassroomName()) || inputName.getClassRoomName().equals(WOLDANGGHALL_3503.getClassroomName())
					|| inputName.getClassRoomName().equals(SSECHUNNUNHALL_7205.getClassroomName())
					|| inputName.getClassRoomName().equals(MIELEHALL_M205.getClassroomName())) {
				str = "gv시설목록_ctl06_btnSelect";
				link = anchor;
				break;
			}
			if (inputName.getClassRoomName().equals(SEUNGYEONHALL_1504.getClassroomName()) || inputName.getClassRoomName().equals(IIMANHALL_2401.getClassroomName()) || inputName.getClassRoomName().equals(WOLDANGGHALL_3504.getClassroomName())
					|| inputName.getClassRoomName().equals(SSECHUNNUNHALL_7206.getClassroomName())
					|| inputName.getClassRoomName().equals(MIELEHALL_M301.getClassroomName())) {
				str = "gv시설목록_ctl07_btnSelect";
				link = anchor;
				break;
			}
			if (inputName.getClassRoomName().equals(SEUNGYEONHALL_1505.getClassroomName()) || inputName.getClassRoomName().equals(IIMANHALL_2402.getClassroomName())
					|| inputName.getClassRoomName().equals(SSECHUNNUNHALL_7207.getClassroomName())
					|| inputName.getClassRoomName().equals(MIELEHALL_M401.getClassroomName())) {
				str = "gv시설목록_ctl08_btnSelect";
				link = anchor;
				break;
			}
			if (inputName.getClassRoomName().equals(SEUNGYEONHALL_1506.getClassroomName())
					|| inputName.getClassRoomName().equals(SSECHUNNUNHALL_7208.getClassroomName())
					|| inputName.getClassRoomName().equals(MIELEHALL_M402.getClassroomName())) {
				str = "gv시설목록_ctl09_btnSelect";
				link = anchor;
				break;
			}
			if (inputName.getClassRoomName().equals(SEUNGYEONHALL_1507.getClassroomName())
					|| inputName.getClassRoomName().equals(SSECHUNNUNHALL_7301.getClassroomName())
					|| inputName.getClassRoomName().equals(MIELEHALL_M403.getClassroomName())) {
				str = "gv시설목록_ctl10_btnSelect";
				link = anchor;
				break;
			}
			if (inputName.getClassRoomName().equals(SEUNGYEONHALL_1508.getClassroomName())
					|| inputName.getClassRoomName().equals(SSECHUNNUNHALL_7302.getClassroomName())
					|| inputName.getClassRoomName().equals(MIELEHALL_M404.getClassroomName())) {
				str = "gv시설목록_ctl11_btnSelect";
				link = anchor;
				break;
			}
			if (inputName.getClassRoomName().equals(SSECHUNNUNHALL_7303.getClassroomName())
					|| inputName.getClassRoomName().equals(MIELEHALL_M406.getClassroomName())) {
				str = "gv시설목록_ctl12_btnSelect";
				link = anchor;
				break;
			}
			if (inputName.getClassRoomName().equals(SSECHUNNUNHALL_7304.getClassroomName())
					|| inputName.getClassRoomName().equals(MIELEHALL_M407.getClassroomName())) {
				str = "gv시설목록_ctl13_btnSelect";
				link = anchor;
				break;
			}
			if (inputName.getClassRoomName().equals(SSECHUNNUNHALL_7305.getClassroomName())) {
				str = "gv시설목록_ctl14_btnSelect";
				link = anchor;
				break;
			}
			if (inputName.getClassRoomName().equals(SSECHUNNUNHALL_7306.getClassroomName())) {
				str = "gv시설목록_ctl15_btnSelect";
				link = anchor;
				break;
			}
			if (inputName.getClassRoomName().equals(SSECHUNNUNHALL_7307.getClassroomName())) {
				str = "gv시설목록_ctl16_btnSelect";
				link = anchor;
				break;
			}
			if (inputName.getClassRoomName().equals(SSECHUNNUNHALL_7308.getClassroomName())) {
				str = "gv시설목록_ctl17_btnSelect";
				link = anchor;
				break;
			}
			if (inputName.getClassRoomName().equals(SSECHUNNUNHALL_7309.getClassroomName())) {
				str = "gv시설목록_ctl18_btnSelect";
				link = anchor;
				break;
			}
		}
		link.click();
		return;
	}

}
