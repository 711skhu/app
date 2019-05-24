package com.shouwn.oj.service.rent;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.shouwn.oj.model.enums.rent.BuildingType;
import com.shouwn.oj.model.request.rent.BuildingRequest;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

import static com.shouwn.oj.model.enums.rent.BuildingType.*;

@Service
public class LectureRoomService {
	//건물 목록 출력
	public static HtmlPage building(HtmlPage contentPage, BuildingRequest buildingName){
		List<HtmlElement> buildingList1;
		buildingList1=  contentPage.getByXPath("//table[@id='gv건물목록']/tbody/tr[@class='cssRowStyle']/td[1]");

		List<HtmlElement> buildingList2;
		buildingList2=  contentPage.getByXPath("//table[@id='gv건물목록']/tbody/tr[@class='cssAlternatingRowStyle']/td[1]");

		System.out.println("*******************************************");
		int count=0;
		for(int j=0; j<(buildingList1.size()+buildingList2.size()); j++){
			if(count%2==0){
				System.out.println(buildingList1.get(j).asText());
				++count;
			}
			else{
				--j;
				System.out.println(buildingList2.get(j).asText());
				++count;
			}
			if(count==10){
				break;
			}
		}
		try {
			clickBuilding(contentPage,buildingName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentPage;
	}

	private static void clickBuilding(HtmlPage contentPage, BuildingRequest inputName) throws IOException {

		HtmlAnchor link = null;
		List<HtmlAnchor> anchors = contentPage.getAnchors();
		for (HtmlAnchor anchor : anchors) {
			String str = anchor.getId();
			if(Objects.equals(SeungyeonHall.getBuildingName(), inputName)){
			if (anchor.getId().equals(SeungyeonHall.getBuildingButton())) {
				link = anchor;
			}}
			if(Objects.equals(IlmanHall.getBuildingName(), inputName)){
			if (anchor.getId().equals(IlmanHall.getBuildingButton())) {
				link = anchor;
			}}
			if(Objects.equals(WoldanggHall.getBuildingName(), inputName)){
			if (anchor.getId().equals(WoldanggHall.getBuildingButton())) {
				link = anchor;
			}}
			if(Objects.equals(LeechunhwanHall.getBuildingName(), inputName)){
			if (anchor.getId().equals(LeechunhwanHall.getBuildingButton())) {
				link = anchor;
			}}
			if(Objects.equals(SsechunnunHall.getBuildingName(), inputName)){
			if (anchor.getId().equals(SsechunnunHall.getBuildingButton())) {
				link = anchor;
			}}
			if(Objects.equals(SungmieleHall.getBuildingName(), inputName)){
			if (anchor.getId().equals(SungmieleHall.getBuildingButton())) {
				link = anchor;
			}}
			if(Objects.equals(MieleHall.getBuildingName(), inputName)){
			if (anchor.getId().equals(MieleHall.getBuildingButton())) {
				link = anchor;
			}}
		}
		link.click();
		return;
	}
}
