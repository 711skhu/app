package com.shouwn.oj.service.rent;

import java.io.IOException;
import javax.servlet.http.HttpSession;

import com.gargoylesoftware.htmlunit.html.*;
import com.shouwn.oj.model.UrlSetting;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.springframework.stereotype.Service;

@Service
public class ConnectToRentPageService {

	public int connectToRentPage(HttpSession session) {
		try {
			UrlSetting urlSetting = new UrlSetting();
			HtmlPage uniMyMainPage = (HtmlPage) session.getAttribute("htmlPage");
			if (!urlSetting.getMainPageUrl().equals(uniMyMainPage.getUrl())) {
				return -1;
			}

			HtmlPage mainPage = getPage(uniMyMainPage);
			getLeftFrameBody(mainPage);
			HtmlPage contentPage = getPage(mainPage);

			session.setAttribute("htmlPage", contentPage);
			HtmlPage rentPage = (HtmlPage) session.getAttribute("htmlPage");
			if (urlSetting.getRentPageUrl().equals(rentPage.getUrl())) {
				return 1;
			} else {
				return 0;
			}

		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		}
	}

	static HtmlPage getPage(HtmlPage mainPage) {
		HtmlElement frameset = mainPage.getBody();
		DomNode frameNode = frameset.getLastChild().getPreviousSibling();
		HtmlFrame frame = (HtmlFrame) frameNode;
		HtmlPage page = (HtmlPage) frame.getEnclosedPage();

		return page;
	}

	static void getLeftFrameBody(HtmlPage mainPage) throws IOException {
		HtmlElement mainFrameset = mainPage.getBody();
		DomNode leftFrameNode = mainFrameset.getFirstChild().getNextSibling();
		HtmlFrame leftFrame = (HtmlFrame) leftFrameNode;
		HtmlPage leftFrameBody = (HtmlPage) leftFrame.getEnclosedPage();
		clickMenu(leftFrameBody);

		return;
	}

	static void clickMenu(HtmlPage leftPage) throws IOException {
		HtmlAnchor webServiceAnchor = leftPage.getAnchorByText("웹서비스");
		leftPage = webServiceAnchor.click();
		HtmlAnchor rentPageAnchor = leftPage.getAnchorByText("[N]시설물대여 신청");
		rentPageAnchor.click();

		return;
	}

}
