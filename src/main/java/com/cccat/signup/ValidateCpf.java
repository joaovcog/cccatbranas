package com.cccat.signup;

public class ValidateCpf {

    public static boolean validateCpf(String rawCpf) {
        if (rawCpf == null || rawCpf.isEmpty()) {
            return false;
        }

        String cpf = removeNonDigits(rawCpf);
        if (isInvalidLength(cpf)) {
            return false;
        }

        if (hasAllDigitsEqual(cpf)) {
            return false;
        }

        int digit1 = calculateDigit(cpf, 10);
        int digit2 = calculateDigit(cpf, 11);

        return extractDigit(cpf).equals(String.valueOf(digit1) + digit2);
    }

    private static String removeNonDigits(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

    private static boolean isInvalidLength(String cpf) {
        final int CPF_LENGTH = 11;
        return cpf.length() != CPF_LENGTH;
    }

    private static boolean hasAllDigitsEqual(String cpf) {
        char firstCpfDigit = cpf.charAt(0);
        return cpf.chars().allMatch(digit -> digit == firstCpfDigit);
    }

    private static int calculateDigit(String cpf, int factor) {
        int total = 0;
        for (char digit : cpf.toCharArray()) {
            if (factor > 1) {
                total += Character.getNumericValue(digit) * factor--;
            }
        }

        int rest = total % 11;
        return (rest < 2) ? 0 : 11 - rest;
    }

    private static String extractDigit(String cpf) {
        return cpf.substring(9);
    }

    public static void main(String[] args) {
        // Test the validateCpf function
        String rawCpf = "123.456.789-09";
        boolean isValid = validateCpf(rawCpf);
        System.out.println("Is CPF valid? " + isValid);
    }
}