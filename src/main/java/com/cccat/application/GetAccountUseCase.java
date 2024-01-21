package com.cccat.application;

import java.util.Optional;

import com.cccat.domain.account.model.Account;
import com.cccat.domain.account.service.AccountService;

public class GetAccountUseCase {

	private AccountService accountService;

	public GetAccountUseCase(AccountService accountService) {
		this.accountService = accountService;
	}

	public Optional<Account> execute(String accountId) {
		return accountService.findById(accountId);
	}

}
