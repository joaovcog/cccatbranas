package com.cccat.signup;

import java.util.regex.Pattern;

public class NameValidator {

	public void validate(String name) {
		if (!Pattern.matches("[a-zA-Z ]+", name)) {
			throw new ValidationException("Invalid name! The name should have only letters.");
		}
	}

}
