package com.futurumgame.base;

import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.factories.basic.WaterMill;
import com.futurumgame.testhelpers.FactoryTestHelper;
import com.futurumgame.testhelpers.ReflectionHelper;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Example factory testclass
 */
public class ExampleFactoryTest {

    @Test
    public void instanceTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        assertNotNull(FactoryTestHelper.instanceFactory(WaterMill.class));
        Factory factory = FactoryTestHelper.instanceFactory(WaterMill.class, 50);
        assertNotNull(factory);
    }

    @Test
    public void hasFactory(){
        String factory = "factory";
        ArrayList<Class[]> args = new ArrayList<>();
        args.add(new Class[0]);
        args.add(new Class[]{int.class});
        assertTrue(ReflectionHelper.hasMethods(WaterMill.class, new String[]{factory, factory}, args));
    }
}
