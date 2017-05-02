package com.gottmusig.components.character;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.gottmusig.database.service.domain.character.Character;

public class CharacterGearPanel extends Panel {

	private static final String WOW_RENDER_PAGE = "http://render-eu.worldofwarcraft.com/character/";
	private static final String WOW_RENDER_TYPE = "-profilemain.jpg";
	private static final String REPLACE = "-avatar.jpg";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Label characterName;
	private final WebComponent characterImage;

	private IModel<Character> characterModel;
	private IModel<String> characterImageModel;

	public CharacterGearPanel(String id, IModel<Character> characterModel) {
		super(id);

		this.characterModel = characterModel;
		this.characterImageModel = Model.of("");

		this.characterName = new Label("character-name");

		// TODO Background-image instead of img
		this.characterImage = new WebComponent("char-image", characterImageModel) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTag(ComponentTag tag) {
				super.onComponentTag(tag);
				if (getDefaultModelObject() != null) {
					checkComponentTag(tag, "img");
					tag.put("src", getDefaultModelObjectAsString());
				}
			}

		};
		characterImage.setOutputMarkupId(true);

		showGear(characterModel);

		add(characterName);
		add(characterImage);
	}

	public void showGear(IModel<Character> characterModel) {
		if (characterModel.getObject() == null) return;
		this.characterModel = characterModel;
		String characterImageString = characterModel.getObject().getThumbnailId();
		characterImageModel.setObject(WOW_RENDER_PAGE + characterImageString + WOW_RENDER_TYPE);
		this.characterName.setDefaultModel(Model.of(characterModel.getObject().getName()));
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		characterModel.detach();
	}

}