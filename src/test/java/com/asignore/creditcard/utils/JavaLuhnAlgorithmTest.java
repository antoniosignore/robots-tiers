package com.asignore.creditcard.utils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class JavaLuhnAlgorithmTest {

    @Test
    public void shoul_validate_the_credit_card() {

        Assert.assertTrue(JavaLuhnAlgorithm.isValidCreditCardNumber("12345678903555"));
        Assert.assertTrue(JavaLuhnAlgorithm.isValidCreditCardNumber("012850003580200"));
        Assert.assertTrue(JavaLuhnAlgorithm.isValidCreditCardNumber("5497083002781388334"));

    }
}