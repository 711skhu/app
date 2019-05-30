package com.shouwn.oj.service.rent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.shouwn.oj.model.enums.rent.BuildingType;
import com.shouwn.oj.model.enums.rent.ClassroomType;
import com.shouwn.oj.model.response.rent.LectureRoom;
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
		HtmlTable lectureRoomsTable = (HtmlTable) rentPage.getElementById("gv시설목록");
		List<LectureRoom> lectureRooms = new ArrayList<>();
		int pageCount = 1;

		try {
			for (int i = 1; i < lectureRoomsTable.getRowCount(); i++) {
				if (lectureRoomsTable.getRow(i).getChildElementCount() == 1) {
					for (HtmlAnchor anchor : rentPage.getAnchors()) {
						if (anchor.asText().equals(Integer.toString(pageCount + 1))) {
							rentPage = anchor.click();
							Thread.sleep(3000);
							++pageCount;
							i = 1;
							lectureRoomsTable = (HtmlTable) rentPage.getElementById("gv시설목록");
							break;
						}
					}
					if (i != 1) {
						break;
					}
				}

				String classroomName = lectureRoomsTable.getCellAt(i, 0).asText();
				int people = Integer.parseInt(lectureRoomsTable.getCellAt(i, 2).asText());
				ClassroomType classroomType = ClassroomType.value(classroomName);
				if (StringUtils.equals(classroomType.getClassroomName(), classroomName)) {
					lectureRooms.add(new LectureRoom(classroomType.getClassroomName(), people, classroomType.getDetailType(), classroomType.getClassType()));
				}
			}

			return lectureRooms;
		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		} catch (InterruptedException e) {
			return ExceptionUtils.rethrow(e);
		}
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
