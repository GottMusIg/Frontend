package com.gottmusig.pages;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.gottmusig.components.CharacterSearchPanel;
import com.gottmusig.components.basics.FooterPanel;
import com.gottmusig.components.basics.HeaderPanel;
import com.gottmusig.database.service.domain.character.CharacterService;
import com.gottmusig.database.service.domain.realm.RealmService;
import com.gottmusig.models.RealmLocationListModel;
import com.gottmusig.models.ServiceProxyModel;

public class GearPage extends WebPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SpringBean
	private RealmLocationListModel locationsModel;

	@SpringBean
	ServiceProxyModel<CharacterService> searchCharModel;
	
	@SpringBean
	ServiceProxyModel<RealmService> realmServiceModel;
	
	public GearPage(final PageParameters parameters) {
		super(parameters);
		
		add(new HeaderPanel("header"));
		
		add(new CharacterSearchPanel("character-search",
									 locationsModel,
									 searchCharModel,
									 realmServiceModel));
		
		add(new FooterPanel("footer"));
	}
	
}