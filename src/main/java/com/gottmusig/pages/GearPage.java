package com.gottmusig.pages;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.gottmusig.components.CharacterSearchPanel;
import com.gottmusig.components.basics.FooterPanel;
import com.gottmusig.components.basics.HeaderPanel;
import com.gottmusig.configurations.ApplicationConfiguration;
import com.gottmusig.models.RealmLocationListModel;
import com.gottmusig.models.ServiceProxyModel;
import com.gottmusig.searchcharacter.domain.api.SearchCharacter;

public class GearPage extends WebPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GearPage(final PageParameters parameters) {
		super(parameters);
		
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		RealmLocationListModel locationsModel = context.getBean(RealmLocationListModel.class);
		ServiceProxyModel<SearchCharacter> searchCharModel = context.getBean(ServiceProxyModel.class);
		
		add(new HeaderPanel("header"));
		
		add(new CharacterSearchPanel("character-search",
									 locationsModel,
									 searchCharModel));
		
		add(new FooterPanel("footer"));
	}
	
}