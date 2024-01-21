package com.cccat.signup;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.sql.SQLException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class ExistingAccountValidatorTest {

	private static final String EXCEPTION_MESSAGE = "An account with the e-mail %s already exists! Please, type another e-mail for creating a new account.";
	private static final String EMAIL = "john@email.com";

	private ExistingAccountValidator existingAccountValidator;
	
	@Mock
	private AccountRepository accountRepository;

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
		
		ValidationException ex = assertThrows(ValidationException.class, () -> existingAccountValidator.validate(EMAIL));
		assertEquals(String.format(EXCEPTION_MESSAGE, EMAIL), ex.getMessage());
	}

}
