package com.gottmusig.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.gottmusig.components.RegistryPanel;
import com.gottmusig.components.basics.FooterPanel;
import com.gottmusig.components.basics.HeaderPanel;

public class RegistryPage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegistryPage(final PageParameters parameters) {
		super(parameters);
		
		add(new HeaderPanel("header"));
		add(new RegistryPanel("create-account"));
		add(new FooterPanel("footer"));
	}
	
}