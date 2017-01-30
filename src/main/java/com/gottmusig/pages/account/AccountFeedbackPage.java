package com.gottmusig.pages.account;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.gottmusig.GottMusIgSession;
import com.gottmusig.components.basics.FooterPanel;
import com.gottmusig.components.basics.HeaderPanel;
import com.gottmusig.pages.HomePage;

public class AccountFeedbackPage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountFeedbackPage(final PageParameters parameters) {
		super(parameters);
		
		final GottMusIgSession session = (GottMusIgSession) AuthenticatedWebSession.get();
		
		IModel<String> feedbackModel = new AbstractReadOnlyModel<String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				StringBuffer feedback = new StringBuffer("You are succesfully ");
				if(session.isSignedIn()) {
					feedback.append("signed in " + session.getUsername() + ".");
				} else {
					feedback.append("signed out.");
				}
				return feedback.toString();
			}
		};
        
        Link<CharacterOverviewPage> charOverview = new Link<CharacterOverviewPage>("character-overview-button") {
        	
        	/**
        	 * 
        	 */
        	private static final long serialVersionUID = 1L;
        
        	@Override
        	public void onClick() {
        		throw new RestartResponseAtInterceptPageException(CharacterOverviewPage.class);
        	}
        	
        };
        charOverview.setVisible(session.isSignedIn());
        
        add(new HeaderPanel("header"));
        add(new Label("feedback", feedbackModel));
        add(new Link<HomePage>("home-button") {
        	
        	/**
        	 * 
        	 */
        	private static final long serialVersionUID = 1L;
        	
        	@Override
        	public void onClick() {
        		throw new RestartResponseAtInterceptPageException(HomePage.class);
        	}
        	
        });
        add(charOverview);
        add(new FooterPanel("footer"));
    }
	
}