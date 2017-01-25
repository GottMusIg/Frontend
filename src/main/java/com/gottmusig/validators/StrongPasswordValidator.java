package com.gottmusig.validators;

import java.util.regex.Pattern;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public class StrongPasswordValidator implements IValidator<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//1 digit, 1 lower, 1 upper, 1 symbol "@#!$%", from 6 to 20
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#!$%]).{6,20})";
	
	private static final String ERROR_MESSAGE = "Password required at least 1 uppercase and 1 lowercase, "
												+ "1 digit, 1 simbol '@#!$%', length from 6 to 20.";
	
	private final Pattern pattern;
	
	public StrongPasswordValidator() {
		pattern = Pattern.compile(PASSWORD_PATTERN);
	}
	
	@Override
	public void validate(IValidatable<String> validatable) {
		final String password = validatable.getValue();
		if(pattern.matcher(password).matches() == false) {
			error(validatable, ERROR_MESSAGE);
		}
	}

	private void error(IValidatable<String> validatable, String errorKey) {
		ValidationError error = new ValidationError();
		error.setMessage(errorKey);
		validatable.error(error);
	}

}