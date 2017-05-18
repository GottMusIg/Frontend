package com.gottmusig.components.character;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.gottmusig.database.service.domain.item.Item;

public class ItemPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Label itemName;
	private final Label itemGS;
	private final WebComponent itemImage;
	
	public ItemPanel(String id) {
		super(id);
		
		this.itemImage = new WebComponent("item-img");
		itemImage.setOutputMarkupId(true);
		
		itemName = new Label("item-name", Model.of(""));
		itemGS = new Label("item-gs", Model.of(""));
		
		add(itemImage);
		add(itemName);
		add(itemGS);
		
	}
	
	public void showItem(IModel<Item> itemModel) {
		if(itemModel.getObject() == null) return;
		this.itemName.setDefaultModelObject(itemModel.getObject().getName());
		this.itemGS.setDefaultModelObject(itemModel.getObject().getItemLevel().toString());
		this.itemImage.add(new AttributeModifier("src", Model.of(itemModel.getObject().getIconTooltip())));
	}
	
}