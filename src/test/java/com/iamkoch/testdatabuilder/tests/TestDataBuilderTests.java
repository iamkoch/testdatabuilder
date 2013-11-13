package com.iamkoch.testdatabuilder.tests;

import com.iamkoch.testdatabuilder.Action;
import com.iamkoch.testdatabuilder.tests.builder.CustomerBuilder;
import com.iamkoch.testdatabuilder.tests.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDataBuilderTests {

    @Test
    public void createShouldReturnInstanceOfDerivedBuilder() {
        CustomerBuilder builder = CustomerBuilder.create(CustomerBuilder.class, Customer.class);
        assertEquals(builder.getClass(), CustomerBuilder.class);
    }

    @Test
    public void withShouldAllowPropertiesToBeSetOnEntity() {
        CustomerBuilder customerBuilder = CustomerBuilder.create(CustomerBuilder.class, Customer.class);
        int userId = customerBuilder.build().getUserId();

        customerBuilder.with(new Action<Customer>() {
                    @Override
                    public void with(Customer entity) {
                        entity.setUserId(383);
                    }
                });

        int updatedUserId = customerBuilder.build().getUserId();

        assertNotEquals(userId, updatedUserId);
    }
}
