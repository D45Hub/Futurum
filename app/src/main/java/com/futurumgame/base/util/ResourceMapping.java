package com.futurumgame.base.util;

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

public class ResourceMapping {

    public static final Hashtable<Integer, Resource> AllResources = gatherAllResources();

    private ResourceMapping(){
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
}
