package com.asignore.creditcard.controller;

import com.asignore.creditcard.client.CreditCardValueBean;
import com.asignore.creditcard.service.CreditCardService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class RestControllerTest {

    public static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";
    public static final String CREDITCARDS = "/creditcards";
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CreditCardService creditCardService;

    @InjectMocks
    private CreditCardController sapientController;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(sapientController).build();
        objectMapper = new ObjectMapper();
    }

    @Ignore
    @Test
    public void should_retrieve_all_creditcard() throws Exception {

        MvcResult mvcResult = this.mockMvc.perform(
                get(CREDITCARDS)
                        .contentType(MediaType.parseMediaType(APPLICATION_JSON_CHARSET_UTF_8))
                        .accept(MediaType.parseMediaType(APPLICATION_JSON_CHARSET_UTF_8)))
                .andExpect(status().isOk()).andReturn();

        String contentResponse = mvcResult.getResponse().getContentAsString();
        log.debug("contentResponse = " + contentResponse);

        final List<CreditCardValueBean> creditCardValueBeans = objectMapper.readValue(contentResponse, new TypeReference<List<CreditCardValueBean>>() {
        });
        Assert.assertEquals("", contentResponse);
        Assert.assertEquals(0, creditCardValueBeans.size());
    }

    @Test
    public void test_create_creditcard_mocked_service_good_number() throws Exception {

        // Given
        CreditCardValueBean creditCardValueBean =
                new CreditCardValueBean(null,
                        "5497083002781388334", "antonio", 100D, null);

        // When
        when(creditCardService.create(eq(creditCardValueBean))).thenReturn(creditCardValueBean);

        // Then
        final String content = objectMapper.writeValueAsString(creditCardValueBean);

        MvcResult mvcResult = this.mockMvc.perform(
                post(CREDITCARDS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        CreditCardValueBean response = objectMapper.readValue(content, CreditCardValueBean.class);

        assertEquals("Not as expected", creditCardValueBean, response);
    }


    @Test
    public void test_create_creditcard_mocked_service_bad_number() throws Exception {

        // Given
        CreditCardValueBean creditCardValueBean =
                new CreditCardValueBean(null,
                        "5497083002781388330", "antonio", 100D, null);

        // When
        when(creditCardService.create(eq(creditCardValueBean))).thenReturn(creditCardValueBean);

        // Then
        final String content = objectMapper.writeValueAsString(creditCardValueBean);

        MvcResult mvcResult = this.mockMvc.perform(
                post(CREDITCARDS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void test_create_creditcard_embedded_database() throws Exception {

        // Given
        CreditCardValueBean creditCardValueBean =
                new CreditCardValueBean(null,"5497083002781388334", "antonio", 100D, null);

        // Then
        {
            final String content = objectMapper.writeValueAsString(creditCardValueBean);

            MvcResult mvcResult = this.mockMvc.perform(
                    post(CREDITCARDS)
                            .content(content)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(content)
            )
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andReturn();

            CreditCardValueBean response = objectMapper.readValue(content, CreditCardValueBean.class);
            assertEquals("Not as expected", creditCardValueBean, response);
        }
    }
}
