package com.cccat.signup;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExistingAccountValidator {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExistingAccountValidator.class);
	
	private AccountRepository accountRepository;
	
	public ExistingAccountValidator() {
		this(new AccountRepository());
	}

	public ExistingAccountValidator(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public void validate(String email) {
		try {
			if (accountRepository.findByEmail(email).isPresent()) {
				throw new ValidationException(String.format("An account with the e-mail %s already exists! Please, type another e-mail for creating a new account.", email));
			}
		} catch (SQLException e) {
			String exceptionDescription = "Error trying to validate existing account.";
			LOGGER.error(exceptionDescription);
			throw new RuntimeException(exceptionDescription, e);
		}
	}

}
