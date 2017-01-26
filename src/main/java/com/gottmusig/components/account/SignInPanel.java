package com.gottmusig.components.account;

import java.io.Serializable;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.gottmusig.GottMusIgSession;
import com.gottmusig.account.domain.api.AccountAdministration;
import com.gottmusig.models.AccountServiceProxyModel;
import com.gottmusig.pages.account.AccountFeedbackPage;

public class SignInPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SignInPanel(String id, final AccountServiceProxyModel<AccountAdministration> accAdminModel) {
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
																				   		 accAdminModel.getObject());
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
		
		add(signInForm);
		
	}
	
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