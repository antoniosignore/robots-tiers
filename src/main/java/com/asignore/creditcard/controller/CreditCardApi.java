package com.asignore.creditcard.controller;

import com.asignore.creditcard.client.CreditCardValueBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@Validated
@RestController
@RequestMapping("/")
@Api(value = "sapient api", tags = {"Sapient Credit Card API"})
public interface CreditCardApi {

    // Swagger info section-------------------------------------------------
    @ApiOperation(value = "", notes = "Get all credit cards", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful response"),
            @ApiResponse(code = 404, message = "Not found response"),
            @ApiResponse(code = 422, message = "Missing or wrong arguments response")})

    // Swagger info section-------------------------------------------------
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Collection<CreditCardValueBean>> findAll();

    // Swagger info section-------------------------------------------------
    @ApiOperation(value = "Create Credit Card", response = CreditCardValueBean.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created a session", response = CreditCardValueBean.class),
            @ApiResponse(code = 400, message = "No session created due to bad request"),
            @ApiResponse(code = 409, message = "Session already existing")})
    // Swagger info section-------------------------------------------------
    @RequestMapping(
            produces = {"application/hal+json", "application/problem+json", "application/json"},
            consumes = {"application/json"}, method = RequestMethod.POST)
    ResponseEntity<CreditCardValueBean> save(@Valid @RequestBody CreditCardValueBean creditCardValueBean);


}
