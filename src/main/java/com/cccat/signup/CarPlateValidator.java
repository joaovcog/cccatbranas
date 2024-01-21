package com.cccat.signup;

import java.util.regex.Pattern;

public class CarPlateValidator {

	public void validate(String carPlate) {
		if (!Pattern.matches("[A-Z]{3}[0-9]{4}", carPlate)) {
			throw new ValidationException("Invalid car plate! Please, type a valid car plate with 3 letters and 4 numbers for signing up.");
		}
	}
	
}
