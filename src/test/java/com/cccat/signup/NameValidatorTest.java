package com.cccat.signup;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NameValidatorTest {

	@Test
	void shouldValidateNameSuccesfullyWithProperPattern() {
		String name = "John Doe";
		assertTrue(NameValidator.isValid(name));
	}
	
	@Test
	void shouldFailValidatingNameWithNumberInTheFirstName() {
		String name = "55555 Doe";
		assertFalse(NameValidator.isValid(name));
	}
	
	@Test
	void shouldFailValidatingNameWithOnlyNumbers() {
		String name = "5555511111";
		assertFalse(NameValidator.isValid(name));
	}
	
	@Test
	void shouldFailValidatingNameWithNumbersAmongTheLetters() {
		String name = "J0hn Doe";
		assertFalse(NameValidator.isValid(name));
	}
	
	@Test
	void shouldFailValidatingEmptyStringForName() {
		String name = "";
		assertFalse(NameValidator.isValid(name));
	}

}
