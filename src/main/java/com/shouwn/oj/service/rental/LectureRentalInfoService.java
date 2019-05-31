package com.shouwn.oj.service.rental;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.shouwn.oj.model.enums.rental.ClassroomType;
import com.shouwn.oj.model.request.rental.RentalListRequest;
import com.shouwn.oj.model.response.rental.LectureRentalInfo;
import com.shouwn.oj.model.response.rental.RentalDate;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.springframework.stereotype.Service;

@Service
public class LectureRentalInfoService {

	public HtmlPage selectClassRoomAndRentalDate(HtmlPage rentalPage, RentalListRequest rentalListRequest) {
		try {
			ClassroomType type = ClassroomType.value(rentalListRequest.getLectureCode());
			for (HtmlAnchor anchor : rentalPage.getAnchors()) {
				if (StringUtils.equals(type.getButton(), anchor.getId())) {
					rentalPage = anchor.click();
					Thread.sleep(3000);
				}
			}

			HtmlInput rentalDateInput = (HtmlInput) rentalPage.getElementById("txtRentDt");
			rentalDateInput.setValueAttribute(rentalListRequest.getRentalDate());

			HtmlInput inquiryButton = (HtmlInput) rentalPage.getElementById("btnList");
			rentalPage = inquiryButton.click();
			Thread.sleep(3000);

			return rentalPage;
		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		}catch (InterruptedException e){
			return ExceptionUtils.rethrow(e);
		}
	}

	public List<LectureRentalInfo> getRentalList(HtmlPage rentalPage){
		List<LectureRentalInfo> rentalList = new ArrayList<>();
		HtmlTable rentalListTable = (HtmlTable) rentalPage.getElementById("gv시설대여현황");

		for (int i = 1; i < rentalListTable.getRowCount(); i++) {
			String rentalState = rentalListTable.getCellAt(i, 0).asText();
			String rowRentalDate = rentalListTable.getCellAt(i, 1).asText();

			RentalDate rentalDate = new RentalDate(Integer.parseInt(rowRentalDate.substring(11, 13)), Integer.parseInt(rowRentalDate.substring(30, 32)) + 1, LocalDate.parse(rowRentalDate.substring(0, 10)));

			rentalList.add(new LectureRentalInfo(i, rentalDate, rentalState));
		}

		return rentalList;
	}
}
