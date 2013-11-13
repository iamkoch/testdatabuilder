package com.iamkoch.testdatabuilder;

public interface Action<T> {
    void with(T entity);
}
