package com.gottmusig.component;

import org.apache.wicket.markup.html.panel.Panel;

public class HeaderPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HeaderPanel(String id) {
		super(id);
		
		add(new NavigationPanel("navigation"));
	}
	
}