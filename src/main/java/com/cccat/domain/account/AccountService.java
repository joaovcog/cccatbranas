package com.cccat.domain.account;

import java.util.UUID;

import com.cccat.infrastructure.persistence.AccountRepositoryImpl;

public class AccountService {

	private AccountRepository accountRepository;

	public AccountService() {
		this(new AccountRepositoryImpl());
	}

	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	public Account create(Account account) {
		account.setAccountId(UUID.randomUUID());
		validateAccount(account);
		return accountRepository.create(account);
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
