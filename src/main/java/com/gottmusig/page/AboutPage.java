package com.gottmusig.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.gottmusig.component.FooterPanel;
import com.gottmusig.component.NavigationPanel;

public class AboutPage extends WebPage {

	public AboutPage(final PageParameters parameters) {
		super(parameters);
		
		add(new NavigationPanel("navigation"));
		add(new FooterPanel("footer"));
		
	}
	
}