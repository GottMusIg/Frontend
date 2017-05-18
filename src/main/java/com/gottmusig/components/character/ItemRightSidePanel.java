package com.gottmusig.components.character;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.gottmusig.database.service.domain.character.Character;

public class ItemRightSidePanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final ItemRightPanel handsPanel;
	private final ItemRightPanel waistPanel;
	private final ItemRightPanel legsPanel;
	private final ItemRightPanel feetPanel;
	private final ItemRightPanel finger1Panel;
	private final ItemRightPanel finger2Panel;
	private final ItemRightPanel trinket1Panel;
	private final ItemRightPanel trinket2Panel;
	private final ItemRightPanel offHandPanel;
	
	public ItemRightSidePanel(String id) {
		super(id);
	
		this.handsPanel = new ItemRightPanel("hands-item");
		handsPanel.setOutputMarkupId(true);
		
		this.waistPanel = new ItemRightPanel("waist-item");
		waistPanel.setOutputMarkupId(true);
		
		this.legsPanel = new ItemRightPanel("legs-item");
		legsPanel.setOutputMarkupId(true);
		
		this.feetPanel = new ItemRightPanel("feet-item");
		feetPanel.setOutputMarkupId(true);
		
		this.finger1Panel = new ItemRightPanel("finger-1-item");
		finger1Panel.setOutputMarkupId(true);
		
		this.finger2Panel = new ItemRightPanel("finger-2-item");
		finger2Panel.setOutputMarkupId(true);
		
		this.trinket1Panel = new ItemRightPanel("trinket-1-item");
		trinket1Panel.setOutputMarkupId(true);
		
		this.trinket2Panel = new ItemRightPanel("trinket-2-item");
		trinket2Panel.setOutputMarkupId(true);
		
		this.offHandPanel = new ItemRightPanel("off-hand-item");
		offHandPanel.setOutputMarkupId(true);
		
		add(handsPanel);
		add(waistPanel);
		add(legsPanel);
		add(feetPanel);
		add(finger1Panel);
		add(finger2Panel);
		add(trinket1Panel);
		add(trinket2Panel);
		add(offHandPanel);
	}

	public void showItemRightSidePanel(IModel<Character> characterModel) {
		this.handsPanel.showItem(Model.of(characterModel.getObject().getEquipmentSet().getHands()));
		this.waistPanel.showItem(Model.of(characterModel.getObject().getEquipmentSet().getWaist()));
		this.legsPanel.showItem(Model.of(characterModel.getObject().getEquipmentSet().getLegs()));
		this.feetPanel.showItem(Model.of(characterModel.getObject().getEquipmentSet().getFeet()));
		this.finger1Panel.showItem(Model.of(characterModel.getObject().getEquipmentSet().getFinger1()));
		this.finger2Panel.showItem(Model.of(characterModel.getObject().getEquipmentSet().getFinger2()));
		this.trinket1Panel.showItem(Model.of(characterModel.getObject().getEquipmentSet().getTrinket1()));
		this.trinket2Panel.showItem(Model.of(characterModel.getObject().getEquipmentSet().getTrinket2()));
		this.offHandPanel.showItem(Model.of(characterModel.getObject().getEquipmentSet().getOffHand()));
	}
	
}