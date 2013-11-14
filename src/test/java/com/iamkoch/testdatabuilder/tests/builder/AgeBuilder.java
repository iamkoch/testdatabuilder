package com.iamkoch.testdatabuilder.tests.builder;

import com.iamkoch.testdatabuilder.TestDataBuilder;
import com.iamkoch.testdatabuilder.tests.domain.Age;

public class AgeBuilder extends TestDataBuilder<AgeBuilder, Age> {

    @Override
    protected void setupDefaults() {

    }

    @Override
    protected Age getDefaultEntity() {
        return new Age(5);
    }
}
