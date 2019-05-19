package com.shouwn.oj.model;

import java.io.IOException;
import java.net.URL;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicSetting {
	private final WebClient webClient;
	private HtmlPage htmlPage;
	private final URL forestBaseUrl = new URL("https://forest.skhu.ac.kr");
	private final URL loginPageUrl = new URL(forestBaseUrl + "/Gate/UniLogin.aspx");
	private final URL mainPageUrl = new URL(forestBaseUrl + "/Gate/UniMyMain.aspx");

	public BasicSetting() throws IOException {
		webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER);
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setCssEnabled(false);
	}
}
