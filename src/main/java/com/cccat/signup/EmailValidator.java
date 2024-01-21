package com.cccat.signup;

import java.util.regex.Pattern;

public class EmailValidator {

	public void validate(String email) {
		if (!Pattern.matches("^(.+)@(.+)$", email)) {
			throw new ValidationException("Invalid e-mail! Please, type a valid e-mail for signing up.");
		}
	}
	
}
