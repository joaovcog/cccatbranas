package com.cccat.domain.account.validation;

import com.cccat.shared.ValidationException;

public abstract class Validator {

	private static final String NULL_EMPTY_VALIDATION_MESSAGE = "No input for the %s! Please, type a valid %s for signing up.";

	public void validate(String value) {
		if (value == null || value.isEmpty()) {
			throw new ValidationException(getNullOrEmptyValidationMessage());
		}
		applyValidation(value);
	}

	protected abstract void applyValidation(String value);

	protected abstract String getNullOrEmptyValidationMessage();

	protected String createNullOrEmptyMessage(String identifier) {
		return String.format(NULL_EMPTY_VALIDATION_MESSAGE, identifier, identifier);
	}

}
