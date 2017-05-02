package com.gottmusig.components;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import com.gottmusig.components.character.CharacterGearPanel;
import com.gottmusig.database.service.domain.character.Character;
import com.gottmusig.database.service.domain.character.CharacterService;
import com.gottmusig.database.service.domain.realm.Realm;
import com.gottmusig.database.service.domain.realm.RealmService;
import com.gottmusig.database.service.domain.realm.jpa.Location;
import com.gottmusig.models.RealmLocationListModel;
import com.gottmusig.models.ServiceProxyModel;

/**
 * These panel is for searching {@link Character}
 * 
 * @author kkalmus
 */
public class CharacterSearchPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IModel<List<Location>> locationsListModel;

	public CharacterSearchPanel(String id,
								RealmLocationListModel locationsListModel,
								final ServiceProxyModel<CharacterService> searchCharModel,
								final ServiceProxyModel<RealmService> realmService) {
		super(id, Model.of(new CharacterSearchFormData()));
		this.locationsListModel = locationsListModel;
		
		final boolean isSignedIn = AuthenticatedWebSession.get().isSignedIn();
		
		final IModel<CharacterSearchFormData> formDataModel = new CompoundPropertyModel<>((CharacterSearchFormData)getDefaultModelObject());
		
		final IModel<List<String>> realmListModel = new ListModel<String>();
		realmListModel.setObject(realmService.getObject()
											 .getAllRealms(locationsListModel.getObject()
													 						 .iterator()
													 						 .next())
											 .stream()
											 .map(Realm::getName)
											 .collect(Collectors.toList()));
		
		final Label responseText = new Label("blizz");
		responseText.setOutputMarkupId(true);
		
		final FeedbackPanel feedbackpanel = new FeedbackPanel("feedbackpanel");
		feedbackpanel.setOutputMarkupId(true);
		
		final IModel<Character> characterModel = Model.of();
		
		final CharacterGearPanel gearPanel = new CharacterGearPanel("character-gear-panel", characterModel);
		gearPanel.setOutputMarkupId(true);
		gearPanel.setOutputMarkupPlaceholderTag(true);
		gearPanel.setVisible(false);
		
		Form<CharacterSearchFormData> searchForm = new Form<>("character-search-form", formDataModel);
		
		final DropDownChoice<String> realms = new DropDownChoice<String>("realm", realmListModel);
		realms.setOutputMarkupId(true);
		
		DropDownChoice<Location> location = new DropDownChoice<>("location", locationsListModel);
		location.add(new AjaxFormComponentUpdatingBehavior("change") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void onUpdate(AjaxRequestTarget target) {
				realmListModel.setObject(realmService.getObject()
													 .getAllRealms(formDataModel.getObject()
															 					.getLocation())
													 .stream()
													 .map(Realm::getName)
													 .collect(Collectors.toList()));
				target.add(realms);
			}
			
		});
		
		TextField<String> characterName = new TextField<>("name");

		AjaxButton addCharacterButton = new AjaxButton("add-character-button") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				target.add(responseText.setDefaultModel(Model.of("Character was added to your account!")));
				
			}
			
		};
		addCharacterButton.setVisible(isSignedIn);
		
		AjaxButton submit = new AjaxButton("search") {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
		      
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				super.onSubmit(target, form);
				Optional<Character> restResult = searchCharModel.getObject()
																.searchCharacter(formDataModel.getObject()
																							  .getRealm(),
																				 formDataModel.getObject()
																				 			  .getName());
				if(restResult.isPresent()) {
					characterModel.setObject(restResult.get());
					gearPanel.showGear(characterModel);
					gearPanel.setVisible(true);
					Session.get().getFeedbackMessages().clear();
		        } else {
		        	characterModel.setObject(null);
		        	gearPanel.setVisible(false);
		        	error("Character not found. ");
		        }
		        target.add(gearPanel);
		        target.add(responseText);
		        target.add(feedbackpanel);
		      }
		      
		    };
		submit.add(new AjaxFormComponentUpdatingBehavior("click") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void onUpdate(AjaxRequestTarget target) {
				target.add(responseText);
			}
		});
		
		searchForm.add(location);
		searchForm.add(realms);
		searchForm.add(characterName);
		searchForm.add(submit);
		searchForm.add(addCharacterButton);
		
		add(feedbackpanel);
		add(searchForm);
		add(responseText);
		add(gearPanel);
		
	}
	
	/**
	 * Default data object for {@link CharacterSearchPanel}
	 * 
	 * @author kkalmus
	 */
	private static class CharacterSearchFormData implements Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private Location location;
		private String realm;
		private String name;

		public Location getLocation() {
			return location;
		}
		public void setLocation(Location location) {
			this.location = location;
		}
		
		public String getRealm() {
			return realm;
		}
		@SuppressWarnings("unused")
		public void setRealm(String realm) {
			this.realm = realm;
		}
		
		public String getName() {
			return name;
		}
		@SuppressWarnings("unused")
		public void setName(String name) {
			this.name = name;
		}
		
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		CharacterSearchFormData formData = (CharacterSearchFormData) getDefaultModelObject();
		Location location = locationsListModel.getObject().iterator().next();
		formData.setLocation(location);
		setDefaultModelObject(formData);
	}
	
}