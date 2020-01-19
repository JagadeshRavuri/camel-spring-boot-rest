package com.test.camelspringbootrest.route;

import com.test.camelspringbootrest.model.Company;
import com.test.camelspringbootrest.service.VATServiceImpl;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.math.BigDecimal;

import static com.test.camelspringbootrest.route.Route.VAT_ROUTE_ID;
import static com.test.camelspringbootrest.route.Route.VAT_ROUTE_URI;


public class VATRouteTest extends CamelSpringTestSupport {

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan("com.test.camelspringbootrest");
        ctx.refresh();
        return ctx;
    }

    @Test
    public void shouldStartVATRoute() throws Exception {
        assertTrue(context().getRouteStatus(VAT_ROUTE_ID.value).isStarted());
    }

    @Test
    public void shouldProcessMessageByVATRoute() throws Exception {

        Company aCompany = new Company();
        aCompany.setAmount(BigDecimal.TEN);
        final Endpoint endpoint = getMandatoryEndpoint(VAT_ROUTE_URI.value);
        final Exchange requestExchange = ExchangeBuilder.anExchange(context()).withBody(aCompany).build();
        final Exchange resultExchange = context().createProducerTemplate().send(endpoint, requestExchange);
        final Company responseBody = resultExchange.getIn().getBody(Company.class);
        assertTrue(responseBody.getInfo().contains(VATServiceImpl.INFO));
    }

    @Test
    public void shouldReturnNullEndpointWhenInvalidVATRoute() throws Exception {

        assertNull(this.context.getEndpoint("INVALID_URI"));

    }
}