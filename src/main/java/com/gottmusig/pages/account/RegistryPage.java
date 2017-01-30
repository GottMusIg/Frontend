package com.gottmusig.pages.account;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.gottmusig.account.domain.api.AccountAdministration;
import com.gottmusig.components.account.RegistryPanel;
import com.gottmusig.components.basics.FooterPanel;
import com.gottmusig.components.basics.HeaderPanel;
import com.gottmusig.models.ServiceProxyModel;

public class RegistryPage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SpringBean
	private ServiceProxyModel<AccountAdministration> accountAdminModel;
	
	public RegistryPage(final PageParameters parameters) {
		super(parameters);
		
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