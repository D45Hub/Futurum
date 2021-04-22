package com.futurumgame.base.unlockables;

import android.util.Log;

import com.futurumgame.base.collections.CollectionHelper;
import com.futurumgame.base.enums.DataEncoding;
import com.futurumgame.base.enums.Separator;
import com.futurumgame.base.gameinternals.WareHouse;
import com.futurumgame.base.interfaces.IData;
import com.futurumgame.base.interfaces.IParseRule;
import com.futurumgame.base.interfaces.IParseRuleProvider;
import com.futurumgame.base.serialization.parsing.ParseResult;
import com.futurumgame.base.serialization.parsing.UnlockableParseRule;
import com.futurumgame.base.unlockables.resources.AluminiumOreUnlockable;
import com.futurumgame.base.unlockables.resources.AluminiumUnlockable;
import com.futurumgame.base.unlockables.resources.BronzeUnlockable;
import com.futurumgame.base.unlockables.resources.CementUnlockable;
import com.futurumgame.base.unlockables.resources.ClayUnlockable;
import com.futurumgame.base.unlockables.resources.CoalUnlockable;
import com.futurumgame.base.unlockables.resources.CokeUnlockable;
import com.futurumgame.base.unlockables.resources.ConcreteUnlockable;
import com.futurumgame.base.unlockables.resources.CopperOreUnlockable;
import com.futurumgame.base.unlockables.resources.CopperUnlockable;
import com.futurumgame.base.unlockables.resources.DiamondOreUnlockable;
import com.futurumgame.base.unlockables.resources.DirtUnlockable;
import com.futurumgame.base.unlockables.resources.FiredClayUnlockable;
import com.futurumgame.base.unlockables.resources.GlassUnlockable;
import com.futurumgame.base.unlockables.resources.GoldOreUnlockable;
import com.futurumgame.base.unlockables.resources.GoldUnlockable;
import com.futurumgame.base.unlockables.resources.GravelUnlockable;
import com.futurumgame.base.unlockables.resources.IronOreUnlockable;
import com.futurumgame.base.unlockables.resources.IronUnlockable;
import com.futurumgame.base.unlockables.resources.LimestoneUnlockable;
import com.futurumgame.base.unlockables.resources.MortarUnlockable;
import com.futurumgame.base.unlockables.resources.OilUnlockable;
import com.futurumgame.base.unlockables.resources.PolishedDiamondUnlockable;
import com.futurumgame.base.unlockables.resources.SandUnlockable;
import com.futurumgame.base.unlockables.resources.SilverOreUnlockable;
import com.futurumgame.base.unlockables.resources.SilverUnlockable;
import com.futurumgame.base.unlockables.resources.SteelUnlockable;
import com.futurumgame.base.unlockables.resources.StoneUnlockable;
import com.futurumgame.base.unlockables.resources.TinOreUnlockable;
import com.futurumgame.base.unlockables.resources.TinUnlockable;
import com.futurumgame.base.unlockables.resources.UnpolishedDiamondUnlockable;
import com.futurumgame.base.unlockables.resources.WoodUnlockable;
import com.futurumgame.base.util.StringUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.function.Consumer;

public class UnlockableCollection implements IData, IParseRuleProvider<Unlockable> {

    private static final HashMap<Class<? extends Unlockable>, Unlockable> InitialUnlockables = gatherAllInitials();
    private static final HashMap<Class<? extends Unlockable>, HashSet<Unlockable>> AllUnlockables = gatherAllUnlockables();

    private static final UnlockableParseRule ParseRule = new UnlockableParseRule(AllUnlockables.get(ResourceUnlockable.class), AllUnlockables.get(ResearchUnlockable.class));

    private final LinkedList<Unlockable> buyableUnlockables = new LinkedList<>();
    private final HashSet<Unlockable> boughtUnlockables = new LinkedHashSet<>();
    private final Class<? extends Unlockable> targetUnlockables;

