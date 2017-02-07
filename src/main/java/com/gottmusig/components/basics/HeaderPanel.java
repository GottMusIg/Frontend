package com.gottmusig.components.basics;

import org.apache.wicket.markup.html.panel.Panel;

import com.gottmusig.GottmusigApplication;

/**
 * Default header for the {@link GottmusigApplication}.
 * 
 * @author kkalmus
 */
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