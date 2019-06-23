package com.shouwn.oj.model.page;

import java.io.IOException;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

public class LectureTables implements Iterable<LectureTable> {

	private final HtmlPage page;

	private LectureTables(HtmlPage page) {
		this.page = page;
	}

	@Override
	public Iterator<LectureTable> iterator() {
		return new LectureIterator(page.getAnchors().iterator());
	}

	@Override
	public void forEach(Consumer<? super LectureTable> action) {

	}

	@Override
	public Spliterator<LectureTable> spliterator() {
		return null;
	}

	public class LectureIterator implements Iterator<LectureTable> {

		private int currentPage = 1;

		private Iterator<HtmlAnchor> htmlAnchorIterator;

		private HtmlAnchor currentAnchor;

		public LectureIterator(Iterator<HtmlAnchor> htmlAnchorIterator) {
			this.htmlAnchorIterator = htmlAnchorIterator;
		}

		@Override
		public boolean hasNext() {
			if (!htmlAnchorIterator.hasNext()) {
				return false;
			}

			currentAnchor = htmlAnchorIterator.next();

			return currentAnchor.asText().equals(String.valueOf(currentPage + 1));
		}

		@Override
		public LectureTable next() {
			try {
				currentAnchor.click();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			return new LectureTable((HtmlTable) page.getElementById("gv시설목록"));
		}
	}
}
