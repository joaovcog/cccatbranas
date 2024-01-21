package com.cccat.domain.account;

import java.util.regex.Pattern;

import com.cccat.shared.ValidationException;

public class EmailValidator {

	public void validate(String email) {
		if (!Pattern.matches("^(.+)@(.+)$", email)) {
			throw new ValidationException("Invalid e-mail! Please, type a valid e-mail for signing up.");
		}
	}
	
}
