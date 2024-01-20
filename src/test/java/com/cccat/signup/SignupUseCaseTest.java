package com.cccat.signup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class SignupUseCaseTest {

	@AfterEach
	public void tearDown() throws SQLException {
		cleanAccountTable();
	}

	private void cleanAccountTable() throws SQLException {
		String url = "jdbc:postgresql://localhost:5432/curso_branas";
        String user = "postgres";
        String password = "root";
        Connection connection =  DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        statement.executeUpdate("delete from cccat15.account");
	}
	
	@Test
	void shouldSignUpSuccessfully() {
		Object input = new Object[]{"Johnn Doe", true, "joh222n@example.com", "12345678901", null, true, false};
		Object objectWithAccountId = SignupUseCase.signup(input);
		Object[] arrayFromObject = (Object[]) objectWithAccountId;
		Pattern UUID_REGEX = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
		
		assertTrue(!Objects.isNull(objectWithAccountId));
		assertTrue(arrayFromObject.length == 2);
		assertEquals("accountId", arrayFromObject[0]);
		assertTrue(UUID_REGEX.matcher(((UUID) arrayFromObject[1]).toString()).matches());
	}

}
