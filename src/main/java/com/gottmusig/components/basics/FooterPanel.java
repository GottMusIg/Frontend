package com.gottmusig.components.basics;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

import com.gottmusig.GottmusigApplication;
import com.gottmusig.pages.statics.AboutPage;

/**
 * Default footer for the {@link GottmusigApplication}.
 * 
 * @author kkalmus
 */
public class FooterPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FooterPanel(String id) {
		super(id);

		add(new BookmarkablePageLink<Object>("about-leon", AboutPage.class) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected CharSequence getURL() {
				return super.getURL() + "#leon";
			};
		});
		add(new BookmarkablePageLink<Object>("about-kamil", AboutPage.class) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected CharSequence getURL() {
				return super.getURL() + "#kamil";
			}
		});
		add(new BookmarkablePageLink<Object>("about-chris", AboutPage.class) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected CharSequence getURL() {
				return super.getURL() + "#christoph";
			}
		});
	}

}
