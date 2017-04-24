package com.gottmusig.components.basics;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.gottmusig.GottMusIgSession;
import com.gottmusig.components.account.RegistryPanel;
import com.gottmusig.components.account.SignInPanel.SignInFormData;
import com.gottmusig.database.service.domain.account.AccountService;
import com.gottmusig.models.ServiceProxyModel;
import com.gottmusig.pages.account.AccountFeedbackPage;
import com.gottmusig.pages.account.RegistryPage;
import com.gottmusig.pages.account.SignInPage;

public class SignInDropDownPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//TODO implements SignInPanel
	@SpringBean
	private ServiceProxyModel<AccountService> accountServiceModel;
	
	public SignInDropDownPanel(String id) {
		super(id, Model.of(new SignInFormData()));

		//TODO implements the SignInPanel
		
		final IModel<SignInFormData> formDataModel = new CompoundPropertyModel<>((SignInFormData) getDefaultModelObject());
		
		Form<SignInFormData> signInForm = new Form<SignInFormData>("sign-in-form", formDataModel) {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				super.onSubmit();
				SignInFormData formData = formDataModel.getObject();
				String sha512 = RegistryPanel.sha256(formData.getPassword());
				String username = formData.getUsername();
				boolean isAuth = ((GottMusIgSession)AuthenticatedWebSession.get())
																		   .custumSignIn(username,
																				   		 sha512,
																				   		 accountServiceModel.getObject());
				if(isAuth) {
					throw new RestartResponseAtInterceptPageException(AccountFeedbackPage.class);
				} else {
					//Feedback
					throw new RestartResponseAtInterceptPageException(SignInPage.class);
				}
			}
			
		};
		
		TextField<String> username = new TextField<>("username");
		
		PasswordTextField password = new PasswordTextField("password");
		
		signInForm.add(username);
		signInForm.add(password);
		signInForm.add(new BookmarkablePageLink<>("create-account", RegistryPage.class));

		add(signInForm);
		
		//TODO Fix for FireFox autocomplete (Dropdown disappears)
		
	}

}