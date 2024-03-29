package com.cccat.application;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cccat.domain.account.model.Account;
import com.cccat.domain.account.service.AccountService;
import com.cccat.infrastructure.ConnectionFactory;
import com.cccat.infrastructure.persistence.AccountRepositoryImpl;

class SignupUseCaseTest {

	private SignupUseCase signupUseCase;
	
	@BeforeEach
	public void setup() {
		signupUseCase = new SignupUseCase(new AccountService(new AccountRepositoryImpl()));
	}
	
	@AfterEach
	public void tearDown() throws SQLException {
		cleanAccountTable();
	}

	private void cleanAccountTable() throws SQLException {
        try (Connection connection = new ConnectionFactory().getConnection();
        		Statement statement = connection.createStatement()) {
        	statement.executeUpdate("delete from cccat15.account");
        }
	}
	
	@Test
	void shouldSignUpPassengerSuccessfully() {
		Account account = getPassengerAccount();
		Account returnedAccount = (Account) signupUseCase.signup(account);
		Pattern UUID_REGEX = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
		
		assertTrue(!Objects.isNull(returnedAccount));
		assertTrue(UUID_REGEX.matcher(returnedAccount.getAccountId().toString()).matches());
	}
	
	@Test
	void shouldSignUpDriverSuccessfully() {
		Account account = getDriverAccount();
		Account returnedAccount = (Account) signupUseCase.signup(account);
		Pattern UUID_REGEX = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
		
		assertTrue(!Objects.isNull(returnedAccount));
		assertTrue(UUID_REGEX.matcher(returnedAccount.getAccountId().toString()).matches());
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
