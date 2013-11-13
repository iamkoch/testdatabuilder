package com.iamkoch.testdatabuilder;

@SuppressWarnings("unchecked")
public abstract class TestDataBuilder<T extends TestDataBuilder<T, U>, U> {

    private U entity;

    protected U getEntity() {
        return this.entity;
    }

    public T with(Action<U> action) {
        action.with(entity);
        return (T)this;
    }

    public U build() {
        return this.entity;
    }

    protected abstract void setupDefaults();

    public static <T extends TestDataBuilder<T, U>, U> T create(Class<T> builder, Class<U> entity) {
        try {
            T t = builder.newInstance();
            t.entity = entity.newInstance();
            t.setupDefaults();
            return t;
        } catch (InstantiationException e) {
            System.out.println("No default public constructor");
            return null;
        } catch (IllegalAccessException e) {
            System.out.println("Inaccessible default constructor");
            return null;
        }
    }
}
