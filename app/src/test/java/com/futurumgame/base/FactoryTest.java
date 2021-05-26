package com.futurumgame.base;

import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.factories.advanced.*;
import com.futurumgame.base.factories.basic.*;
import com.futurumgame.base.factories.ores.*;
import com.futurumgame.base.factories.smelteries.*;
import com.futurumgame.testhelpers.FactoryTestHelper;
import com.futurumgame.testhelpers.ReflectionHelper;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FactoryTest {

    @Test
    public void instanceTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        assertNotNull(FactoryTestHelper.instanceFactory(Forest.class));
        assertNotNull(FactoryTestHelper.instanceFactory(LimestoneQuarry.class));
        assertNotNull(FactoryTestHelper.instanceFactory(MortarMixer.class));
        assertNotNull(FactoryTestHelper.instanceFactory(StoneQuarry.class));
        assertNotNull(FactoryTestHelper.instanceFactory(ClayPit.class));
        assertNotNull(FactoryTestHelper.instanceFactory(DirtPit.class));
        assertNotNull(FactoryTestHelper.instanceFactory(GravelPit.class));
        assertNotNull(FactoryTestHelper.instanceFactory(OilPump.class));
        assertNotNull(FactoryTestHelper.instanceFactory(SandDune.class));
        assertNotNull(FactoryTestHelper.instanceFactory(WaterMill.class));
        assertNotNull(FactoryTestHelper.instanceFactory(CoalMine.class));
        assertNotNull(FactoryTestHelper.instanceFactory(CopperMine.class));
        assertNotNull(FactoryTestHelper.instanceFactory(TinMine.class));
        assertNotNull(FactoryTestHelper.instanceFactory(CopperSmeltry.class));
        assertNotNull(FactoryTestHelper.instanceFactory(TinSmeltry.class));


        Factory forest = FactoryTestHelper.instanceFactory(Forest.class, 50);
        Factory limestoneQuarry = FactoryTestHelper.instanceFactory(LimestoneQuarry.class, 50);
        Factory mortarMixer = FactoryTestHelper.instanceFactory(MortarMixer.class, 50);
        Factory stoneQuarry = FactoryTestHelper.instanceFactory(StoneQuarry.class, 50);
        Factory clayPit = FactoryTestHelper.instanceFactory(ClayPit.class, 50);
        Factory dirtPit = FactoryTestHelper.instanceFactory(DirtPit.class, 50);
        Factory gravelPit = FactoryTestHelper.instanceFactory(GravelPit.class, 50);
        Factory oilPump = FactoryTestHelper.instanceFactory(OilPump.class, 50);
        Factory sandDune = FactoryTestHelper.instanceFactory(SandDune.class, 50);
        Factory waterMill = FactoryTestHelper.instanceFactory(WaterMill.class, 50);
        Factory coalMine = FactoryTestHelper.instanceFactory(CoalMine.class, 50);
        Factory copperMine = FactoryTestHelper.instanceFactory(CopperMine.class, 50);
        Factory tinMine = FactoryTestHelper.instanceFactory(TinMine.class, 50);
        Factory copperSmeltery = FactoryTestHelper.instanceFactory(CopperSmeltry.class, 50);
        Factory tinSmeltery = FactoryTestHelper.instanceFactory(TinSmeltry.class, 50);

        assertNotNull(forest);
        assertNotNull(limestoneQuarry);
        assertNotNull(mortarMixer);
        assertNotNull(stoneQuarry);
        assertNotNull(clayPit);
        assertNotNull(dirtPit);
        assertNotNull(gravelPit);
        assertNotNull(oilPump);
        assertNotNull(sandDune);
        assertNotNull(waterMill);
        assertNotNull(coalMine);
        assertNotNull(copperMine);
        assertNotNull(tinMine);
        assertNotNull(copperSmeltery);
        assertNotNull(tinSmeltery);
    }


    @Test
    public void hasFactoryTest(){
        String factory = "factory";
        ArrayList<Class[]> args = new ArrayList<>();
        args.add(new Class[0]);
        args.add(new Class[]{int.class});

        assertTrue(ReflectionHelper.hasMethods(Forest.class, new String[]{factory, factory}, args));
        assertTrue(ReflectionHelper.hasMethods(LimestoneQuarry.class, new String[]{factory, factory}, args));
        assertTrue(ReflectionHelper.hasMethods(MortarMixer.class, new String[]{factory, factory}, args));
        assertTrue(ReflectionHelper.hasMethods(StoneQuarry.class, new String[]{factory, factory}, args));
        assertTrue(ReflectionHelper.hasMethods(ClayPit.class, new String[]{factory, factory}, args));
        assertTrue(ReflectionHelper.hasMethods(DirtPit.class, new String[]{factory, factory}, args));
        assertTrue(ReflectionHelper.hasMethods(GravelPit.class, new String[]{factory, factory}, args));
        assertTrue(ReflectionHelper.hasMethods(OilPump.class, new String[]{factory, factory}, args));
        assertTrue(ReflectionHelper.hasMethods(SandDune.class, new String[]{factory, factory}, args));
        assertTrue(ReflectionHelper.hasMethods(WaterMill.class, new String[]{factory, factory}, args));
        assertTrue(ReflectionHelper.hasMethods(CoalMine.class, new String[]{factory, factory}, args));
        assertTrue(ReflectionHelper.hasMethods(CopperMine.class, new String[]{factory, factory}, args));
        assertTrue(ReflectionHelper.hasMethods(TinMine.class, new String[]{factory, factory}, args));
        assertTrue(ReflectionHelper.hasMethods(CopperSmeltry.class, new String[]{factory, factory}, args));
        assertTrue(ReflectionHelper.hasMethods(TinSmeltry.class, new String[]{factory, factory}, args));
    }

}
