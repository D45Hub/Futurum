package com.futurumgame.base.gameinternals;

import com.futurumgame.base.collections.CollectionHelper;
import com.futurumgame.base.factories.Factory;
import com.futurumgame.base.resources.Resource;

import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.function.Consumer;

public class FactorySystem {

    private LinkedList<LinkedList<FactoryNode<? extends Resource>>> root = new LinkedList<>();

    public FactorySystem() {
    }

    public <T extends Resource> void add(Factory<T> factory) {
        int enterLevel = factory.getLongestResourceTreePath().size();
        int maxLevel = root.size();
        if(enterLevel > maxLevel){
            throw new InvalidParameterException("Level of resource is to high, so could not produce resource if factory would start.");
        }
        boolean needsNewLevel = enterLevel == root.size();
        LinkedList<FactoryNode<? extends Resource>> level = needsNewLevel ? new LinkedList<>() : root.get(enterLevel);
        if(CollectionHelper.contains(root, depth-> containsTask(depth, factory))){
            return;
        }
        FactoryNode<? extends Resource> node = new FactoryNode<>(factory);
        level.add(node);
        if(needsNewLevel) {
            root.add(level);
        }
    }

    public void forEach(Consumer<FactoryNode<? extends Resource>> consumer) {
        for (LinkedList<FactoryNode<? extends  Resource>> level : root) {
            for (FactoryNode<? extends  Resource> node: level) {
                consumer.accept(node);
            }
        }
    }

    private <T extends Resource> boolean containsTask(LinkedList<FactoryNode<? extends Resource>> level, Factory<T> factory) {
        return CollectionHelper.executeOnFirstIfTrue(level, node -> containsPredicate(node, factory), node->changePrimaryFactory(node, factory));
    }

    private void changePrimaryFactory(FactoryNode<? extends Resource> node,Factory<? extends Resource> factory) {
        node.changePrimaryFactory(factory);
    }

    private static <T extends Resource> boolean containsPredicate(FactoryNode<? extends Resource> node, Factory<T> factory) {
        return node.getResourceName().contentEquals(factory.getResource().getName());
    }
}
