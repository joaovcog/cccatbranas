package com.cccat.domain.account.validation;

import java.util.regex.Pattern;

import com.cccat.shared.ValidationException;

public class AccountIdValidator extends Validator {

	@Override
	protected void applyValidation(String accountId) {
		if (!Pattern.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$", accountId)) {
			throw new ValidationException("Invalid pattern for account ID! The ID should follow the UUID pattern.");
		}
	}

	@Override
	protected String getNullOrEmptyValidationMessage() {
		return "No input for the Account ID!";
	}

}
