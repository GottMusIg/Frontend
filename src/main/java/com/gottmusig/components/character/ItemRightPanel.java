package com.gottmusig.components.character;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.gottmusig.database.service.domain.item.Item;

public class ItemRightPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final WebMarkupContainer wowHead;
	private final Label itemName;
	private final Label itemGS;
	private final WebComponent itemImage;

	public ItemRightPanel(String id) {
		super(id);

		this.wowHead = new WebMarkupContainer("wow-head");
		wowHead.setOutputMarkupId(true);
		
		itemName = new Label("item-name", Model.of(""));
		itemGS = new Label("item-gs", Model.of(""));
		
		this.itemImage = new WebComponent("item-img");
		itemImage.setOutputMarkupId(true);
		
		wowHead.add(itemName);
		wowHead.add(itemGS);
		wowHead.add(itemImage);
	
		add(wowHead);
	}
	
	public void showItem(IModel<Item> itemModel) {
		if(itemModel.getObject() == null) return;
		if(itemModel.getObject().getName().contains("empty")) return;
		this.itemName.setDefaultModelObject(itemModel.getObject().getName());
		this.itemGS.setDefaultModelObject(itemModel.getObject().getItemLevel().toString());
		this.itemImage.add(new AttributeModifier("src", Model.of(itemModel.getObject().getIconTooltip())));
		this.wowHead.add(new AttributeModifier("rel", Model.of(itemModel.getObject().getWowHeadTooltip())));
	}
	
}