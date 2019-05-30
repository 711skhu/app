package com.shouwn.oj.service.rental;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.shouwn.oj.model.enums.rental.ClassroomType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.springframework.stereotype.Service;

@Service
public class LectureRentalInfoService {

	public HtmlPage selectClassRoom(HtmlPage rentalPage, String classroomName) {
		try {
			ClassroomType type = ClassroomType.value(classroomName);
			for (HtmlAnchor anchor : rentalPage.getAnchors()) {
				if (StringUtils.equals(type.getButton(), anchor.getId())) {
					rentalPage = anchor.click();
				}
			}
			return rentalPage;
		} catch (IOException e) {
			return ExceptionUtils.rethrow(e);
		}
	}
}
