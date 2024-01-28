package com.cccat.infrastructure.api.converter;

import com.cccat.domain.account.model.Account;
import com.cccat.infrastructure.api.dto.AccountInputDto;
import com.cccat.infrastructure.api.dto.AccountOutputDto;

public class AccountDtoConverter {

	public Account fromInputDto(AccountInputDto accountInputDto) {
		Account account = new Account();
		
		account.setName(accountInputDto.getName());
		account.setEmail(accountInputDto.getEmail());
		account.setCpf(accountInputDto.getCpf());
		account.setCarPlate(accountInputDto.getCarPlate());
		account.setPassengerAccount(accountInputDto.isPassengerAccount());
		account.setDriverAccount(accountInputDto.isDriverAccount());
		
		return account;
	}
	
	public AccountOutputDto toOutputDto(Account account) {
		AccountOutputDto accountOutputDto = new AccountOutputDto();
		
		accountOutputDto.setAccountId(account.getAccountId().toString());
		accountOutputDto.setName(account.getName());
		accountOutputDto.setEmail(account.getEmail());
		accountOutputDto.setCpf(account.getCpf());
		accountOutputDto.setCarPlate(account.getCarPlate());
		accountOutputDto.setPassengerAccount(account.isPassengerAccount());
		accountOutputDto.setDriverAccount(account.isDriverAccount());
		
		return accountOutputDto;
	}
	
}
