package com.gottmusig.components;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.logging.Logger;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.gottmusig.database.service.domain.dpsdifference.SpecificationDPS;
import com.gottmusig.models.SpecificationDPSListModel;

/**
 * These panel shows the dps difference 
 * between each {@link SpecificationDPS}.
 * 
 * @author kkalmus
 */
public class ClassDPSPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(ClassDPSPanel.class.getName());
	
	public ClassDPSPanel(String id,
						 String title,
						 SpecificationDPSListModel specificationDPSModel) {
		super(id);
		
		setDefaultModel(specificationDPSModel);
		
		add(new Label("title", title));
		
		ListView<SpecificationDPS> view = new ListView<SpecificationDPS>("dps-view", specificationDPSModel) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<SpecificationDPS> item) {
				item.add(new Label("specification", Model.of("#" + (item.getIndex()+1) + " " +
															 item.getModelObject()
																 .getSpecification()
																 .getName())));
				
				item.add(new Label("class", Model.of(item.getModelObject()
														 .getSpecification()
														 .getWOWClass()
														 .getName())));
				
				DecimalFormatSymbols symbols = new DecimalFormatSymbols();
				symbols.setDecimalSeparator('.');
				DecimalFormat format = new DecimalFormat("0.#");
				format.setDecimalFormatSymbols(symbols);
				
				try {
					item.add(new Label("dps", Model.of(format.parse("" + item.getModelObject()
																			 .getDPS())
															 .floatValue())));
				} catch (ParseException e) {
					LOGGER.warning("Could not parse the dps to ###.###: " + e);
				}
				
				WebMarkupContainer dpsDiagram = new WebMarkupContainer("dps-diagram");
				
				dpsDiagram.add(AttributeModifier.append("class", item.getModelObject()
																	 .getSpecification()
																	 .getWOWClass()
																	 .getName()
																	 .toLowerCase()
																	 .replaceAll("\\s+", "")));
				
				int maxDps = ((SpecificationDPSListModel) getDefaultModel()).getMaxDPS();
				
				int dps = item.getModelObject().getDPS() * 100 / maxDps;
				
				dpsDiagram.add(AttributeModifier.append("style", "width: " + dps + "%;"));
				
				item.add(dpsDiagram);
			}
		};
		
		add(view);
	}
	
}