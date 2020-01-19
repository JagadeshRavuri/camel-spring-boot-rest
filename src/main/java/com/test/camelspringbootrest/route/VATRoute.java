package com.test.camelspringbootrest.route;

import com.test.camelspringbootrest.model.Company;
import com.test.camelspringbootrest.service.VATService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.test.camelspringbootrest.route.Route.VAT_ROUTE_ID;
import static com.test.camelspringbootrest.route.Route.VAT_ROUTE_URI;

@Component
class VATRoute extends RouteBuilder {

    @Autowired
    VATService vatService;

    @Override
    public void configure() {

        from(VAT_ROUTE_URI.value)
                .routeId(VAT_ROUTE_ID.value)
                .tracing()
                .log("amount: ${body.amount}")
                .log("name: ${body.name}")
                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        Company bodyIn = (Company) exchange.getIn().getBody();

                        Company company = vatService.includeVAT(bodyIn);

                        exchange.getIn().setBody(company);
                    }
                }).log(">>> processed")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201));
    }

}
