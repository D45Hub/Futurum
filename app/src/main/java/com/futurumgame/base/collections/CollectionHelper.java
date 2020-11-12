package com.futurumgame.base.collections;

import com.futurumgame.base.resources.Resource;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.function.Function;
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

    public static<T> String toString(Collection<T> collection){
        StringBuilder sb = new StringBuilder();
        for (T element :collection) {
            sb.append(element+", ");
        }
        sb.delete(sb.length()-2, sb.length()-1);
        return sb.toString();
    }

    public static<T> String toString(LinkedList<T> collection, Function<T,String> formatter) {
        StringBuilder sb = new StringBuilder();
        for (T element :collection) {
            sb.append(formatter.apply(element) +", ");
        }
        sb.delete(sb.length()-2, sb.length()-1);
        return sb.toString();
    }
}
