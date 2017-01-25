package com.gottmusig.pages.statics;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.gottmusig.components.basics.FooterPanel;
import com.gottmusig.components.basics.HeaderPanel;

public class AboutPage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AboutPage(final PageParameters parameters) {
		super(parameters);
		
		add(new HeaderPanel("header"));
		add(new FooterPanel("footer"));
		
	}
	
}