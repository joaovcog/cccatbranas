package com.cccat.domain.account.validation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.sql.SQLException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.cccat.domain.account.model.Account;
import com.cccat.infrastructure.persistence.AccountRepositoryImpl;
import com.cccat.shared.ValidationException;

class ExistingAccountValidatorTest {

	private static final String NULL_EMPTY_VALIDATION_MESSAGE = "No input for the e-mail! Please, type a valid e-mail for signing up.";
	private static final String EXCEPTION_MESSAGE = "An account with the e-mail %s already exists! Please, type another e-mail for creating a new account.";
	private static final String EMAIL = "john@email.com";

	private ExistingAccountValidator existingAccountValidator;

	@Mock
	private AccountRepositoryImpl accountRepository;

	@BeforeEach
	public void setup() {
		openMocks(this);
		existingAccountValidator = new ExistingAccountValidator(accountRepository);
	}

	@Test
	void shouldValidateThatAccountWithInputEmailDoesntExists() {
		assertDoesNotThrow(() -> existingAccountValidator.validate(EMAIL));
	}

	@Test
	void shouldFailValidatingWhenAccountAlreadyExistsForInputEmail() throws SQLException {
		Account account = new Account();
		account.setEmail(EMAIL);
		when(accountRepository.findByEmail(EMAIL)).thenReturn(Optional.of(account));

		ValidationException ex = assertThrows(ValidationException.class,
				() -> existingAccountValidator.validate(EMAIL));
		assertEquals(String.format(EXCEPTION_MESSAGE, EMAIL), ex.getMessage());
	}

	@Test
	void shouldFailValidatingEmptyStringForEmail() {
		String email = "";
		ValidationException ex = assertThrows(ValidationException.class,
				() -> existingAccountValidator.validate(email));
		assertEquals(ex.getMessage(), NULL_EMPTY_VALIDATION_MESSAGE);
	}

	@Test
	void shouldFailValidatingNullValueForEmail() {
		String email = null;
		ValidationException ex = assertThrows(ValidationException.class,
				() -> existingAccountValidator.validate(email));
		assertEquals(ex.getMessage(), NULL_EMPTY_VALIDATION_MESSAGE);
	}

}
