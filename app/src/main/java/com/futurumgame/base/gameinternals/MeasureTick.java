package com.futurumgame.base.gameinternals;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.resources.Resource;

import java.util.Hashtable;
import java.util.TimerTask;

public class MeasureTick extends TimerTask {

    private final Hashtable<Integer, Resource> previousStocks = new Hashtable<>();
    private final Hashtable<Integer, Units> measuredDeltas;
    private final WareHouse wareHouse;

    public MeasureTick(WareHouse wareHouse, Hashtable<Integer, Units> measuredDeltas) {
        this.measuredDeltas = measuredDeltas;
        this.wareHouse = wareHouse;
    }

    @Override
    public void run() {
        for (Resource resource:wareHouse.getWareHouseStocks().values()) {
            int id = resource.getID();
            if(!previousStocks.containsKey(id)){
                previousStocks.put(id, wareHouse.getWareHouseStock(id));
                measuredDeltas.put(id, Units.Zero.copy());
                return;
            }
            Units delta = wareHouse.getWareHouseStock(id).getCount().copy();
            delta.subtract(previousStocks.get(id).getCount());
            measuredDeltas.put(id, delta);
        }
    }
}
