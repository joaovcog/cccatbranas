package com.cccat.domain.account;

import java.util.regex.Pattern;

import com.cccat.shared.ValidationException;

public class NameValidator extends Validator {

	@Override
	protected void applyValidation(String name) {
		if (!Pattern.matches("[a-zA-Z ]+", name)) {
			throw new ValidationException("Invalid name! The name should have only letters.");
		}
	}

	@Override
	protected String getNullOrEmptyValidationMessage() {
		return createNullOrEmptyMessage("name");
	}

}
