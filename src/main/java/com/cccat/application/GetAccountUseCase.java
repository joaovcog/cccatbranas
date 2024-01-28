package com.cccat.application;

import org.springframework.stereotype.Component;

import com.cccat.domain.account.model.Account;
import com.cccat.domain.account.service.AccountService;

@Component
public class GetAccountUseCase {

	private AccountService accountService;

	public GetAccountUseCase(AccountService accountService) {
		this.accountService = accountService;
	}

	public Account execute(String accountId) {
		return accountService.findById(accountId);
	}

}
