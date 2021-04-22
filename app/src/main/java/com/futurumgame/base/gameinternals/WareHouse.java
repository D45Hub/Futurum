package com.futurumgame.base.gameinternals;

import android.content.res.Resources;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.enums.DataEncoding;
import com.futurumgame.base.enums.Separator;
import com.futurumgame.base.enums.WareHouseAcceptionResult;
import com.futurumgame.base.interfaces.IData;
import com.futurumgame.base.interfaces.IDoubleParseRuleProvider;
import com.futurumgame.base.interfaces.IParseRule;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.basic.Water;
import com.futurumgame.base.serialization.parsing.IntegerUnitsMapEntryParseRule;
import com.futurumgame.base.serialization.parsing.ParseResult;
import com.futurumgame.base.serialization.parsing.ResourceParseRule;
import com.futurumgame.base.unlockables.UnlockableCollection;
import com.futurumgame.base.util.CollectionHelper;
import com.futurumgame.base.util.Logger;
import com.futurumgame.base.util.ResourceMapping;
import com.futurumgame.base.util.StringUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;

public class WareHouse implements IData, IDoubleParseRuleProvider<Resource, Map.Entry<Integer, Units>> {

    public static final Units WaterBaseCapacity = new Units(1, 8);

    private static final ResourceParseRule ResourceParseRule = new ResourceParseRule();
    private static final IntegerUnitsMapEntryParseRule CapacityParseRule = new IntegerUnitsMapEntryParseRule();

    private final Hashtable<Integer, Resource> wareHouseStocks = new Hashtable<>();
    private final Hashtable<Integer, Units> resourceCapacities = new Hashtable<>();

    public WareHouse() {
    }

    public Hashtable<Integer, Resource> getWareHouseStocks() {
        return wareHouseStocks;
    }

    public Resource getWareHouseStock(int resourceID) {
        return wareHouseStocks.get(resourceID);
    }

    public void addResource(Resource resource, Units resourceCap) {
        wareHouseStocks.put(resource.getID(), resource);
        resourceCapacities.put(resource.getID(), resourceCap);
    }

    public WareHouseAcceptionResult addToResources(Resource resource) {
        Units cap = resourceCapacities.get(resource.getID());
        Resource wareHouseStock = wareHouseStocks.get(resource.getID());
        if (cap.equals(wareHouseStock.getCount())) {
            return WareHouseAcceptionResult.RejectedFully;
        }
        wareHouseStock.add(resource);
        WareHouseAcceptionResult result = WareHouseAcceptionResult.AcceptedEverything;
        if (wareHouseStock.getCount().isBiggerThan(cap)) {
            wareHouseStock.setCount(cap);
            result = WareHouseAcceptionResult.RejectedPartial;
        }
        return result;
    }

    public boolean canOfferResources(LinkedList<Resource> requiredResources) {
        for (Resource requiredResource : requiredResources) {
            Resource wareHouseStock = wareHouseStocks.get(requiredResource.getID());
            if (requiredResource.getCount().isBiggerThan(wareHouseStock.getCount())) {
                return false;
            }
        }
        return true;
    }

    public void offerResources(LinkedList<Resource> requiredResources) {
        for (Resource requiredResource : requiredResources) {
            Resource resource = wareHouseStocks.get(requiredResource.getID());
            resource.subtract(requiredResource.getCount());
        }
    }

    @Override
    public void from(byte[] data) {
        if (data.length == 0) {
            addInitial();
            return;
        }
        String stringData = DataEncoding.UTF8.decode(data);
        String[][] warehouse = StringUtil.doubleSplit(stringData);
        if (warehouse.length < 2) {
            Logger.toFewData(getClass(), "warehouse", warehouse, 2);
            addInitial();
            return;
        }
        if (warehouse.length > 2) {
            Logger.toMuchData(getClass(), stringData);
        }
        HashMap<Integer, Resource> stocks = parseStocks(warehouse[0]);
        LinkedList<Map.Entry<Integer, Units>> capacities = parseCapacities(warehouse[1]);
        if (stocks.size() != capacities.size()) {
            Logger.e(getClass(), String.format("size not equal: stocks: {0} capacities {1}", stocks.size(), capacities.size()));
        }
        updateFrom(stocks, capacities);
    }

    @Override
    public byte[] provideData() {
        String stocks = CollectionHelper.toString(ResourceParseRule::getParsingValue, Separator.DefaultSeparator, wareHouseStocks.values());
        String capacities = CollectionHelper.toString(CapacityParseRule::getParsingValue, Separator.DefaultSeparator, resourceCapacities);
        return DataEncoding.UTF8.encode(StringUtil.combine(Separator.CollectionSeparator, stocks, capacities));
    }

    @Override
    public IParseRule<Resource> getFirstParseRule() {
        return ResourceParseRule;
    }

    @Override
    public IParseRule<Map.Entry<Integer, Units>> getSecondParseRule() {
        return CapacityParseRule;
    }

    private void addInitial() {
        addResource(Water.factory(), WaterBaseCapacity);
    }

    private HashMap<Integer, Resource> parseStocks(String[] strings) {
        LinkedList<ParseResult<Resource>> resourceResults = CollectionHelper.select(strings, ResourceParseRule::next);
        LinkedList<Resource> stocks = CollectionHelper.select(CollectionHelper.where(resourceResults, ParseResult::parseSuccess), ParseResult::getResult);
        return CollectionHelper.toHashMap(stocks, Resource::getID);
    }

    private LinkedList<Map.Entry<Integer, Units>> parseCapacities(String[] strings) {
        LinkedList<ParseResult<Map.Entry<Integer, Units>>> capacityResults = CollectionHelper.select(strings, CapacityParseRule::next);
        return CollectionHelper.select(CollectionHelper.where(capacityResults, ParseResult::parseSuccess), ParseResult::getResult);
    }

    private void update(Resource resource, Units capacity) {
        Resource stock = wareHouseStocks.get(resource.getID());
        Units cap = resourceCapacities.get(resource.getID());
        if (stock == null) {
            addResource(resource, capacity);
            return;
        }
        cap.setValue(capacity.getValue());
        cap.setScale(capacity.getScale());
        stock.add(resource.getCount());
    }

    private void updateFrom(HashMap<Integer, Resource> stocks, LinkedList<Map.Entry<Integer, Units>> capacities) {
        for (Map.Entry<Integer, Units> cap : capacities) {
            Resource resource = stocks.remove(cap.getKey());
            if(resource == null) {
                resource = getResourceByReflection(cap.getKey());
            }
            Units defaultCapacity = UnlockableCollection.getDefaultResourceCapacity(cap.getKey());
            Units capacity = defaultCapacity.isBiggerThan(cap.getValue()) ? defaultCapacity : cap.getValue();
            update(resource, capacity);
        }
        for (Resource value : stocks.values()) {
            addResource(value, UnlockableCollection.getDefaultResourceCapacity(value.getID()));
        }
    }

    private Resource getResourceByReflection(int id) {
        Resource resource = ResourceMapping.AllResources.get(id);
        try {
            Method m = resource.getClass().getMethod("factory");
            resource = (Resource) m.invoke(null);
        } catch (NoSuchMethodException|IllegalAccessException|InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return resource;
    }
}
