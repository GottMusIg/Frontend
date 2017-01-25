package com.gottmusig.pages.account;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.gottmusig.components.account.SignInPanel;
import com.gottmusig.components.basics.FooterPanel;
import com.gottmusig.components.basics.HeaderPanel;

public class SignInPage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SignInPage(final PageParameters parameters) {
		super(parameters);
		
		add(new HeaderPanel("header"));
		add(new SignInPanel("sign-in"));
		add(new FooterPanel("footer"));
	}
	
}