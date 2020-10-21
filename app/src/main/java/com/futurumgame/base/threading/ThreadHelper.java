package com.futurumgame.base.threading;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.function.Consumer;

public class ThreadHelper {

    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static <T, FT extends FutureTask<? extends T>> void whenAll(Queue<FT> tasks, Consumer<T> consumer) {
        while (!tasks.isEmpty()) {
            FT task = tasks.poll();
            executor.submit(task);
            T result = null;
            try {
                result = task.get();
            } catch (InterruptedException | ExecutionException e) {
                tasks.add(task);
            }
            if (result != null) {
                consumer.accept(result);
            }
        }
    }
}
