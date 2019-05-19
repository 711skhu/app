package com.shouwn.oj.service.rent;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.html.*;
import com.shouwn.oj.model.BasicSetting;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.springframework.stereotype.Service;

@Service
public class ConnectToRentPageService {

	static void connectToRentPage() {
		try {
			BasicSetting basicSetting = new BasicSetting();

			HtmlPage mainPage = getMainFrameBody(basicSetting.getHtmlPage());
			getLeftFrameBody(mainPage);
//			getMainFrameBody(mainPage);

			HtmlElement mainFrameset = mainPage.getBody();
			DomNode contentFrameNode = mainFrameset.getLastChild().getPreviousSibling();
			HtmlFrame contentFrame = (HtmlFrame) contentFrameNode;
//			FrameWindow fw01 = (FrameWindow) fr01.getEnclosedWindow();
			HtmlPage contentPage = (HtmlPage) contentFrame.getEnclosedPage();
//			System.out.println("p01 " + p01.asXml());

			basicSetting.setHtmlPage(contentPage);

		} catch (IOException e) {
			ExceptionUtils.rethrow(e);
		}
	}

	static HtmlPage getMainFrameBody(HtmlPage uniMyMainPage) {
		HtmlElement frmFrameset = uniMyMainPage.getBody();
		DomNode mainFrameNode = frmFrameset.getLastChild().getPreviousSibling();
		HtmlFrame mainFrame = (HtmlFrame) mainFrameNode;
//			FrameWindow fw0 = (FrameWindow) contentFrame.getEnclosedWindow();
		HtmlPage mainFrameBody = (HtmlPage) mainFrame.getEnclosedPage();
//			System.out.println("p0 "+p0.asXml());

		return mainFrameBody;
	}

	static void getLeftFrameBody(HtmlPage mainPage) throws IOException {
		HtmlElement mainFrameset = mainPage.getBody();
		DomNode leftFrameNode = mainFrameset.getFirstChild().getNextSibling();
		HtmlFrame leftFrame = (HtmlFrame) leftFrameNode;
//		FrameWindow fw2 = (FrameWindow) fr2.getEnclosedWindow();
		HtmlPage leftFrameBody = (HtmlPage) leftFrame.getEnclosedPage();
//		System.out.println("p2 " + p2.asXml());

		clickMenu(leftFrameBody);

		return;
	}

	static void clickMenu(HtmlPage leftPage) throws IOException {
		HtmlAnchor webServiceAnchor = leftPage.getAnchorByText("웹서비스");
		leftPage = webServiceAnchor.click();
//		System.out.println("p22 " + p2.asXml());
		HtmlAnchor rentPageAnchor = leftPage.getAnchorByText("[N]시설물대여 신청");
		rentPageAnchor.click();

		return;
	}

}
