package com.cccat.domain.account.validation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cccat.shared.ValidationException;

class AccountIdValidatorTest {

	private static final String NULL_EMPTY_VALIDATION_MESSAGE = "No input for the Account ID!";
	private static final String EXCEPTION_MESSAGE = "Invalid pattern for account ID! The ID should follow the UUID pattern.";
	
	private AccountIdValidator accountIdValidator;
	
	@BeforeEach
	public void setup() {
		accountIdValidator = new AccountIdValidator();
	}
	
	@Test
	void shouldValidateAccountIdSuccesfullyWithProperPattern() {
		String accountId = UUID.randomUUID().toString();
		assertDoesNotThrow(() -> accountIdValidator.validate(accountId));
	}
	
	@Test
	void shouldFailValidatingAccountIdWithValueDifferenteFromUUID() {
		String accountId = "AAAAAAA";
		ValidationException ex = assertThrows(ValidationException.class, () -> accountIdValidator.validate(accountId));
		assertEquals(ex.getMessage(), EXCEPTION_MESSAGE);
	}
	
	@Test
	void shouldFailValidatingNullValueForAccountId() {
		String accountId = null;
		ValidationException ex = assertThrows(ValidationException.class, () -> accountIdValidator.validate(accountId));
		assertEquals(ex.getMessage(), NULL_EMPTY_VALIDATION_MESSAGE);
	}
	
	@Test
	void shouldFailValidatingEmptyValueForAccountId() {
		String accountId = "";
		ValidationException ex = assertThrows(ValidationException.class, () -> accountIdValidator.validate(accountId));
		assertEquals(ex.getMessage(), NULL_EMPTY_VALIDATION_MESSAGE);
	}

}
