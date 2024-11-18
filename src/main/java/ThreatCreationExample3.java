package main.java;

import static java.lang.System.*;
import static java.util.stream.IntStream.range;

public class ThreatCreationExample3 {
    private static final int CREATE_THREADS_AMOUNT = 10;
    public static void main (final String... args) {
        final Runnable taskDisplayingThreadName = () -> out.println(Thread.currentThread().getName());
        final Runnable taskCreatingThreads = () -> range(0, CREATE_THREADS_AMOUNT)
                .forEach(i -> startThread(taskDisplayingThreadName));
        startThread(taskCreatingThreads);
    }

    private static void startThread(final Runnable runnable) {
        final Thread thread = new Thread(runnable);
        thread.start();
    }
}
