package com.test.camelspringbootrest.controller;

import com.test.camelspringbootrest.model.Company;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.ExchangeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.test.camelspringbootrest.route.Route.VAT_ROUTE_URI;


@RestController
public class VATController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VATController.class);

    @Autowired
    private ProducerTemplate producer;

    @Autowired
    private CamelContext camelContext;

    @PostMapping(path = "/vat", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Company> processVAT(final HttpServletRequest request, @Valid @RequestBody Company company) {

        LOGGER.info("processing VAT");

        final Exchange requestExchange = ExchangeBuilder.anExchange(camelContext).withBody(company).build();

        final Exchange responseExchange = producer.send(VAT_ROUTE_URI.value, requestExchange);

        final Company responseBody = responseExchange.getIn().getBody(Company.class);

        final int responseCode = responseExchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE, Integer.class).intValue();

        return ResponseEntity.status(responseCode).body(responseBody);
    }
}



