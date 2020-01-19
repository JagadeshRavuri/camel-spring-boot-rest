package com.test.camelspringbootrest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.camelspringbootrest.model.Company;
import com.test.camelspringbootrest.service.VATService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.ws.rs.core.MediaType;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class VATControllerIT {

    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    VATService vatService;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void shouldReturnHTTP201_whenValidJsonRequest() throws Exception {

        String validJsonRequest = "{\"amount\":10,\"name\":\"aCompany\",\"info\":\"company information\"}";

        this.mockMvc.perform(MockMvcRequestBuilders.post("/vat")
                .content(validJsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated());

        verify(vatService, times(1)).includeVAT(any(Company.class));
    }


    @Test
    public void shouldReturnHTTP400_whenMissingMandatoryField() throws Exception {

        Company company = new Company();
        company.setInfo("company information");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/vat")
                .content(objectMapper.writeValueAsString(company))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest());

        verify(vatService, times(0)).includeVAT(any(Company.class));
    }


    @Test
    public void shouldReturnHTTP400_whenInvalidAmountField() throws Exception {

        String inValidJsonRequest = "{\"amount\":\"should be number\",\"name\":\"aCompany\",\"info\":\"company information\"}";

        this.mockMvc.perform(MockMvcRequestBuilders.post("/vat")
                .content(inValidJsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isBadRequest());

        verify(vatService, times(0)).includeVAT(any(Company.class));
    }

}

