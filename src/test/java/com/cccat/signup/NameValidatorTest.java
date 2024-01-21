package com.cccat.signup;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NameValidatorTest {

	private NameValidator nameValidator;
	
	@BeforeEach
	public void setup() {
		nameValidator = new NameValidator();
	}
	
	@Test
	void shouldValidateNameSuccesfullyWithProperPattern() {
		String name = "John Doe";
		assertTrue(nameValidator.isValid(name));
	}
	
	@Test
	void shouldFailValidatingNameWithNumberInTheFirstName() {
		String name = "55555 Doe";
		assertFalse(nameValidator.isValid(name));
	}
	
	@Test
	void shouldFailValidatingNameWithOnlyNumbers() {
		String name = "5555511111";
		assertFalse(nameValidator.isValid(name));
	}
	
	@Test
	void shouldFailValidatingNameWithNumbersAmongTheLetters() {
		String name = "J0hn Doe";
		assertFalse(nameValidator.isValid(name));
	}
	
	@Test
	void shouldFailValidatingEmptyStringForName() {
		String name = "";
		assertFalse(nameValidator.isValid(name));
	}

}
