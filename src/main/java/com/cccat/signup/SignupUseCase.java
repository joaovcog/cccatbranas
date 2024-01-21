package com.cccat.signup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SignupUseCase {
    public static Object signup(Account account) {
        String url = "jdbc:postgresql://localhost:5432/curso_branas";
        String user = "postgres";
        String password = "root";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);

            UUID id = UUID.randomUUID();

            String selectQuery = "SELECT * FROM cccat15.account WHERE email = ?";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                selectStatement.setString(1, account.getEmail());
                ResultSet resultSet = selectStatement.executeQuery();
                if (!resultSet.next()) {
                    if (NameValidator.isValid(account.getName())) {
                        if (EmailValidator.isValid(account.getEmail())) {
                            if (CpfValidator.isValid(account.getCpf())) {
                                if (account.isDriverAccount()) {
                                    if (CarPlateValidator.isValid(account.getCarPlate())) {
                                        String insertQuery = "INSERT INTO cccat15.account (account_id, name, email, cpf, car_plate, is_passenger, is_driver) VALUES (?, ?, ?, ?, ?, ?, ?)";
                                        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                                            insertStatement.setObject(1, id);
                                            insertStatement.setString(2, account.getName());
                                            insertStatement.setString(3, account.getEmail());
                                            insertStatement.setString(4, account.getCpf());
                                            insertStatement.setString(5, account.getCarPlate());
                                            insertStatement.setBoolean(6, account.isPassengerAccount());
                                            insertStatement.setBoolean(7, account.isDriverAccount());
                                            insertStatement.executeUpdate();

                                            return new Object[]{"accountId", id};
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        // invalid car plate
                                        return -5;
                                    }
                                } else {
                                    String insertQuery = "INSERT INTO cccat15.account (account_id, name, email, cpf, car_plate, is_passenger, is_driver) VALUES (?, ?, ?, ?, ?, ?, ?)";
                                    try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                                    	insertStatement.setObject(1, id);
                                        insertStatement.setString(2, account.getName());
                                        insertStatement.setString(3, account.getEmail());
                                        insertStatement.setString(4, account.getCpf());
                                        insertStatement.setString(5, account.getCarPlate());
                                        insertStatement.setBoolean(6, account.isPassengerAccount());
                                        insertStatement.setBoolean(7, account.isDriverAccount());
                                        insertStatement.executeUpdate();

                                        return new Object[]{"accountId", id};
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
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

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        // Test the signup function
        Account acount = new Account();
        
        acount.setName("John Doe");
        acount.setEmail("john@example.com");
        acount.setCpf("12345678901");
        acount.setCarPlate(null);
        acount.setPassengerAccount(true);
        acount.setDriverAccount(false);
        
        Object result = signup(acount);
        System.out.println(result);
    }
}
