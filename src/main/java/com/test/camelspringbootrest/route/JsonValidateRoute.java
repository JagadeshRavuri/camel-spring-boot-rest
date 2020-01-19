package com.test.camelspringbootrest.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import static com.test.camelspringbootrest.route.Route.VALIDATE_ROUTE_URI;

@Component
class JsonValidateRoute extends RouteBuilder {

    @Override
    public void configure() {
        from(VALIDATE_ROUTE_URI.value)
                .routeId(Route.VALIDATE_ROUTE_ID.value)
                .log("validating json with schema")
                .marshal().json(JsonLibrary.Jackson)
                .to("json-validator:vat_policy_standard.json");
    }

}
