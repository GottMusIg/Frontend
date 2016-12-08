package com.gottmusig.component;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

import com.gottmusig.page.AboutPage;
import com.gottmusig.page.HomePage;

public class NavigationPanel extends Panel {

	public NavigationPanel(String id) {
		super(id);

		add(new BookmarkablePageLink<>("home", HomePage.class));
		add(new BookmarkablePageLink<>("about", AboutPage.class));
	}

	
}
