package com.cccat.signup;

import java.sql.SQLException;
import java.util.UUID;

public class SignupUseCase {

	public Account signup(Account account) {
		AccountRepository accountRepository = new AccountRepository();
		account.setAccountId(UUID.randomUUID());
		validateAccount(account);
		try {
			return accountRepository.create(account);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void validateAccount(Account account) {
		new NameValidator().validate(account.getName());
		new EmailValidator().validate(account.getEmail());
		new CpfValidator().validate(account.getCpf());
		if (account.isDriverAccount()) {
			new CarPlateValidator().validate(account.getCarPlate());
		}
		new ExistingAccountValidator().validate(account.getEmail());
	}

}
