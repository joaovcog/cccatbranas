package com.cccat.application;

import com.cccat.domain.account.model.Account;
import com.cccat.domain.account.service.AccountService;

public class SignupUseCase {
	
	private AccountService accountService;
	
	public SignupUseCase(AccountService accountService) {
		this.accountService = accountService;
	}

	public Account signup(Account account) {
		return accountService.create(account);
	}

}
