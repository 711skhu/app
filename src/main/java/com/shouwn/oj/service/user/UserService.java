package com.shouwn.oj.service.user;

import java.io.IOException;
import java.net.URL;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.shouwn.oj.model.request.user.UserLoginRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	public boolean login(UserLoginRequest loginRequest) {
		try {
			final URL forestBaseUrl = new URL("https://forest.skhu.ac.kr");
			final URL loginPageUrl = new URL(forestBaseUrl + "/Gate/UniLogin.aspx");
			final URL mainPageUrl = new URL(forestBaseUrl + "/Gate/UniMyMain.aspx");

			WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER);
			webClient.getOptions().setUseInsecureSSL(true);

			HtmlPage loginPage = webClient.getPage(loginPageUrl);
			HtmlForm loginForm = loginPage.getFormByName("");
			loginForm.getInputByName("txtID").setValueAttribute(loginRequest.getStudentNumber());
			loginForm.getInputByName("txtPW").setValueAttribute(loginRequest.getPassword());

			HtmlPage mainPage = loginForm.getInputByName("ibtnLogin").click();
			if (mainPageUrl.equals(mainPage.getBaseURL())) return true;
			else return false;

		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		}
	}
}
