package com.gottmusig.pages.account;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.gottmusig.account.domain.api.AccountAdministration;
import com.gottmusig.components.account.RegistryPanel;
import com.gottmusig.components.basics.FooterPanel;
import com.gottmusig.components.basics.HeaderPanel;
import com.gottmusig.configurations.ApplicationConfiguration;
import com.gottmusig.models.AccountServiceProxyModel;

public class RegistryPage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	AccountServiceProxyModel<AccountAdministration> accountAdminModel;
	
	public RegistryPage(final PageParameters parameters) {
		super(parameters);
		
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		accountAdminModel = context.getBean(AccountServiceProxyModel.class);
		
		add(new HeaderPanel("header"));
		add(new RegistryPanel("create-account", accountAdminModel));
		add(new FooterPanel("footer"));
	}
	
	@Override
	public void detachModels() {
		super.detachModels();
		accountAdminModel.detach();
	}
	
}