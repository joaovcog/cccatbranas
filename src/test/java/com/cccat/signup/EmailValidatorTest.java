package com.cccat.signup;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmailValidatorTest {

	@Test
	void shouldValidateEmailSuccesfullyWithProperPattern() {
		String name = "john@email.com";
		assertTrue(EmailValidator.isValid(name));
	}
	
	@Test
	void shouldFailValidatingEmailWithMissingDomain() {
		String name = "john@";
		assertFalse(EmailValidator.isValid(name));
	}
	
	@Test
	void shouldFailValidatingEmailWithOnlyNumbers() {
		String name = "55555";
		assertFalse(EmailValidator.isValid(name));
	}
	
	@Test
	void shouldFailValidatingEmptyStringForEmail() {
		String name = "";
		assertFalse(EmailValidator.isValid(name));
	}

}
