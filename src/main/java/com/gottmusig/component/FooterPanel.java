package com.gottmusig.component;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

import com.gottmusig.page.AboutPage;

public class FooterPanel extends Panel {

	public FooterPanel(String id) {
		super(id);

		add(new BookmarkablePageLink<Object>("about-leon", AboutPage.class) {
			protected CharSequence getURL() {
				return super.getURL() + "#leon";
			};
		});
		add(new BookmarkablePageLink<Object>("about-kamil", AboutPage.class) {
			@Override
			protected CharSequence getURL() {
				return super.getURL() + "#kamil";
			}
		});
		add(new BookmarkablePageLink<Object>("about-chris", AboutPage.class) {
			@Override
			protected CharSequence getURL() {
				return super.getURL() + "#christoph";
			}
		});
	}

}
