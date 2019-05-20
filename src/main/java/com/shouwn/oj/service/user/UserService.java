package com.shouwn.oj.service.user;

import java.io.IOException;
import javax.servlet.http.HttpSession;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.shouwn.oj.model.UrlSetting;
import com.shouwn.oj.model.request.user.UserLoginRequest;
import com.shouwn.oj.service.rent.ConnectToRentPageService;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final ConnectToRentPageService connectToRentPageService;

	public UserService(ConnectToRentPageService connectToRentPageService) {
		this.connectToRentPageService = connectToRentPageService;
	}

	public boolean login(UserLoginRequest loginRequest, HttpSession session) {
		try {
			session.setAttribute("htmlPage", null);

			UrlSetting urlSetting = new UrlSetting();
			final WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER);
			webClient.getOptions().setUseInsecureSSL(true);
			webClient.getOptions().setJavaScriptEnabled(true);
			webClient.getOptions().setCssEnabled(false);

			HtmlPage loginPage = webClient.getPage(urlSetting.getLoginPageUrl());
			HtmlForm loginForm = loginPage.getFormByName("");
			loginForm.getInputByName("txtID").setValueAttribute(loginRequest.getStudentNumber());
			loginForm.getInputByName("txtPW").setValueAttribute(loginRequest.getPassword());

			HtmlPage mainPage = loginForm.getInputByName("ibtnLogin").click();
			session.setAttribute("htmlPage", mainPage);

			if (urlSetting.getMainPageUrl().equals(mainPage.getUrl())) {
				return true;
			} else {
				return false;
			}

		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		}
	}
}
