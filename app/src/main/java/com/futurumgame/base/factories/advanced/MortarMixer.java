package com.futurumgame.base.factories.advanced;

import com.futurumgame.base.additionalDatatypes.Units;
import com.futurumgame.base.factories.Facility;
import com.futurumgame.base.resources.Resource;
import com.futurumgame.base.resources.ResourceHelper;
import com.futurumgame.base.resources.advanced.Mortar;
import com.futurumgame.base.resources.advanced.Wood;
import com.futurumgame.base.resources.basic.Clay;
import com.futurumgame.base.resources.basic.Water;

import java.util.LinkedList;
import java.util.Queue;

public final class MortarMixer extends Facility<Mortar> {

    private MortarMixer() {
        super(MortarMixer.class.getSimpleName(), Mortar.factory(), new Units(1, 2));
    }

    @Override
    public Mortar work() {
        return null;
    }

    @Override
    public Queue<Integer> getLongestResourceTreePath() {
        LinkedList<Integer> longestResourceTreePath = new LinkedList<>();
        longestResourceTreePath.add(Water.ID);
        longestResourceTreePath.add(Mortar.ID);
        return longestResourceTreePath;
    }

    @Override
    public LinkedList<Resource> getUpgradeCosts() {
        return null;
    }

    @Override
    protected LinkedList<Resource> requiredResources() {
        return null;
    }

    @Override
    protected void upgrade() {
        super.upgrade();
    }

    @Override
    protected Mortar instanceResourceWithAmount(Units amount) {
        return ResourceHelper.setToAmount(Mortar.factory(), amount);
    }

    public static MortarMixer factory() {
        return new MortarMixer();
    }
}
