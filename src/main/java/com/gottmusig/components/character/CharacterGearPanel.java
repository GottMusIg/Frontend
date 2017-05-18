package com.gottmusig.components.character;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
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
	private final ItemPanel neckPanel;
	private final ItemPanel shoulderPanel;
	private final ItemPanel backPanel;
	private final ItemPanel chestPanel;
	private final ItemPanel shirtPanel;
	private final ItemPanel wristPanel;

	
	private IModel<Character> characterModel;
	private IModel<String> characterImageModel;
	private IModel<Item> headItemModel;
	private IModel<Item> neckItemModel;
	private IModel<Item> shoulderItemModel;
	private IModel<Item> backItemModel;
	private IModel<Item> chestItemModel;
	private IModel<Item> shirtItemModel;
	private IModel<Item> wristItemModel;
	

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
		
		neckPanel = new ItemPanel("nack-item");
		neckPanel.setOutputMarkupId(true);

		shoulderPanel = new ItemPanel("shoulders-item");
		shoulderPanel.setOutputMarkupId(true);
		
		backPanel = new ItemPanel("back-item");
		backPanel.setOutputMarkupId(true);
		
		chestPanel = new ItemPanel("chest-item");
		chestPanel.setOutputMarkupId(true);
		
		shirtPanel = new ItemPanel("shirt-item");
		shirtPanel.setOutputMarkupId(true);
		
		wristPanel = new ItemPanel("wrist-item");
		wristPanel.setOutputMarkupId(true);
		
		showGear(characterModel, null);

		characterImage.add(headPanel);
		characterImage.add(neckPanel);
		characterImage.add(shoulderPanel);
		characterImage.add(backPanel);
		characterImage.add(chestPanel);
		characterImage.add(shirtPanel);
		characterImage.add(wristPanel);
		
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
		this.neckItemModel = new AbstractReadOnlyModel<Item>() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Item getObject() {
				return characterModel.getObject().getEquipmentSet().getNeck();
			}
			
		};
		this.shoulderItemModel = new AbstractReadOnlyModel<Item>() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Item getObject() {
				return characterModel.getObject().getEquipmentSet().getShoulders();
			}
			
		};
		this.backItemModel = new AbstractReadOnlyModel<Item>() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Item getObject() {
				return characterModel.getObject().getEquipmentSet().getBack();
			}
			
		};
		this.chestItemModel = new AbstractReadOnlyModel<Item>() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Item getObject() {
				return characterModel.getObject().getEquipmentSet().getChest();
			}
			
		};
		this.shirtItemModel = new AbstractReadOnlyModel<Item>() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Item getObject() {
				return characterModel.getObject().getEquipmentSet().getSkirt();
			}
			
		};
		this.wristItemModel = new AbstractReadOnlyModel<Item>() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Item getObject() {
				return characterModel.getObject().getEquipmentSet().getWrist();
			}
			
		};
		this.headPanel.showItem(headItemModel);
		this.neckPanel.showItem(neckItemModel);
		this.shoulderPanel.showItem(shoulderItemModel);
		this.backPanel.showItem(backItemModel);
		this.chestPanel.showItem(chestItemModel);
		this.shirtPanel.showItem(shirtItemModel);
		this.wristPanel.showItem(wristItemModel);
		if(target != null) {
			target.add(headPanel);
			target.add(neckPanel);
			target.add(shoulderPanel);
			target.add(backPanel);
			target.add(chestPanel);
			target.add(shirtPanel);
			target.add(wristPanel);
		}
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		characterModel.detach();
	}

}