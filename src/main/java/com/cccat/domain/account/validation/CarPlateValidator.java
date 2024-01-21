package com.cccat.domain.account.validation;

import java.util.regex.Pattern;

import com.cccat.shared.ValidationException;

public class CarPlateValidator extends Validator {

	@Override
	protected void applyValidation(String carPlate) {
		if (!Pattern.matches("[A-Z]{3}[0-9]{4}", carPlate)) {
			throw new ValidationException("Invalid car plate! Please, type a valid car plate with 3 letters and 4 numbers for signing up.");
		}
	}

	@Override
	protected String getNullOrEmptyValidationMessage() {
		return createNullOrEmptyMessage("Car Plate");
	}
	
}
