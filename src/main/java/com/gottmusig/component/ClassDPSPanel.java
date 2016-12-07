package com.gottmusig.component;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.gottmusig.dpsdifference.domain.api.SpecificationDPS;

public class ClassDPSPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClassDPSPanel(String id, IModel<List<SpecificationDPS>> specificationDPSModel) {
		super(id);
		
		ListView<SpecificationDPS> view = new ListView<SpecificationDPS>("dps-view", specificationDPSModel) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<SpecificationDPS> item) {
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
				int maxDps = 240000;
				
				int dps = item.getModelObject().getSpecificationDPS() * 100 / maxDps;
				
				dpsDiagram.add(AttributeModifier.append("style", "width: " + dps + "%;"));
				
				item.add(dpsDiagram);
			}
		};
		
		add(view);
		
	}

	
}