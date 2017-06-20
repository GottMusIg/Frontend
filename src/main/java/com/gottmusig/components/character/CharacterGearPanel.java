package com.gottmusig.components.character;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.gottmusig.database.service.domain.character.Character;

public class CharacterGearPanel extends Panel {

	private static final String WOW_RENDER_PAGE = "http://render-eu.worldofwarcraft.com/character/";
	private static final String WOW_RENDER_TYPE = "-profilemain.jpg";
//	private static final String REPLACE = "-avatar.jpg";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Label characterName;
	private final WebMarkupContainer characterImage;
	private final ItemLeftSidePanel itemLeftSidePanel;
	private final ItemRightSidePanel itemRightSidePanel;
	private final DPSPanel dpsPanel;
	
	private IModel<Character> characterModel;
	private IModel<String> characterImageModel;
	

	public CharacterGearPanel(String id, IModel<Character> characterModel) {
		super(id);

		this.characterModel = characterModel;
		this.characterImageModel = Model.of("");

		this.characterName = new Label("character-name");

		this.characterImage = new WebMarkupContainer("character-image");
		characterImage.setOutputMarkupId(true);
		characterImage.setEscapeModelStrings(false);
		
		showGear(characterModel, null);
		
		this.itemLeftSidePanel = new ItemLeftSidePanel("item-left-side-panel");
		itemLeftSidePanel.setOutputMarkupId(true);
				
		this.itemRightSidePanel = new ItemRightSidePanel("item-right-side-panel");
		itemRightSidePanel.setOutputMarkupId(true);
		
		characterImage.add(itemLeftSidePanel);
		characterImage.add(itemRightSidePanel);
		
		dpsPanel = new DPSPanel("dps-panel", "DPS");
		
		add(characterName);
		add(characterImage);
		add(dpsPanel);
	}

	public void showGear(IModel<Character> characterModel, AjaxRequestTarget target) {
		if (characterModel.getObject() == null) return;
		this.characterModel = characterModel;
		String characterImageString = characterModel.getObject().getThumbnailId();
		characterImageModel.setObject(WOW_RENDER_PAGE + characterImageString + WOW_RENDER_TYPE);
		characterImage.add(AttributeModifier.append("style", "background-image: url('" + characterImageModel.getObject() + "');"));
		this.characterName.setDefaultModel(Model.of(characterModel.getObject().getName() 
										   + " - " + characterModel.getObject().getClassSpecification().getName()
										   + " " + characterModel.getObject().getWOWClass().getName()));
		this.itemLeftSidePanel.showLeftSidePanel(characterModel);
		this.itemRightSidePanel.showItemRightSidePanel(characterModel);
		this.dpsPanel.showDPS(characterModel);
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		characterModel.detach();
	}

}