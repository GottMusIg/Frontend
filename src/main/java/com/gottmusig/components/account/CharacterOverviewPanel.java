package com.gottmusig.components.account;

import java.util.List;
import java.util.Optional;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import com.gottmusig.GottMusIgSession;
import com.gottmusig.account.domain.api.Account;
import com.gottmusig.account.domain.api.AccountAdministration;
import com.gottmusig.character.domain.api.Character;
import com.gottmusig.models.ServiceProxyModel;
import com.gottmusig.pages.GearPage;

public class CharacterOverviewPanel extends Panel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CharacterOverviewPanel(String id, ServiceProxyModel<AccountAdministration> accountAdminModel) {
		super(id);
		
		add(new Label("username", Model.of(((GottMusIgSession) AuthenticatedWebSession.get()).getUsername())));

		//TODO DatabaseService - List instead of Iterable
		Optional<Account> account = accountAdminModel.getObject().searchAccount(((GottMusIgSession) AuthenticatedWebSession.get()).getUsername());
		if(account.isPresent()) {
			ListModel<Character> characterModel = new ListModel<>((List<Character>)account.get().getCharacters());
		
			ListView<Character> characterView = new ListView<Character>("characters", characterModel) {
	
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
	
				@Override
				protected void populateItem(ListItem<Character> item) {
					
					add(new Label("character-name", Model.of(item.getModelObject().getName())));
					
					IModel<String> specificationModel = Model.of(item.getModelObject().getClassSpecification().getName()
																 + " " 
																 + item.getModelObject().getClass().getName());
					
					add(new Label("character-specification", specificationModel));
					
					add(new Label("character-realm", Model.of(item.getModelObject().getRealm().getName())));
					
					add(new Label("character-dps", Model.of(item.getModelObject().getDPS())));
					
				}
			};
			
			add(characterView);
		}		
		add(new BookmarkablePageLink<>("add-character", GearPage.class));
	}
	
}