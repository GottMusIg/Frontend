package com.gottmusig.page;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gottmusig.component.CharacterSearchPanel;
import com.gottmusig.component.FooterPanel;
import com.gottmusig.component.NavigationPanel;
import com.gottmusig.configuration.ApplicationConfiguration;
import com.gottmusig.model.RealmLocationListModel;
import com.gottmusig.model.ServiceProxyModel;
import com.gottmusig.searchcharacter.domain.api.SearchCharacter;

public class GearPage extends WebPage implements Serializable {

	public GearPage(final PageParameters parameters) {
		super(parameters);
		
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		RealmLocationListModel locationsModel = context.getBean(RealmLocationListModel.class);
		ServiceProxyModel<SearchCharacter> searchCharModel = context.getBean(ServiceProxyModel.class);
		
		add(new NavigationPanel("navigation"));
		
		add(new CharacterSearchPanel("character-search",
									 locationsModel,
									 searchCharModel));
		
		add(new FooterPanel("footer"));
		
	}
	
}
