package com.shouwn.oj.model.page;

import java.util.List;
import java.util.stream.Collectors;

import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import lombok.Getter;

@Getter
public class LectureTable {

	private final List<Lecture> lectures;

	public LectureTable(HtmlTable table) {
		lectures = LectureTable.getLectureListFromHtmlTable(table);
	}

	private static List<Lecture> getLectureListFromHtmlTable(HtmlTable table) {
		return table.getRows().stream()
				.filter(row -> row.getChildElementCount() != 1)
				.map(LectureTable::makeLectureFromRow)
				.collect(Collectors.toList());
	}

	private static Lecture makeLectureFromRow(HtmlTableRow row) {
		String name = row.getCell(0).asText();
		int people = Integer.parseInt(row.getCell(2).asText());

		return Lecture.builder()
				.name(name)
				.people(people)
				.build();
	}
}
