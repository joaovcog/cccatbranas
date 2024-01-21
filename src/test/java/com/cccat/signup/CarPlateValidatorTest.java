package com.cccat.signup;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarPlateValidatorTest {
	
	private CarPlateValidator carPlateValidator;
	
	@BeforeEach
	public void setup() {
		carPlateValidator = new CarPlateValidator();
	}

	@Test
	void shouldValidateCarPlateSuccesfullyWithProperPatternOfThreeLettersAndFourNumbers() {
		String carPlate = "AAA1111";
		assertTrue(carPlateValidator.isValid(carPlate));
	}
	
	@Test
	void shouldFailValidatingCarPlateWithTwoLetters() {
		String carPlate = "AA1111";
		assertFalse(carPlateValidator.isValid(carPlate));
	}
	
	@Test
	void shouldFailValidatingCarPlateWithThreeNumbers() {
		String carPlate = "AAA111";
		assertFalse(carPlateValidator.isValid(carPlate));
	}
	
	@Test
	void shouldFailValidatingCarPlateWithOnlyLetters() {
		String carPlate = "AAAAAAA";
		assertFalse(carPlateValidator.isValid(carPlate));
	}
	
	@Test
	void shouldFailValidatingCarPlateWithOnlyNumbers() {
		String carPlate = "3334444";
		assertFalse(carPlateValidator.isValid(carPlate));
	}
	
	@Test
	void shouldFailValidatingEmptyStringForCarPlate() {
		String carPlate = "";
		assertFalse(carPlateValidator.isValid(carPlate));
	}

}
