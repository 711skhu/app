package com.shouwn.oj.service.user;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.shouwn.oj.exception.IllegalStateException;
import com.shouwn.oj.model.enums.user.UrlType;
import com.shouwn.oj.model.response.rental.RentalDate;
import com.shouwn.oj.model.response.user.UserLectureRentalInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.springframework.stereotype.Service;

@Service
public class UserRentalListService {

	public List<UserLectureRentalInfo> rentalList(HtmlPage rentalPage) {
		try {
			if (!UrlType.RENTALPAGE_URL.getUrl().equals(rentalPage.getUrl())) {
				throw new IllegalStateException("잘못된 접근 입니다.");

			}

			List<UserLectureRentalInfo> rentalList = new ArrayList<>();
			HtmlTable rentalListTable = (HtmlTable) rentalPage.getElementById("gv대여내역");

			int pageCount = 1;
			int index = 0;
			for (int i = 1; i < rentalListTable.getRowCount(); i++) {
				if (rentalListTable.getRow(i).getChildElementCount() == 1) {
					if (rentalListTable.getRow(i).getTextContent().contains(Integer.toString(pageCount + 1))) {
						HtmlAnchor pagination = rentalPage.getAnchorByHref("javascript:__doPostBack('gv대여내역','Page$" + (++pageCount) + "')");
						rentalPage = pagination.click();
						Thread.sleep(3000);
						rentalListTable = (HtmlTable) rentalPage.getElementById("gv대여내역");
						i = 0;
						continue;
					} else {
						break;
					}
				}

				String rentalState = rentalListTable.getCellAt(i, 0).asText();
				String lectureCode = rentalListTable.getCellAt(i, 1).asText();
				int endIndex = lectureCode.indexOf(")");
				lectureCode = lectureCode.substring(1, endIndex);
				String rowRentalDate = rentalListTable.getCellAt(i, 2).asText();

				RentalDate rentalDate = new RentalDate(Integer.parseInt(rowRentalDate.substring(12, 14)), Integer.parseInt(rowRentalDate.substring(19, 21)) + 1, LocalDate.parse(rowRentalDate.substring(0, 10)));

				rentalList.add(new UserLectureRentalInfo(++index, rentalDate, rentalState, lectureCode, rentalState.equals("승인") ? false : true));
			}

			return rentalList;
		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		} catch (InterruptedException e) {
			return ExceptionUtils.rethrow(e);
		}
	}

	public HtmlPage rentalCancel(HtmlPage rentalPage, int idx) {
		try {
			for (HtmlAnchor anchor : rentalPage.getAnchors()) {
				if (StringUtils.equals(("javascript:__doPostBack('gv대여내역','Page$" + (idx / 10 + 1) + "')"), anchor.getHrefAttribute())) {
					HtmlAnchor pagination = rentalPage.getAnchorByHref("javascript:__doPostBack('gv대여내역','Page$" + (idx / 10 + 1) + "')");
					rentalPage = pagination.click();
					Thread.sleep(3000);
				}
			}

			HtmlAnchor cancel = rentalPage.getAnchorByHref("javascript:__doPostBack('gv대여내역$ctl0" + (idx % 10 + 1) + "$btnDelete','')");
			cancel.click();
			Thread.sleep(3000);

			return rentalPage;
		} catch (InterruptedException e) {
			return ExceptionUtils.rethrow(e);
		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		}
	}
}

