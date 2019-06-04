package com.shouwn.oj.service.rental;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.html.*;
import com.shouwn.oj.exception.IllegalStateException;
import com.shouwn.oj.model.enums.user.UrlType;
import com.shouwn.oj.model.request.rental.RentalRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;

import org.springframework.stereotype.Service;

@Service
public class RequestRentalService {

	public HtmlPage requestRental(HtmlPage rentalPage, RentalRequest rentalRequest) {
		try {
			if (!UrlType.RENTALPAGE_URL.getUrl().equals(rentalPage.getUrl())) {
				throw new IllegalStateException("잘못된 접근 입니다.");
			}

			HtmlSelect selectStartTime = (HtmlSelect) rentalPage.getElementById("fv시설대여_ddlFrTm");
			HtmlOption timeOption = selectStartTime.getOptionByValue(rentalRequest.getStartTime());
			selectStartTime.setSelectedAttribute(timeOption, true);

			HtmlSelect selectEndTime = (HtmlSelect) rentalPage.getElementById("fv시설대여_ddlToTm");
			timeOption = selectEndTime.getOptionByValue(rentalRequest.getEndTime());
			selectStartTime.setSelectedAttribute(timeOption, true);

			HtmlTextArea reason = (HtmlTextArea) rentalPage.getElementById("fv시설대여_txtRentCause");
			reason.setText(rentalRequest.getReason());

			HtmlTextArea peopleList = (HtmlTextArea) rentalPage.getElementById("fv시설대여_txtEntryList");
			peopleList.setText(rentalRequest.getPeopleList());

			HtmlInput phoneNumber = (HtmlInput) rentalPage.getElementById("fv시설대여_txtRentUserContact");
			phoneNumber.setValueAttribute(rentalRequest.getPhoneNumber());

			HtmlCheckBoxInput agreeCheck = (HtmlCheckBoxInput) rentalPage.getElementById("fv시설대여_chk동의");
			agreeCheck.setChecked(true);

			HtmlAnchor request = (HtmlAnchor) rentalPage.getElementById("fv시설대여_btnSubmit");
			rentalPage = request.click();

		} catch (IOException e) {
			ExceptionUtils.rethrow(e);
		}

		return rentalPage;
	}
}
