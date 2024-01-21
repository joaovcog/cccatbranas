package com.cccat.signup;

import java.sql.SQLException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class); 
	
	private AccountRepository accountRepository;

	public AccountService() {
		this(new AccountRepository());
	}

	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	public Account create(Account account) {
		account.setAccountId(UUID.randomUUID());
		validateAccount(account);
		try {
			return accountRepository.create(account);
		} catch (SQLException e) {
			String exceptionDescription = "Error trying to create a new account.";
			LOGGER.error(exceptionDescription);
			throw new RuntimeException(exceptionDescription, e);
		}
	}
	
	private void validateAccount(Account account) {
		new NameValidator().validate(account.getName());
		new EmailValidator().validate(account.getEmail());
		new CpfValidator().validate(account.getCpf());
		if (account.isDriverAccount()) {
			new CarPlateValidator().validate(account.getCarPlate());
		}
		new ExistingAccountValidator().validate(account.getEmail());
	}

}
