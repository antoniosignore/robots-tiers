package com.asignore.creditcard.utils;


public class JavaLuhnAlgorithm {

    public static boolean isValidCreditCardNumber(String str) {
        int[] a = {str.length() % 2 == 0 ? 1 : 2};        // 1 if length even, 2 otherwise
        return str.chars()
                .map(i -> i - '0')                        // convert to the int equivalent
                .map(n -> n * (a[0] = a[0] == 1 ? 2 : 1)) // multiply by 1, 2 alternating
                .map(n -> n > 9 ? n - 9 : n)              // handle sum of digits
                .sum() % 10 == 0;                         // mod 10 should be zero
    }

}
