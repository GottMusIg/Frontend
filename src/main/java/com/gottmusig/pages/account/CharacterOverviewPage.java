package com.gottmusig.pages.account;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.gottmusig.components.account.CharacterOverviewPanel;
import com.gottmusig.components.basics.FooterPanel;
import com.gottmusig.components.basics.HeaderPanel;

// not in use yet @AuthorizeInstantiation("SIGNED_IN")
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