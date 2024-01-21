package com.cccat.signup;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cccat.domain.account.EmailValidator;
import com.cccat.shared.ValidationException;

class EmailValidatorTest {

	private static final String NULL_EMPTY_VALIDATION_MESSAGE = "No input for the e-mail! Please, type a valid e-mail for signing up.";
	private static final String EXCEPTION_MESSAGE = "Invalid e-mail! Please, type a valid e-mail for signing up.";
	
	private EmailValidator emailValidator;
	
	@BeforeEach
	public void setup() {
		emailValidator = new EmailValidator();
	}
	
	@Test
	void shouldValidateEmailSuccesfullyWithProperPattern() {
		String email = "john@email.com";
		assertDoesNotThrow(() -> emailValidator.validate(email));
	}
	
	@Test
	void shouldFailValidatingEmailWithMissingDomain() {
		String email = "john@";
		ValidationException ex = assertThrows(ValidationException.class, () -> emailValidator.validate(email));
		assertEquals(ex.getMessage(), EXCEPTION_MESSAGE);
	}
	
	@Test
	void shouldFailValidatingEmailWithOnlyNumbers() {
		String email = "55555";
		ValidationException ex = assertThrows(ValidationException.class, () -> emailValidator.validate(email));
		assertEquals(ex.getMessage(), EXCEPTION_MESSAGE);
	}
	
	@Test
	void shouldFailValidatingEmptyStringForEmail() {
		String email = "";
		ValidationException ex = assertThrows(ValidationException.class, () -> emailValidator.validate(email));
		assertEquals(ex.getMessage(), NULL_EMPTY_VALIDATION_MESSAGE);
	}
	
	@Test
	void shouldFailValidatingNullValueForEmail() {
		String email = null;
		ValidationException ex = assertThrows(ValidationException.class, () -> emailValidator.validate(email));
		assertEquals(ex.getMessage(), NULL_EMPTY_VALIDATION_MESSAGE);
	}

}
