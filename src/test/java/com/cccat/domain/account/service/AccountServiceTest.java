package com.cccat.domain.account.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import java.util.regex.Pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.cccat.domain.account.model.Account;
import com.cccat.domain.account.model.AccountRepository;
import com.cccat.shared.ValidationException;

class AccountServiceTest {

	private AccountService accountService;

	@Mock
	private AccountRepository accountRepository;

	@BeforeEach
	public void setup() {
		openMocks(this);
		this.accountService = new AccountService(accountRepository);
	}

	@Test
	void shouldCreateAccountForPassengerWithoutFailures() {
		Account account = getPassengerAccount();
		when(accountRepository.create(account)).thenReturn(account);
		Account returnedAccount = accountService.create(account);
		Pattern UUID_REGEX = Pattern
				.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
		assertTrue(UUID_REGEX.matcher(returnedAccount.getAccountId().toString()).matches());
		verify(accountRepository, times(1)).create(account);
	}
	
	@Test
	void shouldFailCreatingAccountForPassengerWhenNoNameFilled() {
		Account account = getPassengerAccount();
		account.setName("");
		when(accountRepository.create(account)).thenReturn(account);
		assertThrows(ValidationException.class, () -> accountService.create(account));
	}
	
	@Test
	void shouldFailCreatingAccountForPassengerWhenInvalidEmail() {
		Account account = getPassengerAccount();
		account.setEmail("joao");
		when(accountRepository.create(account)).thenReturn(account);
		assertThrows(ValidationException.class, () -> accountService.create(account));
	}
	
	@Test
	void shouldFailCreatingAccountForPassengerWhenInvalidCpf() {
		Account account = getPassengerAccount();
		account.setCpf("111333222");
		when(accountRepository.create(account)).thenReturn(account);
		assertThrows(ValidationException.class, () -> accountService.create(account));
	}

	@Test
	void shouldCreateAccountForDriverWithoutFailures() {
		Account account = getDriverAccount();
		when(accountRepository.create(account)).thenReturn(account);
		Account returnedAccount = accountService.create(account);
		Pattern UUID_REGEX = Pattern
				.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
		assertTrue(UUID_REGEX.matcher(returnedAccount.getAccountId().toString()).matches());
		verify(accountRepository, times(1)).create(account);
	}

	@Test
	void shouldFailCreatingAccountForDriverWhenNoCarPlatesFilled() {
		Account account = getDriverAccount();
		account.setCarPlate(null);
		when(accountRepository.create(account)).thenReturn(account);
		assertThrows(ValidationException.class, () -> accountService.create(account));
	}

	private Account getPassengerAccount() {
		Account account = new Account();
		account.setName("John Doe");
		account.setEmail("john@example.com");
		account.setCpf("96311015099");
		account.setCarPlate(null);
		account.setPassengerAccount(true);
		account.setDriverAccount(false);
		return account;
	}

	private Account getDriverAccount() {
		Account account = new Account();
		account.setName("John Doe");
		account.setEmail("john.driver@example.com");
		account.setCpf("96311015099");
		account.setCarPlate("AAA1111");
		account.setPassengerAccount(false);
		account.setDriverAccount(true);
		return account;
	}

}
