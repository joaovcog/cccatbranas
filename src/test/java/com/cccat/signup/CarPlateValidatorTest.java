package com.cccat.signup;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarPlateValidatorTest {
	
	private static final String EXCEPTION_MESSAGE = "Invalid car plate! Please, type a valid car plate with 3 letters and 4 numbers for signing up.";
	
	private CarPlateValidator carPlateValidator;
	
	@BeforeEach
	public void setup() {
		carPlateValidator = new CarPlateValidator();
	}

	@Test
	void shouldValidateCarPlateSuccesfullyWithProperPatternOfThreeLettersAndFourNumbers() {
		String carPlate = "AAA1111";
		assertDoesNotThrow(() -> carPlateValidator.validate(carPlate));
	}
	
	@Test
	void shouldFailValidatingCarPlateWithTwoLetters() {
		String carPlate = "AA1111";
		ValidationException ex = assertThrows(ValidationException.class, () -> carPlateValidator.validate(carPlate));
		assertEquals(ex.getMessage(), EXCEPTION_MESSAGE);
	}
	
	@Test
	void shouldFailValidatingCarPlateWithThreeNumbers() {
		String carPlate = "AAA111";
		ValidationException ex = assertThrows(ValidationException.class, () -> carPlateValidator.validate(carPlate));
		assertEquals(ex.getMessage(), EXCEPTION_MESSAGE);
	}
	
	@Test
	void shouldFailValidatingCarPlateWithOnlyLetters() {
		String carPlate = "AAAAAAA";
		ValidationException ex = assertThrows(ValidationException.class, () -> carPlateValidator.validate(carPlate));
		assertEquals(ex.getMessage(), EXCEPTION_MESSAGE);
	}
	
	@Test
	void shouldFailValidatingCarPlateWithOnlyNumbers() {
		String carPlate = "3334444";
		ValidationException ex = assertThrows(ValidationException.class, () -> carPlateValidator.validate(carPlate));
		assertEquals(ex.getMessage(), EXCEPTION_MESSAGE);
	}
	
	@Test
	void shouldFailValidatingEmptyStringForCarPlate() {
		String carPlate = "";
		ValidationException ex = assertThrows(ValidationException.class, () -> carPlateValidator.validate(carPlate));
		assertEquals(ex.getMessage(), EXCEPTION_MESSAGE);
	}

}
