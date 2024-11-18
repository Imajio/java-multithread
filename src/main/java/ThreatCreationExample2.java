package main.java;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;

public class ThreatCreationExample2 {
    public static void main(String[] args) {
        final Runnable task = () -> out.println(currentThread().getName());
        final Thread thread = new Thread(task);
        thread.start();
    }
}
