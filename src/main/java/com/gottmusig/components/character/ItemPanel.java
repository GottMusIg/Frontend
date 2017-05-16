package com.gottmusig.components.character;

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

	private IModel<String> itemName;
	
	public ItemPanel(String id) {
		super(id);
		
		add(new Label("item-name", itemName));
		
	}
	
	public void showItem(IModel<Item> itemModel) {
		this.itemName = Model.of(itemModel.getObject().getContext());
	}
	
}