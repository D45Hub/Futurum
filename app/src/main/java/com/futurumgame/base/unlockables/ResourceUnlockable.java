package com.futurumgame.base.unlockables;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.factories.advanced.Forest;
import com.futurumgame.base.factories.advanced.LimestoneQuarry;
import com.futurumgame.base.factories.advanced.MortarMixer;
import com.futurumgame.base.factories.advanced.StoneQuarry;
import com.futurumgame.base.factories.basic.ClayPit;
import com.futurumgame.base.factories.basic.DirtPit;
import com.futurumgame.base.factories.basic.GravelPit;
import com.futurumgame.base.factories.basic.OilPump;
import com.futurumgame.base.factories.basic.SandDune;
import com.futurumgame.base.factories.basic.WaterMill;
import com.futurumgame.base.factories.ores.CoalMine;
import com.futurumgame.base.factories.ores.CopperMine;
import com.futurumgame.base.factories.ores.TinMine;
import com.futurumgame.base.factories.smelteries.TinSmeltery;
import com.futurumgame.base.gameinternals.GameRoutine;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.advanced.Cement;
import com.futurumgame.base.resources.advanced.Coke;
import com.futurumgame.base.resources.advanced.Concrete;
import com.futurumgame.base.resources.advanced.FiredClay;
import com.futurumgame.base.resources.advanced.Glass;
import com.futurumgame.base.resources.advanced.Limestone;
import com.futurumgame.base.resources.advanced.Mortar;
import com.futurumgame.base.resources.advanced.Stone;
import com.futurumgame.base.resources.advanced.Wood;
import com.futurumgame.base.resources.alloys.Bronze;
import com.futurumgame.base.resources.alloys.Steel;
import com.futurumgame.base.resources.basic.Clay;
import com.futurumgame.base.resources.basic.Dirt;
import com.futurumgame.base.resources.basic.Gravel;
import com.futurumgame.base.resources.basic.Oil;
import com.futurumgame.base.resources.basic.Sand;
import com.futurumgame.base.resources.basic.Water;
import com.futurumgame.base.resources.jewels.PolishedDiamond;
import com.futurumgame.base.resources.jewels.UnpolishedDiamond;
import com.futurumgame.base.resources.metals.Aluminium;
import com.futurumgame.base.resources.metals.Copper;
import com.futurumgame.base.resources.metals.Gold;
import com.futurumgame.base.resources.metals.Iron;
import com.futurumgame.base.resources.metals.Silver;
import com.futurumgame.base.resources.metals.Tin;
import com.futurumgame.base.resources.ores.AluminiumOre;
import com.futurumgame.base.resources.ores.Coal;
import com.futurumgame.base.resources.ores.CopperOre;
import com.futurumgame.base.resources.ores.DiamondOre;
import com.futurumgame.base.resources.ores.GoldOre;
import com.futurumgame.base.resources.ores.IronOre;
import com.futurumgame.base.resources.ores.SilverOre;
import com.futurumgame.base.resources.ores.TinOre;

import java.util.Hashtable;

public class ResourceUnlockable extends Unlockable {

    private static final Hashtable<Integer, Resource> AllResources = gatherAllResources();
    private static final Hashtable<Integer, Factory<? extends Resource>> AllStartFactories = gatherAllStartFactories();

    private final Resource resource;
    private final Factory<? extends Resource> factory;
    private final Units startCap;

    protected ResourceUnlockable(String name, int id, Units startCap, Resource... costs) {
        super(name, costs);
        resource = AllResources.get(id);
        factory = AllStartFactories.get(id);
        this.startCap = startCap;
    }

    @Override
    public void unlock() {
        GameRoutine.getCurrentWareHouse().addResource(resource, startCap);
        GameRoutine.addNewFactory(factory);
        GameRoutine.getUnlockables().updateNewUnlock(this, GameRoutine.getCurrentWareHouse());
    }

