package com.cccat.domain.account;

import com.cccat.infrastructure.persistence.AccountRepositoryImpl;
import com.cccat.shared.ValidationException;

public class ExistingAccountValidator extends Validator {

	private AccountRepository accountRepository;

	public ExistingAccountValidator() {
		this(new AccountRepositoryImpl());
	}

	public ExistingAccountValidator(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	protected void applyValidation(String email) {
		if (accountRepository.findByEmail(email).isPresent()) {
			throw new ValidationException(String.format("An account with the e-mail %s already exists! "
					+ "Please, type another e-mail for creating a new account.", email));
		}
	}

	@Override
	protected String getNullOrEmptyValidationMessage() {
		return createNullOrEmptyMessage("e-mail");
	}

}
