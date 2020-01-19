package com.test.camelspringbootrest.service;

import com.test.camelspringbootrest.model.Company;

public interface VATService {

    Company includeVAT(Company company);
}
