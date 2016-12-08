package com.gottmusig.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gottmusig.component.ClassDPSPanel;
import com.gottmusig.dpsdifference.configuration.DPSDifferenceConfiguration;
import com.gottmusig.dpsdifference.domain.api.DPSDifference;
import com.gottmusig.model.SpecificationDPSListModel;

public class HomePage extends WebPage {
	
	private static final long serialVersionUID = 1L;
	
	public HomePage(final PageParameters parameters) {
		super(parameters);

		ApplicationContext context = new AnnotationConfigApplicationContext(DPSDifferenceConfiguration.class);
        DPSDifference dpsDifference = context.getBean(DPSDifference.class);
		
		
        SpecificationDPSListModel specificationDPSModel = new SpecificationDPSListModel(dpsDifference);
		
		add(new ClassDPSPanel("classDPSPanel", specificationDPSModel));

    }
	
}