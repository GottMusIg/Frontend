package com.gottmusig;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.gottmusig.pages.HomePage;
import com.gottmusig.pages.account.SignInPage;

/**
 * Application object for GottMusIg-Frontend.
 * 
 * @author kkalmus
 */
public class GottmusigApplication extends AuthenticatedWebApplication {
	
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	@Override
	public void init() {
		super.init();

		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
		
		getApplicationSettings().setAccessDeniedPage(HomePage.class);
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		return SignInPage.class;
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return GottMusIgSession.class;
	}
	
}