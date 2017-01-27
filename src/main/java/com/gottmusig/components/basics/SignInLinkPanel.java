package com.gottmusig.components.basics;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

import com.gottmusig.pages.account.SignInPage;

public class SignInLinkPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SignInLinkPanel(String id) {
		super(id);

		SignInDropDownPanel signInDropDownPanel = new SignInDropDownPanel("sign-in-drop-down");

		add(new BookmarkablePageLink<>("sign-in", SignInPage.class));
		add(signInDropDownPanel);
	}

}