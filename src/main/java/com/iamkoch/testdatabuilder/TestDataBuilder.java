package com.iamkoch.testdatabuilder;

@SuppressWarnings("unchecked")
/**
 * This class provides an API to allow the quick construction of populated data classes with
 * minimal input. The API also provides the opportunity to configure the class (called entity) in
 * greater detail by overriding certain methods
 *
 * @author Antony Koch
 */
public abstract class TestDataBuilder<T extends TestDataBuilder<T, U>, U> {

    private U entity;
    private Class<U> entityClass;

    protected final U getEntity() {
        return this.entity;
    }

    public final T with(Action<U> action) {
        action.with(entity);
        return (T)this;
    }

    /**
     * Returns your entity for use
     * @return the entity you've configured
     */
    public final U build() {
        return this.entity;
    }

    /**
     * Must be overridden. Allows opportunity to provide default values for your entity object
     * Called after (@link #getDefaultEntity(U))
     */
    protected abstract void setupDefaults();

    /**
     * Returns an instance of a your builder super class, with its entity instantiated where possible
     * @param builderClass The class of your builder. Must extend this class
     * @param entity The class of your entity or POJO
     * @param <T> The type of your builder
     * @param <U> The type of your entity
     * @return an instance of your builder class
     */
    public static <T extends TestDataBuilder<T, U>, U> T create(Class<T> builderClass, Class<U> entity) {

        T t;

        try {
            t = builderClass.newInstance();
        } catch (InstantiationException e) {
            System.out.println("Error creating builder");
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            System.out.println("Error creating builder");
            e.printStackTrace();
            return null;
        }

        t.entity = t.getDefaultEntity(entity);
        t.setupDefaults();

        return t;
    }

    /**
     * Override this to use your own instantiated entity object.
     *
     * <p>Leaving this in the base class will mean the entity is constructed
     * using the default, parameterless public constructor</p>
     * @return a new instance of your entity class. null if no default constructor
     */
    protected U getDefaultEntity() {
        return null;
    }

    private <U> U getDefaultEntity(Class<U> entity) {
        U manuallyCreatedEntity = (U)getDefaultEntity();

        if (manuallyCreatedEntity != null)
            return manuallyCreatedEntity;

        try {
            return entity.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }


}
