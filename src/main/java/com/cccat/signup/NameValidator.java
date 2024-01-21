package com.cccat.signup;

import java.util.regex.Pattern;

public class NameValidator {

	public boolean isValid(String name) {
		return Pattern.matches("[a-zA-Z ]+", name);
	}

}
