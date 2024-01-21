package com.cccat.signup;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CpfValidatorTest {
	
	private CpfValidator cpfValidator;
	
	@BeforeEach
	public void setup() {
		cpfValidator = new CpfValidator();
	}

	@Test
	void shouldValidateCpfSuccesfullyWithElevenNumbersAndNoSpecialCharacters() {
		String cpf = "96311015099";
		assertTrue(cpfValidator.isValid(cpf));
	}
	
	@Test
	void shouldValidateCpfSuccesfullyWithElevenNumbersAndMask() {
		String cpf = "963.110.150-99";
		assertTrue(cpfValidator.isValid(cpf));
	}
	
	@Test
	void shouldFailValidatingInvalidCpf() {
		String cpf = "12345678910";
		assertFalse(cpfValidator.isValid(cpf));
	}
	
	@Test
	void shouldFailValidatingCpfWithTenNumbers() {
		String cpf = "9631101509";
		assertFalse(cpfValidator.isValid(cpf));
	}
	
	@Test
	void shouldFailValidatingCpfWithTwelveNumbers() {
		String cpf = "963110150992";
		assertFalse(cpfValidator.isValid(cpf));
	}
	
	@Test
	void shouldFailValidatingNullValueForCpf() {
		String cpf = null;
		assertFalse(cpfValidator.isValid(cpf));
	}
	
	@Test
	void shouldFailValidatingEmptyStringForCpf() {
		String cpf = "";
		assertFalse(cpfValidator.isValid(cpf));
	}

}