    private UnlockableCollection(Class<? extends Unlockable> targetUnlockables) {
        this.targetUnlockables = targetUnlockables;
    }

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
        LinkedList<Unlockable> filteredByHasUnlockedRequieredResources = CollectionHelper.where(personalUnlockables(), u -> wareHouse.getWareHouseStocks().values().containsAll(u.getCosts()));
        LinkedList<Unlockable> newUnlockables = CollectionHelper.where(filteredByHasUnlockedRequieredResources, u -> !buyableUnlockables.contains(u) && !boughtUnlockables.contains(u));
        buyableUnlockables.addAll(newUnlockables);
    }

    public void updateNewUnlock(Unlockable unlocked, WareHouse wareHouse) {
        buyableUnlockables.remove(unlocked);
        boughtUnlockables.add(unlocked);
        update(wareHouse);
    }

    @Override
    public void from(byte[] data) {
        if (data.length == 0) {
            addInitialUnlockable();
            Log.i(UnlockableCollection.class.getSimpleName(), "empty file, assuming no stats existed");
            return;
        }
        String dataString = DataEncoding.UTF8.decode(data);
        String[][] collections = StringUtil.doubleSplit(dataString);
        if (collections.length < 2) {
            Log.e(UnlockableCollection.class.getSimpleName(), "to few data: " + dataString + " array length: " + collections.length);
            addInitialUnlockable();
            return;
        }
        if (collections.length > 2) {
            Log.w(UnlockableCollection.class.getSimpleName(), "to much data: " + dataString);
        }
        simulateUnlocks(collections[1]);
        LinkedList<Unlockable> buyables = CollectionHelper.select(collections[0], this::parse);
        for(Unlockable buyable: CollectionHelper.whereNotNull(buyables)){
            if(buyableUnlockables.contains(buyable)){
                continue;
            }
            buyableUnlockables.add(buyable);
        }
        if(!buyables.isEmpty() || CollectionHelper.sequenceEquals(personalUnlockables(), boughtUnlockables)) {
            return;
        }
        correctFileCorruption();
    }

    @Override
    public IParseRule<Unlockable> getParseRule() {
        return ParseRule;
    }

    @Override
    public byte[] provideData() {
        String buyables = CollectionHelper.toString(ParseRule::getParsingValue,Separator.DefaultSeparator, buyableUnlockables);
        String bought = CollectionHelper.toString(ParseRule::getParsingValue,Separator.DefaultSeparator, boughtUnlockables);
        return DataEncoding.UTF8.encode(StringUtil.combine(Separator.CollectionSeparator, buyables, bought));
    }

    private void addInitialUnlockable() {
        buyableUnlockables.add(InitialUnlockables.get(targetUnlockables));
    }

    private void correctFileCorruption() {
        if(boughtUnlockables.isEmpty()) {
            Log.i(UnlockableCollection.class.getSimpleName(), "starting from scratch");
            addInitialUnlockable();
            return;
        }
        //TODO fix other possible corruption states, currently no further known state
    }

    private HashSet<Unlockable> personalUnlockables(){
        return AllUnlockables.get(targetUnlockables);
    }

    private Unlockable parse(String s) {
        ParseResult<Unlockable> result = ParseRule.next(s);
        if (result.parseSuccess()) {
            return result.getResult();
        }
        return null;
    }

    private void simulateUnlocks(String[] resources) {
        LinkedList<Unlockable> bought = CollectionHelper.select(resources, this::parse);
        buyableUnlockables.addAll(CollectionHelper.whereNotNull(bought));
        LinkedList<Unlockable> temp = new LinkedList<>(buyableUnlockables);
        temp.forEach(Unlockable::unlock);
    }

    public static UnlockableCollection createResourceUnlockables() {
        return new UnlockableCollection(ResourceUnlockable.class);
    }

    public static UnlockableCollection createResearchUnlockables() {
        return new UnlockableCollection(ResearchUnlockable.class);
    }

    //Not Cleancode!!!! TODO remove dependecy on init order
    private static HashMap<Class<? extends Unlockable>, Unlockable> gatherAllInitials() {
        HashMap<Class<? extends Unlockable>, Unlockable> initials = new HashMap<>();
        initials.put(ResourceUnlockable.class, DirtUnlockable.factory());
        return initials;
    }

    private static HashMap<Class<? extends Unlockable>, HashSet<Unlockable>> gatherAllUnlockables() {
        HashMap<Class<? extends Unlockable>, HashSet<Unlockable>> allUnlockables = new HashMap<>();
        allUnlockables.put(ResourceUnlockable.class, gatherAllResourceUnlockables());
        allUnlockables.put(ResearchUnlockable.class, gatherAllResearchUnlockables());
        return allUnlockables;
    }

    private static HashSet<Unlockable> gatherAllResourceUnlockables() {
        HashSet<Unlockable> allResourceUnlockables = new HashSet<>();
        allResourceUnlockables.add(InitialUnlockables.get(ResourceUnlockable.class));
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

    private static HashSet<Unlockable> gatherAllResearchUnlockables() {
        return new HashSet<>();
    }
}
