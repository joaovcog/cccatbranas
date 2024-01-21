package com.cccat.signup;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmailValidatorTest {

	@Test
	void shouldValidateEmailSuccesfullyWithProperPattern() {
		String email = "john@email.com";
		assertTrue(EmailValidator.isValid(email));
	}
	
	@Test
	void shouldFailValidatingEmailWithMissingDomain() {
		String email = "john@";
		assertFalse(EmailValidator.isValid(email));
	}
	
	@Test
	void shouldFailValidatingEmailWithOnlyNumbers() {
		String email = "55555";
		assertFalse(EmailValidator.isValid(email));
	}
	
	@Test
	void shouldFailValidatingEmptyStringForEmail() {
		String email = "";
		assertFalse(EmailValidator.isValid(email));
	}

}
