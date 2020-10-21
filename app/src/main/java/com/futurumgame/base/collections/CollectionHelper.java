package com.futurumgame.base.collections;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class CollectionHelper {

    private CollectionHelper() {
    }

    public static <T> boolean contains(Collection<T> collection, Predicate<T> predicate) {
        for (T element : collection) {
            if (predicate.test(element)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean executeIfTrue(Collection<T> collection, Predicate<T> predicate, Consumer<T> consumer) {
        boolean wasConsumed = false;
        for (T element : collection) {
            if (predicate.test(element)) {
                consumer.accept(element);
                wasConsumed = true;
            }
        }
        return wasConsumed;
    }

    public static <T> boolean executeOnFirstIfTrue(Collection<T> collection, Predicate<T> predicate, Consumer<T> consumer) {
        for (T element : collection) {
            if (predicate.test(element)) {
                consumer.accept(element);
                return true;
            }
        }
        return false;
    }
}
