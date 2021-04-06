package com.futurumgame.base.collections;

import com.futurumgame.base.interfaces.IEquatable;
import com.futurumgame.base.resources.Resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class CollectionHelper {

    private CollectionHelper() {
    }

    public static <T> void addAll(Collection<T> collection, T... toAdd){
        for (T element : toAdd) {
            collection.add(element);
        }
    }

    public static <T> void addAll(Collection<T> collection, Iterable<T> toAdd){
        for (T element : toAdd) {
            collection.add(element);
        }
    }

    public static <T> LinkedList<T> asIterable(T... elements) {
        LinkedList<T> iterable = new LinkedList<>();
        addAll(iterable, elements);
        return iterable;
    }

    public static <T> List<T> asList(Iterable<T> collection){
        if(collection instanceof List){
            return (List<T>) collection;
        }
        List<T> list = new LinkedList<>();
        addAll(list, collection);
        return list;
    }

    public static <T> boolean contains(Iterable<T> collection, Predicate<T> predicate) {
        for (T element : collection) {
            if (predicate.test(element)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean sequenceEquals(Iterable<T> first, Iterable<T> second) {
        if(first == second) {
            return true;
        }if(first == null ^ second == null){
            return false;
        }
        List<T> firstAsList = asList(first);
        List<T> secondAsList = asList(second);
        if(firstAsList.size() != secondAsList.size()){
            return false;
        }
        for (int i =0 ;i<firstAsList.size(); i++){
            if(!firstAsList.get(i).equals(secondAsList.get(i))){
                return false;
            }
        }
        return true;
    }

    public static <T extends IEquatable<T>> boolean equatableSequenceEquals(Iterable<T> first, Iterable<T> second) {
        if(first == second) {
            return true;
        }if(first == null ^ second == null){
            return false;
        }
        List<T> firstAsList = asList(first);
        List<T> secondAsList = asList(second);
        if(firstAsList.size() != secondAsList.size()){
            return false;
        }
        for (int i =0 ;i<firstAsList.size(); i++){
            if(!firstAsList.get(i).isEqualTo(secondAsList.get(i))){
                return false;
            }
        }
        return true;
    }

    public static <T,S> LinkedList<S> select(Iterable<T> collection, Function<T, S> selector) {
        return select(collection, e->true, selector);
    }

    public static <T,S> LinkedList<S> select(T[] arr, Function<T, S> selector) {
        return select(arr, e->true, selector);
    }

    public static <T,S> LinkedList<S> select(Iterable<T> collection, Predicate<T> predicate, Function<T, S> selector) {
        LinkedList<S> selected = new LinkedList<>();
        executeIfTrue(collection, predicate, e->selected.add(selector.apply(e)));
        return selected;
    }

    public static <T,S> LinkedList<S> select(T[] arr, Predicate<T> predicate, Function<T, S> selector) {
        LinkedList<S> selected = new LinkedList<>();
        executeIfTrue(arr, predicate, e->selected.add(selector.apply(e)));
        return selected;
    }

    public static <T> LinkedList<T> where(Iterable<T> collection, Predicate<T> predicate) {
        LinkedList<T> selected = new LinkedList<>();
        executeIfTrue(collection, predicate, e->selected.add(e));
        return selected;
    }

    public static <T> LinkedList<T> where(T[] arr, Predicate<T> predicate) {
        LinkedList<T> selected = new LinkedList<>();
        executeIfTrue(arr, predicate, e->selected.add(e));
        return selected;
    }

    public static <T> boolean executeIfTrue(Iterable<T> collection, Predicate<T> predicate, Consumer<T> consumer) {
        boolean wasConsumed = false;
        for (T element : collection) {
            if (predicate.test(element)) {
                consumer.accept(element);
                wasConsumed = true;
            }
        }
        return wasConsumed;
    }

    public static <T> boolean executeIfTrue(T[] arr, Predicate<T> predicate, Consumer<T> consumer) {
        boolean wasConsumed = false;
        for (T element : arr) {
            if (predicate.test(element)) {
                consumer.accept(element);
                wasConsumed = true;
            }
        }
        return wasConsumed;
    }

    public static <T> boolean executeOnFirstIfTrue(Iterable<T> collection, Predicate<T> predicate, Consumer<T> consumer) {
        for (T element : collection) {
            if (predicate.test(element)) {
                consumer.accept(element);
                return true;
            }
        }
        return false;
    }

    public static <T> boolean executeOnFirstIfTrue(T[] arr, Predicate<T> predicate, Consumer<T> consumer) {
        for (T element : arr) {
            if (predicate.test(element)) {
                consumer.accept(element);
                return true;
            }
        }
        return false;
    }

    public static<T> String toString(Iterable<T> collection){
        return toString(collection, e->e.toString());
    }

    public static<T> String toString(Iterable<T> collection, Function<T, String> formatter) {
        StringBuilder sb = new StringBuilder();
        for (T element : collection) {
            sb.append(formatter.apply(element) +", ");
        }
        sb.delete(sb.length()-2, sb.length()-1);
        return sb.toString();
    }
}
