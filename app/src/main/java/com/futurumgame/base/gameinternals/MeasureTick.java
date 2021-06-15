package com.futurumgame.base.gameinternals;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;

import java.util.Hashtable;
import java.util.TimerTask;

public class MeasureTick extends TimerTask {

    private final Hashtable<Integer, Units> previousStocks = new Hashtable<>();
    private final Hashtable<Integer, Units> measuredDeltas;
    private final WareHouse wareHouse;

    public MeasureTick(WareHouse wareHouse, Hashtable<Integer, Units> measuredDeltas) {
        this.measuredDeltas = measuredDeltas;
        this.wareHouse = wareHouse;
    }

    @Override
    public void run() {
        for (Resource resource: wareHouse.getWareHouseStocks().values()) {
            int id = resource.getID();
            if(!previousStocks.containsKey(id)) {
                previousStocks.put(id, resource.getCount().copy());
                measuredDeltas.put(id, Units.Zero.copy());
                continue;
            }
            Units stocks = wareHouse.getWareHouseStock(id).getCount().copy();
            Units delta = stocks.copy();
            delta.subtract(previousStocks.get(id));
            measuredDeltas.put(id, delta);
            previousStocks.put(id, stocks);
        }
    }
}
