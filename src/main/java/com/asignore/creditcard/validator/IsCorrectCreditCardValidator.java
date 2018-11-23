package com.asignore.creditcard.validator;


import com.asignore.creditcard.client.CreditCardValueBean;
import com.asignore.creditcard.utils.JavaLuhnAlgorithm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsCorrectCreditCardValidator implements ConstraintValidator<IsCorrectCreditCard, CreditCardValueBean> {

    @Override
    public void initialize(IsCorrectCreditCard constraintAnnotation) {
    }

    @Override
    public boolean isValid(CreditCardValueBean value, ConstraintValidatorContext context) {
        if (value == null) return true;

        if (value.getCreditCardNumber() == null || value.getCreditCardNumber().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Credit card number is required")
                    .addPropertyNode("creditCardNumber").addConstraintViolation();
            return false;
        }

        if (!JavaLuhnAlgorithm.isValidCreditCardNumber(value.getCreditCardNumber())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Credit card number is not valid")
                    .addPropertyNode("creditCardNumber").addConstraintViolation();
            return false;
        }

        return true;
    }
}