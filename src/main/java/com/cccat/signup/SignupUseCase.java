package com.cccat.signup;

import java.sql.SQLException;
import java.util.UUID;

public class SignupUseCase {

	public Object signup(Account account) {
		AccountRepository accountRepository = new AccountRepository();
		account.setAccountId(UUID.randomUUID());

		new NameValidator().validate(account.getName());
		new EmailValidator().validate(account.getEmail());
		new CpfValidator().validate(account.getCpf());
		if (account.isDriverAccount()) {
			new CarPlateValidator().validate(account.getCarPlate());
		}

		try {
			if (accountRepository.findByEmail(account.getEmail()).isEmpty()) {
				return accountRepository.create(account);
			} else {
				// already exists
				return -4;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
