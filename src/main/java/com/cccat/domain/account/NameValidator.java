package com.cccat.domain.account;

import java.util.regex.Pattern;

import com.cccat.shared.ValidationException;

public class NameValidator {

	public void validate(String name) {
		if (!Pattern.matches("[a-zA-Z ]+", name)) {
			throw new ValidationException("Invalid name! The name should have only letters.");
		}
	}

}
