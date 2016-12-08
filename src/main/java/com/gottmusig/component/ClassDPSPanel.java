package com.gottmusig.component;

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

	public ClassDPSPanel(String id, SpecificationDPSListModel specificationDPSModel) {
		super(id);
		
		setDefaultModel(specificationDPSModel);
		
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
				
				item.add(new Label("dps", Model.of(item.getModelObject().getSpecificationDPS())));
				
				WebMarkupContainer dpsDiagram = new WebMarkupContainer("dps-diagram");
				
				dpsDiagram.add(AttributeModifier.append("class", item.getModelObject()
																	 .getSpecification()
																	 .getWOWClass()
																	 .getName()
																	 .toLowerCase()));
				
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