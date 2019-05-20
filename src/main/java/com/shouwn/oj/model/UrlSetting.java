package com.shouwn.oj.model;

import java.io.IOException;
import java.net.URL;

import lombok.Getter;

@Getter
public class UrlSetting {
	final URL forestBaseUrl = new URL("https://forest.skhu.ac.kr");
	final URL loginPageUrl = new URL(forestBaseUrl + "/Gate/UniLogin.aspx");
	final URL mainPageUrl = new URL(forestBaseUrl + "/Gate/UniMyMain.aspx");
	final URL rentPageUrl = new URL(forestBaseUrl + "/Gate/SAM/Lesson/G/SSEG20P.aspx?&maincd=O&systemcd=S&seq=100");

	public UrlSetting() throws IOException {
	}
}
