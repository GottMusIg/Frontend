package com.gottmusig.component;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

import com.gottmusig.page.GearPage;

public class CharacterOverviewPanel extends Panel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CharacterOverviewPanel(String id) {
		super(id);
		
		add(new BookmarkablePageLink<>("add-character", GearPage.class));
	}
	
}