package com.cccat.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() throws SQLException {
		String url = "jdbc:postgresql://localhost:5432/curso_branas";
		String user = "postgres";
		String password = "root";
		return DriverManager.getConnection(url, user, password);
	}

}
