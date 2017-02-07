package com.gottmusig.components.account;

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

/**
 * These panel shows all {@link Character} of an {@link Account}.
 * 
 * @author kkalmus
 */
public class CharacterOverviewPanel extends Panel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CharacterOverviewPanel(String id,
								  ServiceProxyModel<AccountAdministration> accountAdminModel) {
		super(id);
		
		Model<String> usernameModel = Model.of(((GottMusIgSession) AuthenticatedWebSession.get()).getUsername());
		
		add(new Label("username", usernameModel));

		Optional<Account> account = accountAdminModel.getObject().searchAccount(usernameModel.getObject());
		
		if(account.isPresent()) {
			ListModel<Character> characterModel = new ListModel<>(account.get().getCharacters());
			
			ListView<Character> characterView = new ListView<Character>("characters", characterModel) {
	
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
	
				@Override
				protected void populateItem(ListItem<Character> item) {
					
					item.add(new Label("character-name", Model.of(item.getModelObject().getName())));
					
					IModel<String> specificationModel = Model.of(item.getModelObject().getClassSpecification().getName()
																 + " " 
																 + item.getModelObject().getClassSpecification().getWOWClass().getName());
					
					item.add(new Label("character-specification", specificationModel));
					
					item.add(new Label("character-realm", Model.of(item.getModelObject().getRealm().getName())));
					
					item.add(new Label("character-dps", Model.of(item.getModelObject().getDPS())));
					
				}
			};
			
			add(characterView);
		}		
		add(new BookmarkablePageLink<>("add-character", GearPage.class));
	}
	
}