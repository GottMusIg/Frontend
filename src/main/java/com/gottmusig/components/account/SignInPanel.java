package com.gottmusig.components.account;

import java.io.Serializable;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.gottmusig.GottMusIgSession;
import com.gottmusig.database.service.domain.account.Account;
import com.gottmusig.database.service.domain.account.AccountService;
import com.gottmusig.models.ServiceProxyModel;
import com.gottmusig.pages.account.AccountFeedbackPage;
import com.gottmusig.pages.account.RegistryPage;

/**
 * These panel is the defaul sign in for an {@link Account}.
 *  
 * @author kkalmus
 */
public class SignInPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SignInPanel(String id, final ServiceProxyModel<AccountService> accountServiceModel) {
		super(id, Model.of(new SignInFormData()));

		final IModel<SignInFormData> formDataModel = new CompoundPropertyModel<>((SignInFormData) getDefaultModelObject());
		
		add(new FeedbackPanel("feedback"));
		
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
				}
			}
			
		};
		
		TextField<String> username = new TextField<>("username");
		username.setRequired(true);
		
		PasswordTextField password = new PasswordTextField("password");
		password.setRequired(true);
		
		signInForm.add(username);
		signInForm.add(password);
		signInForm.add(new Link<RegistryPage>("account-registry-link") {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			public void onClick() {
				throw new RestartResponseAtInterceptPageException(RegistryPage.class);
			}
		});
		
		add(signInForm);
	}
	
	/**
	 * Default data object for {@link SignInPanel}
	 * 
	 * @author kkalmus
	 */
	public static class SignInFormData implements Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private String username;
		private String password;
		
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
	}

}