package com.gottmusig.component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import com.gottmusig.dpsdifference.jpa.SpecificationDPSEntity;
import com.gottmusig.model.SpecificationDPSListModel;

public class ClassDPSPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClassDPSPanel(String id,
						 String title,
						 SpecificationDPSListModel specificationDPSModel) {
		super(id);
		
		setDefaultModel(specificationDPSModel);
		
		add(new Label("title", title));
		
		ListView<SpecificationDPSEntity> view = new ListView<SpecificationDPSEntity>("dps-view", specificationDPSModel) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<SpecificationDPSEntity> item) {
				item.add(new Label("specification", Model.of(item.getModelObject()
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
					item.add(new Label("dps", Model.of(format.parse("" + item.getModelObject().getSpecificationDPS()).floatValue())));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				WebMarkupContainer dpsDiagram = new WebMarkupContainer("dps-diagram");
				
				dpsDiagram.add(AttributeModifier.append("class", item.getModelObject()
																	 .getSpecification()
																	 .getWOWClass()
																	 .getName()
																	 .toLowerCase()
																	 .replaceAll("\\s+", "")));
				
				//TODO add the max dps from the domain model
				int maxDps = ((SpecificationDPSListModel) getDefaultModel()).getMaxDPS();
				
				int dps = item.getModelObject().getSpecificationDPS() * 100 / maxDps;
				
				dpsDiagram.add(AttributeModifier.append("style", "width: " + dps + "%;"));
				
				item.add(dpsDiagram);
			}
		};
		
		add(view);
		
	}

	
}