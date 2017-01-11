package com.gottmusig.component;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

import com.gottmusig.page.AboutPage;
import com.gottmusig.page.CharacterOverviewPage;
import com.gottmusig.page.CreateAccountPage;
import com.gottmusig.page.GearPage;
import com.gottmusig.page.HomePage;

public class NavigationPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NavigationPanel(String id) {
		super(id);

		add(new BookmarkablePageLink<>("home", HomePage.class));
		add(new BookmarkablePageLink<>("gear", GearPage.class));
		add(new BookmarkablePageLink<>("about", AboutPage.class));
		add(new BookmarkablePageLink<>("character", CharacterOverviewPage.class));
		
		add(new BookmarkablePageLink<>("create-account", CreateAccountPage.class));
	}

	
}
