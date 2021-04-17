package com.futurumgame.base.util;

import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.factories.advanced.LimestoneQuarry;
import com.futurumgame.base.factories.advanced.MortarMixer;
import com.futurumgame.base.factories.advanced.StoneQuarry;
import com.futurumgame.base.factories.basic.ClayPit;
import com.futurumgame.base.factories.basic.DirtPit;
import com.futurumgame.base.factories.basic.GravelPit;
import com.futurumgame.base.factories.basic.OilPump;
import com.futurumgame.base.factories.basic.SandDune;
import com.futurumgame.base.factories.basic.WaterMill;
import com.futurumgame.base.factories.ores.CopperMine;
import com.futurumgame.base.factories.ores.TinMine;
import com.futurumgame.base.factories.smelteries.CopperSmeltry;
import com.futurumgame.base.factories.smelteries.TinSmeltry;
import com.futurumgame.base.interfaces.IFactoryInstancer;
import com.futurumgame.base.resources.Resource;

import java.util.HashMap;

public class FactoryMapping {

    private static final HashMap<String, IFactoryInstancer> InstancerMapping = getMapping();

    private FactoryMapping() {
    }

    public static <T extends Resource> Factory<T> instanceFactory(String name, int level) {
        IFactoryInstancer instancer = InstancerMapping.get(name);
        return (Factory<T>) (instancer == null ? null : instancer.instance(level));
    }

    private static HashMap<String, IFactoryInstancer> getMapping() {
        HashMap<String, IFactoryInstancer> mapping = new HashMap<>();
        mapping.put(WaterMill.Name, WaterMill::factory);
        mapping.put(DirtPit.Name, DirtPit::factory);
        mapping.put(ClayPit.Name, ClayPit::factory);
        mapping.put(SandDune.Name, SandDune::factory);
        mapping.put(GravelPit.Name, GravelPit::factory);
        mapping.put(OilPump.Name, OilPump::factory);//ToDo: implement factories
        //mapping.put(WoodForest.Name, WoodForest::factory);
        mapping.put(MortarMixer.Name, MortarMixer::factory);
        mapping.put(StoneQuarry.Name, StoneQuarry::factory);
        mapping.put(LimestoneQuarry.Name, LimestoneQuarry::factory);
        mapping.put(TinMine.Name, TinMine::factory);
        mapping.put(CopperMine.Name, CopperMine::factory);
        mapping.put(TinSmeltry.Name, TinSmeltry::factory);
        mapping.put(CopperSmeltry.Name, CopperSmeltry::factory);
        //mapping.put(CokeOven.Name, CokeOven::Factory);
        //mapping.put(Kiln.Name, Kiln::Factory);
        //mapping.put(GlassManufacture.Name, GlassManufacture::Factory);
        //mapping.put(BronzeSmeltry.Name, BronzeSmeltry::Factory);
        //mapping.put(CementPlant.Name, CementPlant::Factory);
        //mapping.put(IronMine.Name, IronMine::Factory);
        //mapping.put(IronSmeltry.Name, IronSmeltry::Factory);
        //mapping.put(ConcretePlant.Name, ConcretePlant::Factory);
        //mapping.put(GoldMine.Name, GoldMine::Factory);
        //mapping.put(SilverMine.Name, SilverMine::Factory);
        //mapping.put(AluminiumMine.Name, AluminiumMine::Factory);
        //mapping.put(DiamondMine.Name, DiamondMine::Factory);
        //mapping.put(BlastFurnace.Name, BlastFurnace::Factory);
        //mapping.put(GoldSmeltry.Name, GoldSmeltry::Factory);
        //mapping.put(SilverSmeltry.Name, SilverSmeltry::Factory);
        //mapping.put(AluminiumSmeltry.Name, AluminiumSmeltry::Factory);
        //mapping.put(DiamondFiltrationFacility.Name, DiamondFiltrationFacility::Factory);
        //mapping.put(DiamondPolishingFacility.Name, DiamondPolishingFacility::Factory);
        return mapping;
    }
}
