package com.cccat.signup;

import java.sql.SQLException;
import java.util.UUID;

public class SignupUseCase {
	
    public static Object signup(Account account) {
    	AccountRepository accountRepository = new AccountRepository();
		account.setAccountId(UUID.randomUUID());
		try {
			if (accountRepository.findByEmail(account.getEmail()).isEmpty()) {
				if (NameValidator.isValid(account.getName())) {
					if (EmailValidator.isValid(account.getEmail())) {
						if (CpfValidator.isValid(account.getCpf())) {
							if (account.isDriverAccount()) {
								if (CarPlateValidator.isValid(account.getCarPlate())) {
									return accountRepository.create(account);
								} else {
									// invalid car plate
									return -5;
								}
							} else {
								return accountRepository.create(account);
							}
						} else {
							// invalid cpf
							return -1;
						}
					} else {
						// invalid email
						return -2;
					}
				} else {
					// invalid name
					return -3;
				}
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
