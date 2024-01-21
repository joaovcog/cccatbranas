package com.cccat.domain.account;

public class SignupUseCase {
	
	private AccountService accountService;
	
	public SignupUseCase(AccountService accountService) {
		this.accountService = accountService;
	}

	public Account signup(Account account) {
		return accountService.create(account);
	}

}
