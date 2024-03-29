package com.cccat.infrastructure.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.cccat.domain.account.model.Account;
import com.cccat.domain.account.model.AccountRepository;
import com.cccat.infrastructure.ConnectionFactory;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountRepositoryImpl.class);
	
	public Account create(Account account) {
		String insertQuery = "INSERT INTO cccat15.account (account_id, name, email, cpf, car_plate, is_passenger, is_driver) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement insertStatement = new ConnectionFactory().getConnection().prepareStatement(insertQuery)) {
			insertStatement.setObject(1, account.getAccountId());
			insertStatement.setString(2, account.getName());
			insertStatement.setString(3, account.getEmail());
			insertStatement.setString(4, account.getCpf());
			insertStatement.setString(5, account.getCarPlate());
			insertStatement.setBoolean(6, account.isPassengerAccount());
			insertStatement.setBoolean(7, account.isDriverAccount());
			insertStatement.executeUpdate();
			
			return account;
		} catch (SQLException e) {
			String exceptionDescription = "Error trying to create a new account.";
			LOGGER.error(exceptionDescription);
			throw new RuntimeException(exceptionDescription, e);
		}
	}
	
	@Override
	public Optional<Account> findById(UUID accountId) {
		String selectQuery = "SELECT * FROM cccat15.account WHERE account_id = ?";
        try (PreparedStatement selectStatement = new ConnectionFactory().getConnection().prepareStatement(selectQuery)) {
            selectStatement.setObject(1, accountId);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
            	return Optional.of(extractAccountFromResultSet(resultSet));
            }
        } catch (SQLException e) {
			String exceptionDescription = "Error trying to find account by ID.";
			LOGGER.error(exceptionDescription);
			throw new RuntimeException(exceptionDescription, e);
		}
        return Optional.empty();
	}

	public Optional<Account> findByEmail(String email) {
		String selectQuery = "SELECT * FROM cccat15.account WHERE email = ?";
        try (PreparedStatement selectStatement = new ConnectionFactory().getConnection().prepareStatement(selectQuery)) {
            selectStatement.setString(1, email);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
            	return Optional.of(extractAccountFromResultSet(resultSet));
            }
        } catch (SQLException e) {
			String exceptionDescription = "Error trying to find account by e-mail.";
			LOGGER.error(exceptionDescription);
			throw new RuntimeException(exceptionDescription, e);
		}
        return Optional.empty();
	}
	
	private Account extractAccountFromResultSet(ResultSet resultSet) throws SQLException {
		Account account = new Account();
		account.setAccountId((UUID) resultSet.getObject("account_id"));
		account.setName(resultSet.getString("name"));
		account.setEmail(resultSet.getString("email"));
		account.setCpf(resultSet.getString("cpf"));
		account.setCarPlate(resultSet.getString("car_plate"));
		account.setPassengerAccount(resultSet.getBoolean("is_passenger"));
		account.setDriverAccount(resultSet.getBoolean("is_driver"));
		return account;
	}

}
