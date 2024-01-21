package com.cccat.domain.account.validation;

import java.util.regex.Pattern;

import com.cccat.shared.ValidationException;

public class EmailValidator extends Validator {

	@Override
	protected void applyValidation(String email) {
		if (!Pattern.matches("^(.+)@(.+)$", email)) {
			throw new ValidationException("Invalid e-mail! Please, type a valid e-mail for signing up.");
		}
	}

	@Override
	protected String getNullOrEmptyValidationMessage() {
		return createNullOrEmptyMessage("e-mail");
	}
	
}
