package com.shouwn.oj.service.rent;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.shouwn.oj.model.enums.rent.BuildingType;
import com.shouwn.oj.model.enums.rent.ClassroomType;
import com.shouwn.oj.model.response.rent.LectureRoom;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;

@Service
public class LectureRoomService {

	public HtmlPage selectBuilding(HtmlPage rentPage, int buildingNumber) {
		try {
			BuildingType type = BuildingType.valueOf(buildingNumber);
			for (HtmlAnchor anchor : rentPage.getAnchors()) {
				if (StringUtils.equals(type.getBuildingButton(), anchor.getId())) {
					rentPage = anchor.click();
					break;
				}
			}
			return rentPage;
		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		}
	}

	public List<LectureRoom> classRoomList(HtmlPage rentPage) {
		List<LectureRoom> lectureRooms = new ArrayList<>();
		HtmlTable lectureRoomsTable = (HtmlTable) rentPage.getElementById("gv시설목록");
		for (int i = 1; i < lectureRoomsTable.getRowCount(); i++) {
			String classroomName = lectureRoomsTable.getCellAt(i, 0).asText();
			int people = Integer.parseInt(lectureRoomsTable.getCellAt(i, 2).asText());
			ClassroomType classroomType = ClassroomType.value(classroomName);
			if (StringUtils.equals(classroomType.getClassroomName(), classroomName)) {
				lectureRooms.add(new LectureRoom(classroomType.getClassroomName(), people, classroomType.getDetailType(), classroomType.getClassType()));
			}
		}
		return lectureRooms;
	}

	public HtmlPage selectClassRoom(HtmlPage rentPage, String classroomName) {
		try {
			ClassroomType type = ClassroomType.value(classroomName);
			for (HtmlAnchor anchor : rentPage.getAnchors()) {
				if (StringUtils.equals(type.getButton(), anchor.getId())) {
					rentPage = anchor.click();
				}
			}
			return rentPage;
		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		}
	}
}
