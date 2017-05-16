package com.gottmusig.components.character;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.gottmusig.database.service.domain.character.Character;
import com.gottmusig.database.service.domain.item.Item;

public class CharacterGearPanel extends Panel {

	private static final String WOW_RENDER_PAGE = "http://render-eu.worldofwarcraft.com/character/";
	private static final String WOW_RENDER_TYPE = "-profilemain.jpg";
	private static final String REPLACE = "-avatar.jpg";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Label characterName;
	private final WebMarkupContainer characterImage;
	private final ItemPanel headPanel;

	private IModel<Character> characterModel;
	private IModel<String> characterImageModel;
	private IModel<Item> headItemModel;
	

	public CharacterGearPanel(String id, IModel<Character> characterModel) {
		super(id);

		this.characterModel = characterModel;
		this.characterImageModel = Model.of("");

		this.characterName = new Label("character-name");

		this.characterImage = new WebMarkupContainer("character-image");
		characterImage.setOutputMarkupId(true);
		characterImage.setEscapeModelStrings(false);

		
		headPanel = new ItemPanel("head-item");
		headPanel.setOutputMarkupId(true);
		
		showGear(characterModel, null);

		characterImage.add(headPanel);

		add(characterName);
		add(characterImage);
	}

	public void showGear(IModel<Character> characterModel, AjaxRequestTarget target) {
		if (characterModel.getObject() == null) return;
		this.characterModel = characterModel;
		String characterImageString = characterModel.getObject().getThumbnailId();
		characterImageModel.setObject(WOW_RENDER_PAGE + characterImageString + WOW_RENDER_TYPE);
		characterImage.add(AttributeModifier.append("style", "background-image: url('" + characterImageModel.getObject() + "');"));
		this.characterName.setDefaultModel(Model.of(characterModel.getObject().getName()));
		this.headItemModel = new AbstractReadOnlyModel<Item>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Item getObject() {
				return characterModel.getObject().getEquipmentSet().getHead();
			}
			
		};
		this.headPanel.showItem(headItemModel);
		if(target != null) {
			target.add(headPanel);
		}
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		characterModel.detach();
	}

}