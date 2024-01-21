package com.cccat.domain.account;

import java.util.UUID;

public class Account {
	
	private UUID accountId;
	private String name;
	private String email;
	private String cpf;
	private String carPlate;
	private boolean driverAccount;
	private boolean passengerAccount;
	
	public UUID getAccountId() {
		return accountId;
	}
	
	public void setAccountId(UUID accountId) {
		this.accountId = accountId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getCarPlate() {
		return carPlate;
	}
	
	public void setCarPlate(String carPlate) {
		this.carPlate = carPlate;
	}

	public boolean isDriverAccount() {
		return driverAccount;
	}

	public void setDriverAccount(boolean driverAccount) {
		this.driverAccount = driverAccount;
	}

	public boolean isPassengerAccount() {
		return passengerAccount;
	}

	public void setPassengerAccount(boolean passengerAccount) {
		this.passengerAccount = passengerAccount;
	}

}
