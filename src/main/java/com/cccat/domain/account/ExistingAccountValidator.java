package com.cccat.domain.account;

import com.cccat.infrastructure.persistence.AccountRepositoryImpl;
import com.cccat.shared.ValidationException;

public class ExistingAccountValidator {

	private AccountRepository accountRepository;
	
	public ExistingAccountValidator() {
		this(new AccountRepositoryImpl());
	}

	public ExistingAccountValidator(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public void validate(String email) {
		if (accountRepository.findByEmail(email).isPresent()) {
			throw new ValidationException(String.format("An account with the e-mail %s already exists! "
					+ "Please, type another e-mail for creating a new account.", email));
		}
	}

}
