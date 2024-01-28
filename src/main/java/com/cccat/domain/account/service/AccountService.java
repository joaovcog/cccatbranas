package com.cccat.domain.account.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.cccat.domain.account.model.Account;
import com.cccat.domain.account.model.AccountRepository;
import com.cccat.domain.account.validation.AccountIdValidator;
import com.cccat.domain.account.validation.CarPlateValidator;
import com.cccat.domain.account.validation.CpfValidator;
import com.cccat.domain.account.validation.EmailValidator;
import com.cccat.domain.account.validation.ExistingAccountValidator;
import com.cccat.domain.account.validation.NameValidator;

@Service
public class AccountService {

	private AccountRepository accountRepository;

	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public Account create(Account account) {
		account.setAccountId(UUID.randomUUID());
		validateAccount(account);
		return accountRepository.create(account);
	}

	private void validateAccount(Account account) {
		new ExistingAccountValidator().validate(account.getEmail());
		new NameValidator().validate(account.getName());
		new EmailValidator().validate(account.getEmail());
		new CpfValidator().validate(account.getCpf());
		if (account.isDriverAccount()) {
			new CarPlateValidator().validate(account.getCarPlate());
		}
	}

	public Optional<Account> findById(String accountId) {
		new AccountIdValidator().validate(accountId);
		return accountRepository.findById(UUID.fromString(accountId));
	}

}
