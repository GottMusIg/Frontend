package com.gottmusig;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;

import com.gottmusig.pages.HomePage;
import com.gottmusig.pages.account.SignInPage;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see com.gottmusig.Start#main(String[])
 */
public class GottmusigApplication extends AuthenticatedWebApplication {
	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage() {
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init() {
		super.init();

		getApplicationSettings().setAccessDeniedPage(HomePage.class);
		
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//		ctx.register(DPSDifferenceConfiguration.class);
//		ctx.refresh();
//		getComponentInitializationListeners().add(new SpringComponentInjector(this, ctx));
		
		// add your configuration here
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