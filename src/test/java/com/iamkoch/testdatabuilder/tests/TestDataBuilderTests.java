package com.iamkoch.testdatabuilder.tests;

import com.iamkoch.testdatabuilder.Action;
import com.iamkoch.testdatabuilder.tests.builder.AgeBuilder;
import com.iamkoch.testdatabuilder.tests.builder.CustomerBuilder;
import com.iamkoch.testdatabuilder.tests.domain.Age;
import com.iamkoch.testdatabuilder.tests.domain.Customer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDataBuilderTests {

    CustomerBuilder builder;

    @Before
    public void setup() {
        builder = CustomerBuilder.create(CustomerBuilder.class, Customer.class);
    }

    @Test
    public void testCreateReturnsInstanceOfDerivedBuilder() {
        assertEquals(builder.getClass(), CustomerBuilder.class);
    }

    @Test
    public void testWithAllowsPropertiesToBeSetOnEntity() {
        Customer customer = builder.build();
        int userId = customer.getUserId();

        builder.with(new Action<Customer>() {
            @Override
            public void with(Customer entity) {
                entity.setUserId(383);
            }
        });

        int updatedUserId = customer.getUserId();

        assertNotEquals(userId, updatedUserId);
    }

    @Test
    public void testGetDefaultEntitySetsEntityWhenOverriddenAndNoPublicConstructorForEntity() {
        AgeBuilder ageBuilder = AgeBuilder.create(AgeBuilder.class, Age.class);
        assertNotNull(ageBuilder.build());
    }
}
