package com.cccat.signup;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cccat.domain.account.CpfValidator;
import com.cccat.shared.ValidationException;

class CpfValidatorTest {
	
	private static final String NO_INPUT_CPF_EXCEPTION_MESSAGE = "No input for the CPF! Please, type a valid CPF for signing up.";
	private static final String INVALID_LENGTH_CPF_EXCEPTION_MESSAGE = "Invalid length for CPF! The CPF must have 11 number digits.";
	private static final String INVALID_CPF_EXCEPTION_MESSAGE = "Invalid CPF! Please, type a valid CPF for signing up.";
	
	private CpfValidator cpfValidator;
	
	@BeforeEach
	public void setup() {
		cpfValidator = new CpfValidator();
	}

	@Test
	void shouldValidateCpfSuccesfullyWithElevenNumbersAndNoSpecialCharacters() {
		String cpf = "96311015099";
		assertDoesNotThrow(() -> cpfValidator.validate(cpf));
	}
	
	@Test
	void shouldValidateCpfSuccesfullyWithElevenNumbersAndMask() {
		String cpf = "963.110.150-99";
		assertDoesNotThrow(() -> cpfValidator.validate(cpf));
	}
	
	@Test
	void shouldFailValidatingInvalidCpf() {
		String cpf = "12345678910";
		ValidationException ex = assertThrows(ValidationException.class, () -> cpfValidator.validate(cpf));
		assertEquals(ex.getMessage(), INVALID_CPF_EXCEPTION_MESSAGE);
	}
	
	@Test
	void shouldFailValidatingCpfWithTenNumbers() {
		String cpf = "9631101509";
		ValidationException ex = assertThrows(ValidationException.class, () -> cpfValidator.validate(cpf));
		assertEquals(ex.getMessage(), INVALID_LENGTH_CPF_EXCEPTION_MESSAGE);
	}
	
	@Test
	void shouldFailValidatingCpfWithTwelveNumbers() {
		String cpf = "963110150992";
		ValidationException ex = assertThrows(ValidationException.class, () -> cpfValidator.validate(cpf));
		assertEquals(ex.getMessage(), INVALID_LENGTH_CPF_EXCEPTION_MESSAGE);
	}
	
	@Test
	void shouldFailValidatingNullValueForCpf() {
		String cpf = null;
		ValidationException ex = assertThrows(ValidationException.class, () -> cpfValidator.validate(cpf));
		assertEquals(ex.getMessage(), NO_INPUT_CPF_EXCEPTION_MESSAGE);
	}
	
	@Test
	void shouldFailValidatingEmptyStringForCpf() {
		String cpf = "";
		ValidationException ex = assertThrows(ValidationException.class, () -> cpfValidator.validate(cpf));
		assertEquals(ex.getMessage(), NO_INPUT_CPF_EXCEPTION_MESSAGE);
	}

}
