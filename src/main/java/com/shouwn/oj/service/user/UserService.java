package com.shouwn.oj.service.user;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.shouwn.oj.model.BasicSetting;
import com.shouwn.oj.model.request.user.UserLoginRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	public boolean login(UserLoginRequest loginRequest) {
		try {
			BasicSetting basicSetting = new BasicSetting();

			HtmlPage loginPage = basicSetting.getWebClient().getPage(basicSetting.getLoginPageUrl());
			HtmlForm loginForm = loginPage.getFormByName("");
			loginForm.getInputByName("txtID").setValueAttribute(loginRequest.getStudentNumber());
			loginForm.getInputByName("txtPW").setValueAttribute(loginRequest.getPassword());

			basicSetting.setHtmlPage(loginForm.getInputByName("ibtnLogin").click());
			if (basicSetting.getMainPageUrl().equals(basicSetting.getHtmlPage().getBaseURL())) {
				return true;
			} else {
				return false;
			}

		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		}
	}
}
