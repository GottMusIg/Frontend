package com.gottmusig.components;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class RegistryPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegistryPanel(String id) {
		super(id, Model.of(new RegistryFormData()));

		final IModel<RegistryFormData> formDataModel = new CompoundPropertyModel<>((RegistryFormData) getDefaultModelObject());
		
		Form<RegistryFormData> registryForm = new Form<RegistryFormData>("registry-form", formDataModel) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit() {
				super.onSubmit();
				System.out.println(formDataModel.getObject().getUsername());
				System.out.println(formDataModel.getObject().getPassword());
				System.out.println(formDataModel.getObject().getConfirmPassword());
			}
			
		};
		
		TextField<String> usernameField = new TextField<>("username");
		
		PasswordTextField passwordField = new PasswordTextField("password");
		
		PasswordTextField confirmPasswordField = new PasswordTextField("confirmPassword");
		
		registryForm.add(usernameField);
		registryForm.add(passwordField);
		registryForm.add(confirmPasswordField);
		
		add(registryForm);
		
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