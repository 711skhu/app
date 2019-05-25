package com.shouwn.oj.service.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.shouwn.oj.exception.rent.RentException;
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
			String rentState = rentListTable.getCellAt(i, 0).asText();
			String lectureCode = rentListTable.getCellAt(i, 1).asText();
			int endIndex = lectureCode.indexOf(")");
			lectureCode = lectureCode.substring(1, endIndex);
			String rowRentDate = rentListTable.getCellAt(i, 2).asText();

			RentDate rentDate = new RentDate(Integer.parseInt(rowRentDate.substring(12, 14)), Integer.parseInt(rowRentDate.substring(19, 21)) + 1, LocalDate.parse(rowRentDate.substring(0, 10)));

			rentList.add(new LectureRentInfo(rentDate, rentState, lectureCode));
		}

		return rentList;
	}
}
