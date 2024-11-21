package main.java;

import static java.lang.String.format;
import static java.lang.System.*;
import static java.lang.Thread.*;

// NEW
// RUNNABLE
// WAITING
//TIMED_WAITING
//TERMINATED

public class ThreadsStatesExample {

//    private static final String MESSAGE_TEMPLATE_THREAD_STATE = "%s : %s\n";
//    private static final int AMOUNT_MILLISECONDS_TO_JOIN_IN_THREAD_ON_MAIN_THREAD = 2000 /*500*/;
//
//    public static void main(String[] args) throws InterruptedException {
//        final Thread mainThread = currentThread();
//        final Thread thread = new Thread(() -> {
//            try {
//                mainThread.join(AMOUNT_MILLISECONDS_TO_JOIN_IN_THREAD_ON_MAIN_THREAD);
//                showThreadState(currentThread());
//            } catch (final InterruptedException interruptedException) {
//
//            }
//        });
//        thread.start();
//        sleep(1000);
//        showThreadState(thread);
//
//    }

    private static final String MESSAGE_TEMPLATE_THREAD_STATE = "%s : %s\n";

    public static void main(final String... args) throws InterruptedException {
        final Thread thread = new Thread(()->{
            throw new RuntimeException();
        });
        thread.start();
        thread.join();
        showThreadState(thread);
    }

    private static void showThreadState(final Thread thread) {
        out.printf(format(MESSAGE_TEMPLATE_THREAD_STATE, thread.getName(), thread.getState()));
    }
}