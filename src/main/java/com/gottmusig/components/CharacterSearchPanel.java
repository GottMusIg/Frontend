package com.gottmusig.components;

import java.io.Serializable;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;

import com.gottmusig.models.RealmLocationListModel;
import com.gottmusig.models.ServiceProxyModel;
import com.gottmusig.searchcharacter.domain.api.SearchCharacter;
import com.gottmusig.searchcharacter.jpa.Location;


public class CharacterSearchPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IModel<List<Location>> locationsListModel;

	public CharacterSearchPanel(String id,
								RealmLocationListModel locationsListModel,
								final ServiceProxyModel<SearchCharacter> searchCharModel) {
		super(id, Model.of(new CharacterSearchFormData()));
		this.locationsListModel = locationsListModel;
		
		final boolean isSignedIn = AuthenticatedWebSession.get().isSignedIn();
		
		final IModel<CharacterSearchFormData> formDataModel = new CompoundPropertyModel<>((CharacterSearchFormData)getDefaultModelObject());
		
		final IModel<List<String>> realmListModel = new ListModel<String>();
		realmListModel.setObject(searchCharModel.getObject().getRealms(locationsListModel.getObject().iterator().next()));
		
		final Label responseText = new Label("blizz");
		responseText.setOutputMarkupId(true);
		
		Form<CharacterSearchFormData> searchForm = new Form<CharacterSearchFormData>("character-search-form",
																					 formDataModel) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				super.onSubmit();

				String restResult = searchCharModel.getObject().searchCharacter(formDataModel.getObject()
																							 .getLocation(),
																	   			formDataModel.getObject()
																	   						 .getRealm(),
																	   			formDataModel.getObject()
																	   						 .getName());
				responseText.setDefaultModel(Model.of(restResult));
			}
		};
		
		
		final DropDownChoice<String> realms = new DropDownChoice<String>("realm", realmListModel);
		realms.setOutputMarkupId(true);
		
		DropDownChoice<Location> location = new DropDownChoice<>("location", locationsListModel);
		location.add(new AjaxFormComponentUpdatingBehavior("change") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void onUpdate(AjaxRequestTarget target) {
				realmListModel.setObject(searchCharModel.getObject().getRealms(formDataModel.getObject().getLocation()));
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
				target.add(responseText);
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
		
		add(searchForm);
		add(responseText);
		
	}
	
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
		public void setRealm(String realm) {
			this.realm = realm;
		}
		
		public String getName() {
			return name;
		}
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