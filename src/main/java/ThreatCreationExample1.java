package main.java;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;

public class ThreatCreationExample1 {
    public static void main(final String... args) {
        out.println(currentThread().getName());
        final Thread thread = new Thread(){
            @Override
            public void run() {
                out.println(currentThread().getName());
            }
        };
        thread.start();
    }
}
