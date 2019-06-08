package com.shouwn.oj.service.user;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.javascript.host.event.Event;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
		if (!UrlType.RENTALPAGE_URL.getUrl().equals(rentalPage.getUrl())) {
			throw new IllegalStateException("잘못된 접근 입니다.");
		}

		List<UserLectureRentalInfo> rentalList = new ArrayList<>();
		HtmlTable rentalListTable = (HtmlTable) rentalPage.getElementById("gv대여내역");
		int pageCount = 1;

		try {
			for (int i = 1; i < rentalListTable.getRowCount(); i++) {
				if (rentalListTable.getRow(i).getChildElementCount() == 1) {
					for (HtmlAnchor anchor : rentalPage.getAnchors()) {
						if (anchor.asText().equals(Integer.toString(pageCount + 1))) {
							rentalPage = anchor.click();
							Thread.sleep(3000);
							++pageCount;
							i = 1;
							rentalListTable = (HtmlTable) rentalPage.getElementById("gv대여내역");
							break;
						}
					}
					if (i != 1) {
						break;
					}
				}
				String rentalState = rentalListTable.getCellAt(i, 0).asText();
				String lectureCode = rentalListTable.getCellAt(i, 1).asText();
				int endIndex = lectureCode.indexOf(")");
				lectureCode = lectureCode.substring(1, endIndex);
				String rowRentalDate = rentalListTable.getCellAt(i, 2).asText();

				RentalDate rentalDate = new RentalDate(Integer.parseInt(rowRentalDate.substring(12, 14)), Integer.parseInt(rowRentalDate.substring(19, 21)) + 1, LocalDate.parse(rowRentalDate.substring(0, 10)));

				rentalList.add(new UserLectureRentalInfo(i, rentalDate, rentalState, lectureCode, rentalState.equals("승인") ? false : true));
			}
			return rentalList;
		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		} catch (InterruptedException e) {
			return ExceptionUtils.rethrow(e);
		}

	}


	public HtmlPage rentalCancel(HtmlPage rentalPage, int idx) {
		System.out.println("제대로 링크 타고 들어옴");
		HtmlTable rentalListTable = (HtmlTable) rentalPage.getElementById("gv대여내역");
		System.out.println("delete버튼을 정할 것이다.");
		//삭제될 수 있는 것 중에서 anchor를 받자
		//idx의 값을 받아서 해당하는 것을 삭제하기
		try {
			System.out.println("삭제하려는 시간  "+rentalListTable.getCellAt(idx,2).asText() );
			System.out.println("삭제하려는 값  "+rentalListTable.getCellAt(idx,3).asText() );
			if(rentalListTable.getCellAt(idx,3).asText().equals("삭제")){
				System.out.println("if문이 잘 받아졌다. ");
				//아래와 같은 방법으로 하면 제일 첫번째 보이는 버튼 값을 입력 받게 된다.
				/*HtmlAnchor deleteButton = (HtmlAnchor) rentalPage.getElementById("gv대여내역_ctl02_btnDelete");
				deleteButton.click();*/
				for (HtmlAnchor anchor : rentalPage.getAnchors()) {
					if (anchor.asText().equals(idx)) {
						rentalPage = anchor.click();
						Thread.sleep(3000);
						System.out.println("클릭했습니다.");
						break;
					}
				}

			}else{
				System.out.println("클릭할 수 없습니다.");
			}
			return rentalPage;
		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		} catch (InterruptedException e) {
			return ExceptionUtils.rethrow(e);
		}
	}

}
