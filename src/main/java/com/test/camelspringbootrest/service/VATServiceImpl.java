package com.test.camelspringbootrest.service;

import com.test.camelspringbootrest.model.Company;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VATServiceImpl implements VATService {

    public static final String INFO = "Amount includes VAT";

    public Company includeVAT(Company bodyIn) {
        bodyIn.setName(bodyIn.getName());
        BigDecimal percentageAmount = bodyIn.getAmount().multiply(new BigDecimal((double) 20 / 100));
        bodyIn.setAmount(bodyIn.getAmount().add(percentageAmount).setScale(2, RoundingMode.HALF_UP));
        bodyIn.setInfo(INFO);

        return bodyIn;
    }
}
