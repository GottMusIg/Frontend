package com.gottmusig.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.gottmusig.components.ClassDPSPanel;
import com.gottmusig.components.basics.FooterPanel;
import com.gottmusig.components.basics.HeaderPanel;
import com.gottmusig.models.SpecificationDPSListModel;

public class HomePage extends WebPage {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SpringBean
	private SpecificationDPSListModel specificationDPSListModel;
	
	public HomePage(final PageParameters parameters) {
		super(parameters);
		
        add(new HeaderPanel("header"));
		add(new ClassDPSPanel("classDPSPanel",
				   			  "Class DPS",
				   			  specificationDPSListModel));
		add(new FooterPanel("footer"));
    }
	
}