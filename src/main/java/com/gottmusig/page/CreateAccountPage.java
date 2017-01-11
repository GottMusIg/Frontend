package com.gottmusig.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.gottmusig.component.CreateAccountPanel;
import com.gottmusig.component.FooterPanel;
import com.gottmusig.component.HeaderPanel;

public class CreateAccountPage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateAccountPage(final PageParameters parameters) {
		super(parameters);
		
		add(new HeaderPanel("header"));
		add(new CreateAccountPanel("create-account"));
		add(new FooterPanel("footer"));
	}
	
}