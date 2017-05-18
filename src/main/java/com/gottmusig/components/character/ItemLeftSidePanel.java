package com.gottmusig.components.character;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.gottmusig.database.service.domain.character.Character;

public class ItemLeftSidePanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final ItemLeftPanel headPanel;
	private final ItemLeftPanel neckPanel;
	private final ItemLeftPanel shoulderPanel;
	private final ItemLeftPanel backPanel;
	private final ItemLeftPanel chestPanel;
	private final ItemLeftPanel shirtPanel;
	private final ItemLeftPanel wristPanel;
	private final ItemLeftPanel mainHandPanel;
	
	public ItemLeftSidePanel(String id) {
		super(id);

		headPanel = new ItemLeftPanel("head-item");
		headPanel.setOutputMarkupId(true);
		
		neckPanel = new ItemLeftPanel("nack-item");
		neckPanel.setOutputMarkupId(true);

		shoulderPanel = new ItemLeftPanel("shoulders-item");
		shoulderPanel.setOutputMarkupId(true);
		
		backPanel = new ItemLeftPanel("back-item");
		backPanel.setOutputMarkupId(true);
		
		chestPanel = new ItemLeftPanel("chest-item");
		chestPanel.setOutputMarkupId(true);
		
		shirtPanel = new ItemLeftPanel("shirt-item");
		shirtPanel.setOutputMarkupId(true);
		
		wristPanel = new ItemLeftPanel("wrist-item");
		wristPanel.setOutputMarkupId(true);

		mainHandPanel = new ItemLeftPanel("main-hand-item");
		mainHandPanel.setOutputMarkupId(true);
		
		add(headPanel);
		add(neckPanel);
		add(shoulderPanel);
		add(backPanel);
		add(chestPanel);
		add(shirtPanel);
		add(wristPanel);
		add(mainHandPanel);
	}

	public void showLeftSidePanel(IModel<Character> characterModel) {
		if(characterModel.getObject() == null) return;
		this.headPanel.showItem(Model.of(characterModel.getObject().getEquipmentSet().getHead()));
		this.neckPanel.showItem(Model.of(characterModel.getObject().getEquipmentSet().getNeck()));
		this.shoulderPanel.showItem(Model.of(characterModel.getObject().getEquipmentSet().getShoulders()));
		this.backPanel.showItem(Model.of(characterModel.getObject().getEquipmentSet().getBack()));
		this.chestPanel.showItem(Model.of(characterModel.getObject().getEquipmentSet().getChest()));
		this.shirtPanel.showItem(Model.of(characterModel.getObject().getEquipmentSet().getSkirt()));
		this.wristPanel.showItem(Model.of(characterModel.getObject().getEquipmentSet().getWrist()));
		this.mainHandPanel.showItem(Model.of(characterModel.getObject().getEquipmentSet().getMainHand()));
	}
	
}