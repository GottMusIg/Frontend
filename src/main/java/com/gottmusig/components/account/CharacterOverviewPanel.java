package com.gottmusig.components.account;

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
import com.gottmusig.database.service.domain.account.Account;
import com.gottmusig.database.service.domain.account.AccountService;
import com.gottmusig.database.service.domain.character.Character;
import com.gottmusig.models.ServiceProxyModel;
import com.gottmusig.pages.GearPage;

import jersey.repackaged.com.google.common.collect.Lists;

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

	private Model<Account> accountModel;
	private ListModel<Character> characterModel;
	
	public CharacterOverviewPanel(String id,
								  ServiceProxyModel<AccountService> accountAdminModel) {
		super(id);
		
		accountModel = Model.of(accountAdminModel.getObject()
												 .searchAccount(((GottMusIgSession) AuthenticatedWebSession.get()).getAccount()
														 														  .getUserName())
												 .get());
		
		add(new Label("username", accountModel.getObject().getUserName()));
		
		if(accountModel != null) {
			characterModel = new ListModel<>(Lists.newArrayList(accountModel.getObject().getCharacters()));
			
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
	
	@Override
	protected void onDetach() {
		super.onDetach();
		accountModel.detach();
		characterModel.detach();
	}
	
}