package com.cccat.infrastructure.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cccat.application.GetAccountUseCase;
import com.cccat.application.SignupUseCase;
import com.cccat.domain.account.model.Account;
import com.cccat.infrastructure.api.converter.AccountDtoConverter;
import com.cccat.infrastructure.api.dto.AccountInputDto;
import com.cccat.infrastructure.api.dto.AccountOutputDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	private SignupUseCase signupUseCase;
	private GetAccountUseCase getAccountUseCase;

	public AccountController(SignupUseCase signupUseCase, GetAccountUseCase getAccountUseCase) {
		this.signupUseCase = signupUseCase;
		this.getAccountUseCase = getAccountUseCase;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AccountOutputDto saveAccount(@RequestBody @Valid AccountInputDto accountInputDto) {
		AccountDtoConverter accountDtoConverter = new AccountDtoConverter();
		Account account = accountDtoConverter.fromInputDto(accountInputDto);
		account = signupUseCase.signup(account);
		return accountDtoConverter.toOutputDto(account);
	}

	@GetMapping("/{accountId}")
	@ResponseStatus(HttpStatus.OK)
	public AccountOutputDto getAccountById(@PathVariable String accountId) {
		AccountDtoConverter accountDtoConverter = new AccountDtoConverter(); // TODO turn this converter into a
																				// component
		Account account = getAccountUseCase.execute(accountId);
		return accountDtoConverter.toOutputDto(account);
	}

}
