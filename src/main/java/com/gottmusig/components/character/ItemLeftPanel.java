package com.gottmusig.components.character;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.gottmusig.database.service.domain.item.Item;

public class ItemLeftPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final WebMarkupContainer wowHead;
	private final Label itemName;
	private final Label itemGS;
	private final WebComponent itemImage;
	
	public ItemLeftPanel(String id) {
		super(id);

		this.wowHead = new WebMarkupContainer("wow-head");
		wowHead.setOutputMarkupId(true);
		
		this.itemImage = new WebComponent("item-img");
		itemImage.setOutputMarkupId(true);
		
		itemName = new Label("item-name", Model.of(""));
		itemGS = new Label("item-gs", Model.of(""));
		
		wowHead.add(itemImage);
		wowHead.add(itemName);
		wowHead.add(itemGS);
		
		add(wowHead);
	}
	
	public void showItem(IModel<Item> itemModel) {
		IModel<String> itemNameModel = Model.of("Empty Slot");
		IModel<String> itemGSModel = Model.of("-");
		IModel<String> itemImageModel = Model.of("http://wow.zamimg.com/images/wow/icons/large/inv_misc_questionmark.jpg");
		IModel<String> wowHeadModel = Model.of("");
		if(itemModel.getObject() != null && !itemModel.getObject().getName().contains("empty")) {
			itemNameModel = Model.of(itemModel.getObject().getName());
			itemGSModel = Model.of(itemModel.getObject().getItemLevel().toString());
			itemImageModel = Model.of(itemModel.getObject().getIconTooltip());
			wowHeadModel = Model.of(itemModel.getObject().getWowHeadTooltip());
		}
		this.itemName.setDefaultModel(itemNameModel);
		this.itemGS.setDefaultModel(itemGSModel);
		this.itemImage.add(new AttributeModifier("src", itemImageModel));
		this.wowHead.add(new AttributeModifier("rel", wowHeadModel));
	}
	
}