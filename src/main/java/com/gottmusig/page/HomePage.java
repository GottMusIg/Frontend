package com.gottmusig.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.gottmusig.component.ClassDPSPanel;
import com.gottmusig.component.FooterPanel;
import com.gottmusig.component.HeaderPanel;
import com.gottmusig.configuration.ApplicationConfiguration;
import com.gottmusig.model.SpecificationDPSListModel;

public class HomePage extends WebPage {
	
	private static final long serialVersionUID = 1L;
	
	public HomePage(final PageParameters parameters) {
		super(parameters);

		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		SpecificationDPSListModel specificationDPSModel = context.getBean(SpecificationDPSListModel.class);
		
        add(new HeaderPanel("header"));
		add(new ClassDPSPanel("classDPSPanel",
				   			  "Class DPS",
				   			  specificationDPSModel));
		add(new FooterPanel("footer"));
    }
	
}