package com.cccat.signup;

import java.util.regex.Pattern;

public class EmailValidator {

	public static boolean isValid(String email) {
		return Pattern.matches("^(.+)@(.+)$", email);
	}
	
}
