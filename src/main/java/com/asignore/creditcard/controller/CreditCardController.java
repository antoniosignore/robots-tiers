package com.asignore.creditcard.controller;

import com.asignore.creditcard.client.CreditCardValueBean;
import com.asignore.creditcard.service.CreditCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/creditcards")
public class CreditCardController implements CreditCardApi {

    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @Override
    public ResponseEntity<Collection<CreditCardValueBean>> findAll() {
        final List<CreditCardValueBean> all = creditCardService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CreditCardValueBean> save(@Valid @RequestBody CreditCardValueBean creditCardValueBean) {
        if (creditCardValueBean.getRemainingCredit() == null)
            creditCardValueBean.setRemainingCredit(creditCardValueBean.getCreditLimit());
        final CreditCardValueBean creditCard = creditCardService.create(creditCardValueBean);
        return new ResponseEntity<>(creditCard, HttpStatus.CREATED);
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(MethodArgumentNotValidException exception) {

        String errorMsg = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(exception.getMessage());

        return ErrorResponse.builder().message(errorMsg).build();
    }
}

