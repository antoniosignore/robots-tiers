package com.asignore.creditcard.client;

import com.asignore.creditcard.validator.IsCorrectCreditCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@IsCorrectCreditCard
public class CreditCardValueBean {

    Long id;

    @NotNull
    String creditCardNumber;

    @NotNull
    String name;

    @NotNull
    Double creditLimit;

    Double remainingCredit;

}
