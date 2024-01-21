package com.cccat.domain.account;

import com.cccat.shared.ValidationException;

public class CpfValidator {

	private static final int CPF_LENGTH = 11;
	
    public void validate(String rawCpf) {
        if (rawCpf == null || rawCpf.isEmpty()) {
        	throw new ValidationException("No input for CPF! Please, type a valid CPF for signing up.");
        }
        String cpf = removeNonDigits(rawCpf);
        if (isInvalidLength(cpf)) {
        	throw new ValidationException("Invalid length for CPF! The CPF must have 11 number digits.");
        }
        if (hasAllDigitsEqual(cpf) || !extractVerifierDigit(cpf).equals(getCalculatedVerifierDigit(cpf))) {
        	throw new ValidationException("Invalid CPF! Please, type a valid CPF for signing up.");
        }
    }

    private String removeNonDigits(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

    private boolean isInvalidLength(String cpf) {
        return cpf.length() != CPF_LENGTH;
    }

    private boolean hasAllDigitsEqual(String cpf) {
        char firstCpfDigit = cpf.charAt(0);
        return cpf.chars().allMatch(digit -> digit == firstCpfDigit);
    }
    
    private String getCalculatedVerifierDigit(String cpf) {
    	int digitOne = calculateDigit(cpf, 10);
        int digitTwo = calculateDigit(cpf, 11);
        
        return String.valueOf(digitOne) + digitTwo;
    }

    private int calculateDigit(String cpf, int factor) {
        int total = 0;
        for (char digit : cpf.toCharArray()) {
            if (factor > 1) {
                total += Character.getNumericValue(digit) * factor--;
            }
        }
        int rest = total % CPF_LENGTH;
        return (rest < 2) ? 0 : CPF_LENGTH - rest;
    }

    private String extractVerifierDigit(String cpf) {
        return cpf.substring(9);
    }

}
