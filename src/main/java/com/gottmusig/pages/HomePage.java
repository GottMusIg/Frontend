package com.gottmusig.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.gottmusig.components.ClassDPSPanel;
import com.gottmusig.components.basics.FooterPanel;
import com.gottmusig.components.basics.HeaderPanel;
import com.gottmusig.configurations.ApplicationConfiguration;
import com.gottmusig.models.SpecificationDPSListModel;

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