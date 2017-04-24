package com.gottmusig.pages.account;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.gottmusig.components.account.SignInPanel;
import com.gottmusig.components.basics.FooterPanel;
import com.gottmusig.components.basics.HeaderPanel;
import com.gottmusig.database.service.domain.account.AccountService;
import com.gottmusig.models.ServiceProxyModel;

public class SignInPage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SpringBean
	private ServiceProxyModel<AccountService> accountServiceModel;

	public SignInPage(final PageParameters parameters) {
		super(parameters);
		
		add(new HeaderPanel("header"));
		add(new SignInPanel("sign-in", accountServiceModel));
		add(new FooterPanel("footer"));
	}

	@Override
	public void detachModels() {
		super.detachModels();
		accountServiceModel.detach();
	}
	
}