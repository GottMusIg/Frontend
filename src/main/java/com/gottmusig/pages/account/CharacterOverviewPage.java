package com.gottmusig.pages.account;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.gottmusig.account.domain.api.AccountAdministration;
import com.gottmusig.components.account.CharacterOverviewPanel;
import com.gottmusig.components.basics.FooterPanel;
import com.gottmusig.components.basics.HeaderPanel;
import com.gottmusig.models.ServiceProxyModel;

// not in use yet @AuthorizeInstantiation("SIGNED_IN")
public class CharacterOverviewPage extends WebPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SpringBean
	private ServiceProxyModel<AccountAdministration> accountAdminModel;
	
	public CharacterOverviewPage(final PageParameters parameters) {
		super(parameters);
		
		add(new HeaderPanel("header"));
		add(new CharacterOverviewPanel("character-overview", accountAdminModel));
		add(new FooterPanel("footer"));
	}
	
}