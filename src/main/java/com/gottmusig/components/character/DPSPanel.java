package com.gottmusig.components.character;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.gottmusig.database.service.domain.character.Character;

public class DPSPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Label charNameLabel;
	private final Label dpsLabel;
	private final WebMarkupContainer dpsDiagram;
	
	public DPSPanel(String id,
					String title) {
		super(id);
		

		charNameLabel = new Label("char-name", Model.of(""));
		
		dpsLabel = new Label("dps", Model.of(""));
		
		dpsDiagram = new WebMarkupContainer("dps-diagram");
		
		
		add(new Label("title", title));
		add(charNameLabel);
		add(dpsLabel);
		add(dpsDiagram);
	}

	public void showDPS(IModel<Character> characterModel) {
		charNameLabel.setDefaultModelObject("DPS of " + characterModel.getObject().getName());
		dpsLabel.setDefaultModelObject(characterModel.getObject().getDPS() + "DPS");
	}
	
}