package com.cccat.domain.account;

import java.util.Optional;

public interface AccountRepository {

	Account create(Account account);
	
	Optional<Account> findByEmail(String email);
	
}
