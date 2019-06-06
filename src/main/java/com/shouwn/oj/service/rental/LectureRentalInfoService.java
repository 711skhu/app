package com.shouwn.oj.service.rental;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.shouwn.oj.exception.IllegalStateException;
import com.shouwn.oj.exception.InvalidParameterException;
import com.shouwn.oj.model.enums.rental.ClassroomType;
import com.shouwn.oj.model.enums.user.UrlType;
import com.shouwn.oj.model.response.rental.LectureRentalInfo;
import com.shouwn.oj.model.response.rental.RentalDate;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.springframework.stereotype.Service;

@Service
public class LectureRentalInfoService {

	public HtmlPage selectClassRoomAndRentalDate(HtmlPage rentalPage, String classroomNumber, String rentalDate) {
		if (!UrlType.RENTALPAGE_URL.getUrl().equals(rentalPage.getUrl())) {
			throw new IllegalStateException("잘못된 접근 입니다.");
		}
		if (!rentalDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
			throw new InvalidParameterException("잘못된 날짜 형식 입니다.");
		}

		try {
			HtmlTable lectureRoomsTable = (HtmlTable) rentalPage.getElementById("gv시설목록");
			boolean classroomCheck = false;

			for (int i = 1; i < lectureRoomsTable.getRowCount(); i++) {
				if (lectureRoomsTable.getRow(i).getChildElementCount() == 1) {
					for (HtmlAnchor anchor : rentalPage.getAnchors()) {
						if (anchor.asText().equals("1") || anchor.asText().equals("2")) {
							rentalPage = anchor.click();
							Thread.sleep(3000);
							break;
						}
					}
				}

				if (lectureRoomsTable.getCellAt(i, 0).asText().equals(classroomNumber)) {
					classroomCheck = true;
					break;
				}
			}

			if (!classroomCheck) {
				lectureRoomsTable = (HtmlTable) rentalPage.getElementById("gv시설목록");
				for (int i = 1; i < lectureRoomsTable.getRowCount(); i++) {
					if (lectureRoomsTable.getRow(i).getChildElementCount() == 1) {
						break;
					}

					if (lectureRoomsTable.getCellAt(i, 0).asText().equals(classroomNumber)) {
						classroomCheck = true;
						break;
					}
				}
			}

			ClassroomType type = ClassroomType.valudOfClassroomName(classroomNumber);
			if (type == null) {
				throw new InvalidParameterException("존재하지 않는 강의실 입니다.");
			} else if (!classroomCheck) {
				throw new InvalidParameterException("건물이 일치하지 않는 강의실 입니다.");
			}

			for (HtmlAnchor anchor : rentalPage.getAnchors()) {
				if (StringUtils.equals(type.getButton(), anchor.getId())) {
					rentalPage = anchor.click();
					Thread.sleep(3000);
				}
			}

			HtmlInput rentalDateInput = (HtmlInput) rentalPage.getElementById("txtRentDt");
			rentalDateInput.setValueAttribute(rentalDate);

			HtmlInput inquiryButton = (HtmlInput) rentalPage.getElementById("btnList");
			rentalPage = inquiryButton.click();
			Thread.sleep(3000);

			return rentalPage;
		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		} catch (InterruptedException e) {
			return ExceptionUtils.rethrow(e);
		}
	}

	public List<LectureRentalInfo> getRentalList(HtmlPage rentalPage) {
		List<LectureRentalInfo> rentalList = new ArrayList<>();
		HtmlTable rentalListTable = (HtmlTable) rentalPage.getElementById("gv시설대여현황");
		int index = 1;

		for (int i = 1; i < rentalListTable.getRowCount(); i++) {
			if (!rentalListTable.getCellAt(i, 2).asText().equals("제한")) {
				String rentalState = rentalListTable.getCellAt(i, 0).asText();
				String rowRentalDate = rentalListTable.getCellAt(i, 1).asText();

				RentalDate rentalDate = new RentalDate(Integer.parseInt(rowRentalDate.substring(11, 13)), Integer.parseInt(rowRentalDate.substring(30, 32)) + 1, LocalDate.parse(rowRentalDate.substring(0, 10)));

				rentalList.add(new LectureRentalInfo(index++, rentalState, rentalDate));
			}
		}

		return rentalList;
	}
}
