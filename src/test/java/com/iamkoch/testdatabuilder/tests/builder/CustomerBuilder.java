package com.iamkoch.testdatabuilder.tests.builder;

import com.iamkoch.testdatabuilder.TestDataBuilder;
import com.iamkoch.testdatabuilder.tests.domain.Customer;

public class CustomerBuilder extends TestDataBuilder<CustomerBuilder, Customer> {

    @Override
    protected void setupDefaults() {
        this.getEntity().setName("David Roberts");
        this.getEntity().setUserId(4043);
    }
}
