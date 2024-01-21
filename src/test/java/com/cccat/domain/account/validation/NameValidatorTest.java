package com.cccat.domain.account.validation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cccat.shared.ValidationException;

class NameValidatorTest {

	private static final String NULL_EMPTY_VALIDATION_MESSAGE = "No input for the name! Please, type a valid name for signing up.";
	private static final String EXCEPTION_MESSAGE = "Invalid name! The name should have only letters.";
	
	private NameValidator nameValidator;
	
	@BeforeEach
	public void setup() {
		nameValidator = new NameValidator();
	}
	
	@Test
	void shouldValidateNameSuccesfullyWithProperPattern() {
		String name = "John Doe";
		assertDoesNotThrow(() -> nameValidator.validate(name));
	}
	
	@Test
	void shouldFailValidatingNameWithNumberInTheFirstName() {
		String name = "55555 Doe";
		ValidationException ex = assertThrows(ValidationException.class, () -> nameValidator.validate(name));
		assertEquals(ex.getMessage(), EXCEPTION_MESSAGE);
	}
	
	@Test
	void shouldFailValidatingNameWithOnlyNumbers() {
		String name = "5555511111";
		ValidationException ex = assertThrows(ValidationException.class, () -> nameValidator.validate(name));
		assertEquals(ex.getMessage(), EXCEPTION_MESSAGE);
	}
	
	@Test
	void shouldFailValidatingNameWithNumbersAmongTheLetters() {
		String name = "J0hn Doe";
		ValidationException ex = assertThrows(ValidationException.class, () -> nameValidator.validate(name));
		assertEquals(ex.getMessage(), EXCEPTION_MESSAGE);
	}
	
	@Test
	void shouldFailValidatingEmptyStringForName() {
		String name = "";
		ValidationException ex = assertThrows(ValidationException.class, () -> nameValidator.validate(name));
		assertEquals(ex.getMessage(), NULL_EMPTY_VALIDATION_MESSAGE);
	}
	
	@Test
	void shouldFailValidatingNullValueForName() {
		String name = null;
		ValidationException ex = assertThrows(ValidationException.class, () -> nameValidator.validate(name));
		assertEquals(ex.getMessage(), NULL_EMPTY_VALIDATION_MESSAGE);
	}

}
