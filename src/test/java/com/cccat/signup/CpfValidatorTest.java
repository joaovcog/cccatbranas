package com.cccat.signup;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CpfValidatorTest {

	@Test
	void shouldValidateCpfSuccesfullyWithElevenNumbersAndNoSpecialCharacters() {
		String cpf = "96311015099";
		assertTrue(CpfValidator.isValid(cpf));
	}
	
	@Test
	void shouldValidateCpfSuccesfullyWithElevenNumbersAndMask() {
		String cpf = "963.110.150-99";
		assertTrue(CpfValidator.isValid(cpf));
	}
	
	@Test
	void shouldFailValidatingInvalidCpf() {
		String cpf = "12345678910";
		assertFalse(CpfValidator.isValid(cpf));
	}
	
	@Test
	void shouldFailValidatingCpfWithTenNumbers() {
		String cpf = "9631101509";
		assertFalse(CpfValidator.isValid(cpf));
	}
	
	@Test
	void shouldFailValidatingCpfWithTwelveNumbers() {
		String cpf = "963110150992";
		assertFalse(CpfValidator.isValid(cpf));
	}
	
	@Test
	void shouldFailValidatingNullValueForCpf() {
		String cpf = null;
		assertFalse(CpfValidator.isValid(cpf));
	}
	
	@Test
	void shouldFailValidatingEmptyStringForCpf() {
		String cpf = "";
		assertFalse(CpfValidator.isValid(cpf));
	}

}
