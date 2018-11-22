package com.asignore.creditcard;

import com.asignore.creditcard.controller.CreditCardController;
import com.asignore.creditcard.service.CreditCardService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditCardApplicationTests {

    @Autowired
    private CreditCardController controller;

    @Autowired
    private CreditCardService service;

    @Test
    public void contextLoads() {
        Assert.assertNotNull(controller);
        Assert.assertNotNull(service);
    }

}
