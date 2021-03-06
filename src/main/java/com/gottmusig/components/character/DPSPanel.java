package com.gottmusig.components.character;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.logging.Logger;

import org.apache.wicket.AttributeModifier;
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

	private static final Logger LOGGER = Logger.getLogger(DPSPanel.class.getName());
	public static final String CLASS = "class";
	
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
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		DecimalFormat format = new DecimalFormat("0.#");
		format.setDecimalFormatSymbols(symbols);
		try {
			dpsLabel.setDefaultModelObject(format.parse("" + characterModel.getObject().getDPS()).floatValue() + " DPS");
		} catch (ParseException e) {
			LOGGER.warning("Could not parse the dps to ###.###: " + e);
		}
		
		dpsDiagram.add(AttributeModifier.remove(CLASS));
		dpsDiagram.add(AttributeModifier.append(CLASS, "dps"));
		dpsDiagram.add(AttributeModifier.append(CLASS, characterModel.getObject()
																	   .getClassSpecification()
																	   .getWOWClass()
																	   .getName()
																	   .toLowerCase()
																	   .replaceAll("\\s+", "")));
		
		int charDps = characterModel.getObject().getDPS();
		charDps = charDps <= 1 ? 1 : charDps;
		int maxDps = characterModel.getObject().getClassSpecification().getSpecificationDPS().getDPS();
		maxDps = maxDps <= 0 ? charDps : maxDps;
		int dps = charDps * 100 / maxDps;
		
		dpsDiagram.add(AttributeModifier.append("style", "width: " + dps + "%;"));
	}
	
}