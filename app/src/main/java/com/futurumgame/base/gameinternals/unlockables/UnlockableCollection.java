package com.futurumgame.base.gameinternals.unlockables;

import com.futurumgame.base.collections.CollectionHelper;
import com.futurumgame.base.gameinternals.WareHouse;
import com.futurumgame.base.gameinternals.unlockables.resources.AluminiumOreUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.AluminiumUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.BronzeUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.CementUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.ClayUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.CoalUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.CokeUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.ConcreteUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.CopperOreUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.CopperUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.DiamondOreUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.DirtUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.FiredClayUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.GlassUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.GoldOreUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.GoldUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.GravelUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.IronOreUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.IronUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.LimestoneUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.MortarUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.OilUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.PolishedDiamondUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.SandUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.SilverOreUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.SilverUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.SteelUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.StoneUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.TinOreUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.TinUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.UnpolishedDiamondUnlockable;
import com.futurumgame.base.gameinternals.unlockables.resources.WoodUnlockable;
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

import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.function.Consumer;

public class UnlockableCollection {

    private static final HashSet<Unlockable> AllUnlockables = gatherAllUnlockables();

    private final LinkedList<Unlockable> buyableUnlockables = new LinkedList<>();
    private final HashSet<Unlockable> boughtUnlockables = new LinkedHashSet<>();

    public int size() {
        return buyableUnlockables.size();
    }

    public boolean isEmpty() {
        return buyableUnlockables.isEmpty();
    }

    public void foreach(Consumer<Unlockable> consumer) {
        for (Unlockable unlockable : buyableUnlockables) {
            consumer.accept(unlockable);
        }
    }

    public Unlockable get(int idx) {
        return buyableUnlockables.get(idx);
    }

    public void update(WareHouse wareHouse) {
        LinkedList<Unlockable> filteredByHasUnlockedRequieredResources = CollectionHelper.where(AllUnlockables, u -> wareHouse.getWareHouseStocks().values().containsAll(u.getCosts()));
        LinkedList<Unlockable> newUnlockables = CollectionHelper.where(filteredByHasUnlockedRequieredResources, u -> !buyableUnlockables.contains(u) && !boughtUnlockables.contains(u));
        buyableUnlockables.addAll(newUnlockables);
    }

    public void updateNewUnlock(Unlockable unlocked, WareHouse wareHouse) {
        buyableUnlockables.remove(unlocked);
        boughtUnlockables.add(unlocked);
        update(wareHouse);
    }

    private static HashSet<Unlockable> gatherAllUnlockables() {
        HashSet<Unlockable> allUnlockables = new HashSet<>();
        allUnlockables.addAll(gatherAllResourceUnlockables());
        allUnlockables.addAll(gatherAllResearchUnlockables());
        return allUnlockables;
    }

    private static LinkedList<Unlockable> gatherAllResourceUnlockables() {
        LinkedList<Unlockable> allResourceUnlockables = new LinkedList<>();
        allResourceUnlockables.add(DirtUnlockable.factory());
        allResourceUnlockables.add(ClayUnlockable.factory());
        allResourceUnlockables.add(SandUnlockable.factory());
        allResourceUnlockables.add(GravelUnlockable.factory());
        allResourceUnlockables.add(OilUnlockable.factory());
        allResourceUnlockables.add(WoodUnlockable.factory());
        allResourceUnlockables.add(MortarUnlockable.factory());
        allResourceUnlockables.add(StoneUnlockable.factory());
        allResourceUnlockables.add(LimestoneUnlockable.factory());
        allResourceUnlockables.add(TinOreUnlockable.factory());
        allResourceUnlockables.add(CopperOreUnlockable.factory());
        allResourceUnlockables.add(CoalUnlockable.factory());
        allResourceUnlockables.add(TinUnlockable.factory());
        allResourceUnlockables.add(CopperUnlockable.factory());
        allResourceUnlockables.add(CokeUnlockable.factory());
        allResourceUnlockables.add(FiredClayUnlockable.factory());
        allResourceUnlockables.add(GlassUnlockable.factory());
        allResourceUnlockables.add(BronzeUnlockable.factory());
        allResourceUnlockables.add(CementUnlockable.factory());
        allResourceUnlockables.add(IronOreUnlockable.factory());
        allResourceUnlockables.add(IronUnlockable.factory());
        allResourceUnlockables.add(ConcreteUnlockable.factory());
        allResourceUnlockables.add(GoldOreUnlockable.factory());
        allResourceUnlockables.add(SilverOreUnlockable.factory());
        allResourceUnlockables.add(AluminiumOreUnlockable.factory());
        allResourceUnlockables.add(DiamondOreUnlockable.factory());
        allResourceUnlockables.add(SteelUnlockable.factory());
        allResourceUnlockables.add(GoldUnlockable.factory());
        allResourceUnlockables.add(SilverUnlockable.factory());
        allResourceUnlockables.add(AluminiumUnlockable.factory());
        allResourceUnlockables.add(UnpolishedDiamondUnlockable.factory());
        allResourceUnlockables.add(PolishedDiamondUnlockable.factory());
        return allResourceUnlockables;
    }

    private static LinkedList<Unlockable> gatherAllResearchUnlockables() {
        return new LinkedList<>();
    }
}
