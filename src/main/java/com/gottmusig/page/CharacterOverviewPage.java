package com.gottmusig.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.gottmusig.component.CharacterOverviewPanel;
import com.gottmusig.component.FooterPanel;
import com.gottmusig.component.HeaderPanel;

public class CharacterOverviewPage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CharacterOverviewPage(final PageParameters parameters) {
		super(parameters);
		
		add(new HeaderPanel("header"));
		add(new CharacterOverviewPanel("character-overview"));
		add(new FooterPanel("footer"));
	}
	
}