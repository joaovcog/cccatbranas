package com.cccat.domain.account.model;

import java.util.Optional;

public interface AccountRepository {

	Account create(Account account);
	
	Optional<Account> findByEmail(String email);
	
}
