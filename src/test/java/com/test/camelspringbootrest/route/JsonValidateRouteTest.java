package com.test.camelspringbootrest.route;

import com.test.camelspringbootrest.model.Company;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.math.BigDecimal;

import static com.test.camelspringbootrest.route.Route.VALIDATE_ROUTE_URI;

public class JsonValidateRouteTest extends CamelSpringTestSupport {

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan("com.test.camelspringbootrest");
        ctx.refresh();
        return ctx;
    }

    @Test
    public void shouldStartJsonValidateRoute() throws Exception {
        assertTrue(context().getRouteStatus(Route.VALIDATE_ROUTE_ID.value).isStarted());
    }

    @Test
    public void shouldReturnEndpointWhenValidRoute() throws Exception {
        Company aCompany = new Company();
        aCompany.setAmount(BigDecimal.TEN);
        aCompany.setName("aCompanyName");
        aCompany.setInfo("info");

        ProducerTemplate template = context().createProducerTemplate();
        template.sendBody(VALIDATE_ROUTE_URI.value, aCompany);

        assertNotNull(this.context.getEndpoint(VALIDATE_ROUTE_URI.value));
    }

    @Test
    public void shouldPassValidationWhenInfoNotProvided() throws Exception {
        Company aCompany = new Company();
        aCompany.setAmount(BigDecimal.TEN);
        aCompany.setName("aCompanyName");

        ProducerTemplate template = context().createProducerTemplate();
        template.sendBody(VALIDATE_ROUTE_URI.value, aCompany);

        assertNotNull(this.context.getEndpoint(VALIDATE_ROUTE_URI.value));
    }

}