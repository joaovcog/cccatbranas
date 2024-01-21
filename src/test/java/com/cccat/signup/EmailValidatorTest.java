package com.cccat.signup;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmailValidatorTest {

	private EmailValidator emailValidator;
	
	@BeforeEach
	public void setup() {
		emailValidator = new EmailValidator();
	}
	
	@Test
	void shouldValidateEmailSuccesfullyWithProperPattern() {
		String email = "john@email.com";
		assertTrue(emailValidator.isValid(email));
	}
	
	@Test
	void shouldFailValidatingEmailWithMissingDomain() {
		String email = "john@";
		assertFalse(emailValidator.isValid(email));
	}
	
	@Test
	void shouldFailValidatingEmailWithOnlyNumbers() {
		String email = "55555";
		assertFalse(emailValidator.isValid(email));
	}
	
	@Test
	void shouldFailValidatingEmptyStringForEmail() {
		String email = "";
		assertFalse(emailValidator.isValid(email));
	}

}
