package com.gottmusig.pages;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.gottmusig.components.CharacterSearchPanel;
import com.gottmusig.components.basics.FooterPanel;
import com.gottmusig.components.basics.HeaderPanel;
import com.gottmusig.models.RealmLocationListModel;
import com.gottmusig.models.ServiceProxyModel;
import com.gottmusig.searchcharacter.domain.api.SearchCharacter;

public class GearPage extends WebPage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SpringBean
	private RealmLocationListModel locationsModel;

	@SpringBean
	ServiceProxyModel<SearchCharacter> searchCharModel;
	
	public GearPage(final PageParameters parameters) {
		super(parameters);
		
		add(new HeaderPanel("header"));
		
		add(new CharacterSearchPanel("character-search",
									 locationsModel,
									 searchCharModel));
		
		add(new FooterPanel("footer"));
	}
	
}