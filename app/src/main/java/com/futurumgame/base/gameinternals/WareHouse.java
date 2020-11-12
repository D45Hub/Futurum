package com.futurumgame.base.gameinternals;

import androidx.recyclerview.widget.RecyclerView;

import com.futurumgame.base.MainActivity;
import com.futurumgame.base.R;
import com.futurumgame.base.ui.adapter.ResourceAdapter;
import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.enums.WareHouseAcceptionResult;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.basic.Water;

import java.util.Hashtable;
import java.util.LinkedList;

public class WareHouse {

    private final MainActivity main;
    private final Hashtable<Integer, Resource> wareHouseStocks = new Hashtable<>();
    private final Hashtable<Integer, Units> resourceCapacities = new Hashtable<>();

    public WareHouse(MainActivity mainActivity) {
        main = mainActivity;
        addResource(Water.factory(), new Units(1, 8));
    }

    public Hashtable<Integer, Resource> getWareHouseStocks() {
        return wareHouseStocks;
    }

    public Resource getWareHouseStock(int resourceID){
        return wareHouseStocks.get(resourceID);
    }

    public void addResource(Resource resource, Units resourceCap){
        wareHouseStocks.put(resource.getID(), resource);
        resourceCapacities.put(resource.getID(), resourceCap);
    }

    public WareHouseAcceptionResult addToResources(Resource resource) {
        Units cap = resourceCapacities.get(resource.getID());
        Resource  wareHouseStock = wareHouseStocks.get(resource.getID());
        if(cap.equals(wareHouseStock.getCount())){
            return WareHouseAcceptionResult.RejectedFully;
        }
        wareHouseStock.add(resource);
        WareHouseAcceptionResult result = WareHouseAcceptionResult.AcceptedEverything;
        if (wareHouseStock.getCount().isBiggerThan(cap)){
            wareHouseStock.setCount(cap);
            result = WareHouseAcceptionResult.RejectedPartial;
        }
        return result;
    }

    public boolean canOfferResources(LinkedList<Resource> requiredResources) {
        for (Resource requiredResource:requiredResources) {
            Resource wareHouseStock = (Resource) wareHouseStocks.get(requiredResource.getID());
            if(requiredResource.getCount().isBiggerThan(wareHouseStock.getCount())){
                return false;
            }
        }
        return true;
    }

    public void offerResources(LinkedList<Resource> requiredResources) {
        for (Resource requiredResource : requiredResources) {
            ((Resource) wareHouseStocks.get(requiredResource.getID())).subtract(requiredResource.getCount());
        }
    }
}
