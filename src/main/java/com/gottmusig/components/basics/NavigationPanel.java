package com.gottmusig.components.basics;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

import com.gottmusig.pages.CharacterOverviewPage;
import com.gottmusig.pages.GearPage;
import com.gottmusig.pages.HomePage;
import com.gottmusig.pages.RegistryPage;
import com.gottmusig.pages.satatic.AboutPage;

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
		
		add(new BookmarkablePageLink<>("create-account", RegistryPage.class));
	}

	
}
