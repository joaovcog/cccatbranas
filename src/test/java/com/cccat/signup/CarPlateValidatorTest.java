package com.cccat.signup;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CarPlateValidatorTest {

	@Test
	void shouldValidateCarPlateSuccesfullyWithProperPatternOfThreeLettersAndFourNumbers() {
		String carPlate = "AAA1111";
		assertTrue(CarPlateValidator.isValid(carPlate));
	}
	
	@Test
	void shouldFailValidatingCarPlateWithTwoLetters() {
		String carPlate = "AA1111";
		assertFalse(CarPlateValidator.isValid(carPlate));
	}
	
	@Test
	void shouldFailValidatingCarPlateWithThreeNumbers() {
		String carPlate = "AAA111";
		assertFalse(CarPlateValidator.isValid(carPlate));
	}
	
	@Test
	void shouldFailValidatingCarPlateWithOnlyLetters() {
		String carPlate = "AAAAAAA";
		assertFalse(CarPlateValidator.isValid(carPlate));
	}
	
	@Test
	void shouldFailValidatingCarPlateWithOnlyNumbers() {
		String carPlate = "3334444";
		assertFalse(CarPlateValidator.isValid(carPlate));
	}
	
	@Test
	void shouldFailValidatingEmptyStringForCarPlate() {
		String carPlate = "";
		assertFalse(CarPlateValidator.isValid(carPlate));
	}

}
