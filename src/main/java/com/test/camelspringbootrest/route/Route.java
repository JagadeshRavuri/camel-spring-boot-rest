package com.test.camelspringbootrest.route;

public enum Route {
    VAT_ROUTE_URI("direct:remoteService"), VAT_ROUTE_ID("direct-route");

    public final String value;

    Route(String value) {
        this.value = value;
    }


}


