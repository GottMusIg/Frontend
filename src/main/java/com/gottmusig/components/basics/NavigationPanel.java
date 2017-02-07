package com.gottmusig.components.basics;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import com.gottmusig.pages.GearPage;
import com.gottmusig.pages.HomePage;
import com.gottmusig.pages.account.AccountFeedbackPage;
import com.gottmusig.pages.account.CharacterOverviewPage;
import com.gottmusig.pages.statics.AboutPage;

/**
 * Default navigation for the {@link HeaderPanel}
 * 
 * @author kkalmus
 */
public class NavigationPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NavigationPanel(String id) {
		super(id);

		boolean isSignedIn = AuthenticatedWebSession.get().isSignedIn();

		BookmarkablePageLink<CharacterOverviewPage> charactersPage = new BookmarkablePageLink<>("character",
																								CharacterOverviewPage.class);
		Link<String> signOutLink = new Link<String>("sign-out") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
		
			@Override
			public void onClick() {
				AuthenticatedWebSession.get().signOut();
				throw new RestartResponseAtInterceptPageException(AccountFeedbackPage.class);
			}
			
		};


		SignInLinkPanel signInLinkPanel = new SignInLinkPanel("sign-in-panel");
		
		charactersPage.setVisible(isSignedIn);
		signOutLink.setVisible(isSignedIn);
		signInLinkPanel.setVisible(!isSignedIn);
		
		add(new BookmarkablePageLink<>("home", HomePage.class));
		add(new BookmarkablePageLink<>("gear", GearPage.class));
		add(new BookmarkablePageLink<>("about", AboutPage.class));
		add(charactersPage);
		add(signOutLink);
		add(signInLinkPanel);
	}
	
}