    private static Hashtable<Integer, Resource> gatherAllResources() {
        Hashtable<Integer, Resource> allResources = new Hashtable<>();
        allResources.put(Water.ID, Water.factory());
        allResources.put(Dirt.ID, Dirt.factory());
        allResources.put(Clay.ID, Clay.factory());
        allResources.put(Sand.ID, Sand.factory());
        allResources.put(Gravel.ID, Gravel.factory());
        allResources.put(Oil.ID, Oil.factory());
        allResources.put(Wood.ID, Wood.factory());
        allResources.put(Mortar.ID, Mortar.factory());
        allResources.put(Stone.ID, Stone.factory());
        allResources.put(Limestone.ID, Limestone.factory());
        allResources.put(TinOre.ID, TinOre.factory());
        allResources.put(CopperOre.ID, CopperOre.factory());
        allResources.put(Coal.ID, Coal.factory());
        allResources.put(Tin.ID, Tin.factory());
        allResources.put(Copper.ID, Copper.factory());
        allResources.put(Coke.ID, Coke.factory());
        allResources.put(FiredClay.ID, FiredClay.factory());
        allResources.put(Glass.ID, Glass.factory());
        allResources.put(Bronze.ID, Bronze.factory());
        allResources.put(Cement.ID, Cement.factory());
        allResources.put(IronOre.ID, IronOre.factory());
        allResources.put(Iron.ID, Iron.factory());
        allResources.put(Concrete.ID, Concrete.factory());
        allResources.put(GoldOre.ID, GoldOre.factory());
        allResources.put(SilverOre.ID, SilverOre.factory());
        allResources.put(AluminiumOre.ID, AluminiumOre.factory());
        allResources.put(DiamondOre.ID, DiamondOre.factory());
        allResources.put(Steel.ID, Steel.factory());
        allResources.put(Gold.ID, Gold.factory());
        allResources.put(Silver.ID, Silver.factory());
        allResources.put(Aluminium.ID, Aluminium.factory());
        allResources.put(UnpolishedDiamond.ID, UnpolishedDiamond.factory());
        allResources.put(PolishedDiamond.ID, PolishedDiamond.factory());
        return allResources;
    }

    private static Hashtable<Integer, Factory<? extends Resource>> gatherAllStartFactories() {
        Hashtable<Integer, Factory<? extends Resource>> allResources = new Hashtable<>();
        allResources.put(Water.ID, WaterMill.factory());
        allResources.put(Dirt.ID, DirtPit.factory());
        allResources.put(Clay.ID, ClayPit.factory());
        allResources.put(Sand.ID, SandDune.factory());
        allResources.put(Gravel.ID, GravelPit.factory());
        allResources.put(Oil.ID, OilPump.factory());
        allResources.put(Wood.ID, Forest.factory());
        allResources.put(Mortar.ID, MortarMixer.factory());
        allResources.put(Stone.ID, StoneQuarry.factory());
        allResources.put(Limestone.ID, LimestoneQuarry.factory());
        allResources.put(TinOre.ID, TinMine.factory());
        allResources.put(CopperOre.ID, CopperMine.factory());
        allResources.put(Coal.ID, CoalMine.factory());
        allResources.put(Tin.ID, TinSmeltery.factory());
        allResources.put(Copper.ID, CopperMine.factory());
        //allResources.put(Coke.ID, Coke.factory());
        //allResources.put(FiredClay.ID, FiredClay.factory());
        //allResources.put(Glass.ID, Glass.factory());
        //allResources.put(Bronze.ID, Bronze.factory());
        //allResources.put(Cement.ID, Cement.factory());
        //allResources.put(IronOre.ID, IronOre.factory());
        //allResources.put(Iron.ID, Iron.factory());
        //allResources.put(Concrete.ID, Concrete.factory());
        //allResources.put(GoldOre.ID, GoldOre.factory());
        //allResources.put(SilverOre.ID, SilverOre.factory());
        //allResources.put(AluminiumOre.ID, AluminiumOre.factory());
        //allResources.put(DiamondOre.ID, DiamondOre.factory());
        //allResources.put(Steel.ID, Steel.factory());
        //allResources.put(Gold.ID, Gold.factory());
        //allResources.put(Silver.ID, Silver.factory());
        //allResources.put(Aluminium.ID, Aluminium.factory());
        //allResources.put(UnpolishedDiamond.ID, UnpolishedDiamond.factory());
        //allResources.put(PolishedDiamond.ID, PolishedDiamond.factory());
        return allResources;
    }
}
