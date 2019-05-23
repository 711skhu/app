package com.shouwn.oj.service.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.shouwn.oj.exception.rent.RentException;
import com.shouwn.oj.exception.rent.RentListIsEmptyException;
import com.shouwn.oj.model.enums.rent.RentStateType;
import com.shouwn.oj.model.enums.user.UrlType;
import com.shouwn.oj.model.response.rent.LectureRentInfo;
import com.shouwn.oj.model.response.rent.RentDate;

import org.springframework.stereotype.Service;

@Service
public class UserRentListService {

	public List<LectureRentInfo> rentList(HtmlPage rentPage) {
		if (!UrlType.RentPageURL.getUrl().equals(rentPage.getUrl())) {
			throw new RentException("잘못된 접근 입니다.");
		}

		List<LectureRentInfo> rentList = new ArrayList<>();
		HtmlTable rentListTable = (HtmlTable) rentPage.getElementById("gv대여내역");

		for (int i = 1; i < rentListTable.getRowCount(); i++) {
			RentStateType rentStateType = RentStateType.valueOf(rentListTable.getCellAt(i, 0).asText());
			String lectureCode = rentListTable.getCellAt(i, 1).asText().substring(1, 5);
			String rowRentDate = rentListTable.getCellAt(i, 2).asText();

			RentDate rentDate = new RentDate(rowRentDate.charAt(13) - '0', (rowRentDate.charAt(20) - '0') + 1, LocalDate.parse(rowRentDate.substring(0, 10)));

			rentList.add(new LectureRentInfo(rentDate, rentStateType, lectureCode));
		}

		if (rentList.isEmpty()) {
			throw new RentListIsEmptyException("대여 내역이 존재하지 않습니다.");
		}

		return rentList;
	}
}
