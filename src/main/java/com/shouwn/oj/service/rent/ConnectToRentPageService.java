package com.shouwn.oj.service.rent;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.html.*;
import com.shouwn.oj.exception.rent.RentException;
import com.shouwn.oj.model.enums.user.UrlType;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.springframework.stereotype.Service;

@Service
public class ConnectToRentPageService {

	public HtmlPage connectToRentPage(HtmlPage uniMyMainPage) {
		try {
			if (!UrlType.MainPageURL.getUrl().equals(uniMyMainPage.getUrl())) {
				throw new RentException("잘못된 접근 입니다.");
			}

			HtmlPage mainPage = getPage(uniMyMainPage);
			getLeftFrameBody(mainPage);
			HtmlPage contentPage = getPage(mainPage);

			if (UrlType.RentPageURL.getUrl().equals(contentPage.getUrl())) {
				return contentPage;
			} else {
				throw new RentException("연결 실패");
			}

		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		}
	}

	private HtmlPage getPage(HtmlPage mainPage) {
		HtmlElement frameset = mainPage.getBody();
		DomNode frameNode = frameset.getLastChild().getPreviousSibling();
		HtmlFrame frame = (HtmlFrame) frameNode;
		HtmlPage page = (HtmlPage) frame.getEnclosedPage();

		return page;
	}

	private void getLeftFrameBody(HtmlPage mainPage) throws IOException {
		HtmlElement mainFrameset = mainPage.getBody();
		DomNode leftFrameNode = mainFrameset.getFirstChild().getNextSibling();
		HtmlFrame leftFrame = (HtmlFrame) leftFrameNode;
		HtmlPage leftFrameBody = (HtmlPage) leftFrame.getEnclosedPage();
		clickMenu(leftFrameBody);

		return;
	}

	private void clickMenu(HtmlPage leftPage) throws IOException {
		HtmlAnchor webServiceAnchor = leftPage.getAnchorByText("웹서비스");
		leftPage = webServiceAnchor.click();
		HtmlAnchor rentPageAnchor = leftPage.getAnchorByText("[N]시설물대여 신청");
		rentPageAnchor.click();

		return;
	}

}
