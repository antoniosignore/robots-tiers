package com.asignore.creditcard.utils;

import com.asignore.creditcard.client.CreditCardValueBean;
import com.asignore.creditcard.model.CreditCardEntity;


public class MapperUtils {

    public static CreditCardValueBean toValueBean(CreditCardEntity creditCardEntity) {

        return new CreditCardValueBean(
                creditCardEntity.getId(),
                creditCardEntity.getNumber(),
                creditCardEntity.getName(),
                creditCardEntity.getCreditLimit(),
                creditCardEntity.getRemainingCredit());
    }

    public static CreditCardEntity toEntityBean(CreditCardValueBean creditCardValueBean) {
        final CreditCardEntity creditCardEntity = new CreditCardEntity();
        creditCardEntity.setId(creditCardValueBean.getId());
        creditCardEntity.setNumber(creditCardValueBean.getCreditCardNumber());
        creditCardEntity.setName(creditCardValueBean.getName());
        creditCardEntity.setCreditLimit(creditCardValueBean.getCreditLimit());
        creditCardEntity.setRemainingCredit(creditCardValueBean.getRemainingCredit());
        return creditCardEntity;
    }
}
