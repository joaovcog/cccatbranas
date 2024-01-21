package com.cccat.signup;

import java.util.regex.Pattern;

public class CarPlateValidator {

	public static boolean isValid(String carPlate) {
		return Pattern.matches("[A-Z]{3}[0-9]{4}", carPlate);
	}
	
}
