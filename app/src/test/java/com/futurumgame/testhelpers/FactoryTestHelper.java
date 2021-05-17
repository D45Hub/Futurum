package com.futurumgame.testhelpers;

import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FactoryTestHelper {

    private static final String factory = "factory";

    private FactoryTestHelper() {
    }

    /**
     * Instances a non abstract factory class. This method is only intended for non abstract subclasses of {@link Factory}.
     *
     * @param clazz the class that should be initialized
     * @param <T> the factory type
     * @param <S> the resource type of the factory
     * @return an instance of the specified factory class
     * @throws NoSuchMethodException if the instancer method <b>factory</b> does not exist, this case should never be the case, the test should fail
     * @throws InvocationTargetException if the method itself throws an exception, this case should never be the case, the test should fail
     * @throws IllegalAccessException if the method is not public and thus not accessable, this case should never be the case, the test should fail
     * @throws IllegalArgumentException if the method is not static and can thus not instance from a static context, this case should never be the case, the test should fail
     */
    public static <T extends Factory<S>, S extends Resource> T instanceFactory(Class<T> clazz)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IllegalArgumentException {
        Method factoryFactory = clazz.getMethod(factory);
        return (T) factoryFactory.invoke(null);
    }

    /**
     * Instances a non abstract factory class at a spezified level. This method is only intended for non abstract subclasses of {@link Factory}.
     *
     * @param clazz the class that should be initialized
     * @param level the level the class should be initialized with
     * @param <T> the factory type
     * @param <S> the resource type of the factory
     * @return an instance of the specified factory class
     * @throws NoSuchMethodException if the instancer method <b>factory</b> does not exist, this case should never be the case, the test should fail
     * @throws InvocationTargetException if the method itself throws an exception, this case should never be the case, the test should fail
     * @throws IllegalAccessException if the method is not public and thus not accessable, this case should never be the case, the test should fail
     * @throws IllegalArgumentException if the method is not static and can thus not instance from a static context, this case should never be the case, the test should fail
     */
    public static <T extends Factory<S>, S extends Resource> T instanceFactory(Class<T> clazz, int level)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IllegalArgumentException {
        Method factoryFactory = clazz.getMethod(factory, int.class);
        return (T) factoryFactory.invoke(null, level);
    }
}
