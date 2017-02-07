package com.gottmusig.components.account;

import java.io.Serializable;
import java.security.MessageDigest;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.gottmusig.account.domain.api.Account;
import com.gottmusig.account.domain.api.AccountAdministration;
import com.gottmusig.models.ServiceProxyModel;
import com.gottmusig.pages.HomePage;
import com.gottmusig.validators.StrongPasswordValidator;

/**
 * These panel is the default registy panel for an {@link Account}.
 * 
 * @author kkalmus
 */
public class RegistryPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public RegistryPanel(String id, final ServiceProxyModel<AccountAdministration> accAdminModel) {
			super(id, Model.of(new RegistryFormData()));

		final IModel<RegistryFormData> formDataModel = new CompoundPropertyModel<>((RegistryFormData) getDefaultModelObject());
		
		add(new FeedbackPanel("feedback"));
		
		Form<RegistryFormData> registryForm = new Form<RegistryFormData>("registry-form", formDataModel) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit() {
				super.onSubmit();
				RegistryFormData formData = formDataModel.getObject();
				String sha256 = sha256(formData.getPassword());
				accAdminModel.getObject().register(formData.getUsername(), sha256);
				throw new RestartResponseAtInterceptPageException(HomePage.class);
			}
			
		};
		
		TextField<String> usernameField = new TextField<>("username");
		
		PasswordTextField passwordField = new PasswordTextField("password");
		passwordField.add(new StrongPasswordValidator());
		
		PasswordTextField confirmPasswordField = new PasswordTextField("confirmPassword");
		
		registryForm.add(usernameField);
		registryForm.add(passwordField);
		registryForm.add(confirmPasswordField);
		registryForm.add(new EqualPasswordInputValidator(passwordField, confirmPasswordField));
		
		add(registryForm);
		
	}
	
	/*
	 * TODO FIXIT - org.apache.commons.codec.digest.DigestUtils.sha256Hex(stringText);
	 * OR Guava - Hashing.sha256().hashString("String", StandardCharsets.UTF_8).toString();
	 */
	public static String sha256(String base) {
		try {	
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			
			for(int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			
			return hexString.toString();
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	public static class RegistryFormData implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private String username;
		private String password;
		private String confirmPassword;
		
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
		
		public String getConfirmPassword() {
			return confirmPassword;
		}
		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}
		
	}
	
}