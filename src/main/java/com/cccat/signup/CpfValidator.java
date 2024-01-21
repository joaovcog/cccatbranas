package com.cccat.signup;

public class CpfValidator {

	private static final int CPF_LENGTH = 11;
	
    public boolean isValid(String rawCpf) {
        if (rawCpf == null || rawCpf.isEmpty()) {
            return false;
        }
        String cpf = removeNonDigits(rawCpf);
        if (isInvalidLength(cpf) || hasAllDigitsEqual(cpf)) {
            return false;
        }
        return extractVerifierDigit(cpf).equals(getCalculatedVerifierDigit(cpf));
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
