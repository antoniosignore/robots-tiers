package com.asignore.creditcard.controller;

import com.asignore.creditcard.client.CreditCardValueBean;
import com.asignore.creditcard.service.CreditCardService;
import com.asignore.creditcard.utils.CustomErrorType;
import com.asignore.creditcard.utils.JavaLuhnAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

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
        CreditCardValueBean creditCard;
            creditCard = creditCardService.create(creditCardValueBean);
        return new ResponseEntity<>(creditCard, HttpStatus.CREATED);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<CustomErrorType> handleAll(Exception ex, WebRequest request) {
        return new ResponseEntity(new CustomErrorType(ex.getLocalizedMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    }


//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponse handleException(MethodArgumentNotValidException exception) {
//
//        String errorMsg = exception.getBindingResult().getFieldErrors().stream()
//                .map(DefaultMessageSourceResolvable::getDefaultMessage)
//                .findFirst()
//                .orElse(exception.getMessage());
//
//        return ErrorResponse.builder().message(errorMsg).build();
//    }


}

