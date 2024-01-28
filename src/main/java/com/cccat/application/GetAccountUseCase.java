package com.cccat.application;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.cccat.domain.account.model.Account;
import com.cccat.domain.account.service.AccountService;

@Component
public class GetAccountUseCase {

	private AccountService accountService;

	public GetAccountUseCase(AccountService accountService) {
		this.accountService = accountService;
	}

	public Optional<Account> execute(String accountId) {
		// TODO Throw exception if account is not found and create a Controller Advice to manage exceptions
		return accountService.findById(accountId);
	}

}
