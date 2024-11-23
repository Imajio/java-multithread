package main.java;

import java.util.concurrent.TimeUnit;

import static java.lang.System.*;
import static java.lang.Thread.*;
import static java.util.concurrent.TimeUnit.*;

public class ThreadsTerminatingExample {

    private static final String MESSAGE_REQUEST_WAS_SENT = "Request was sent";
    private static final int DURATION_INSECONDS_DELIVERINGRESPONSE = 1;
    private static final String MESSAGE_RESPONSE_WAS_RECEIVED = "Response was received.\n";
    private static final String MESSAGE_SERVER_WAS_STOPPED = "Server was stopped.";
    private static final int TIME_WAITING_IN_SECONDS_TO_STOPPING_THREAD = 5;
    private static final String MESSAGE_THREAD_WAS_INTERRUPTED = "Thread was interrupted.";

    private static volatile boolean running = true;

    public static void main(String[] args) throws InterruptedException {
        final Thread communicatingThread = new Thread(() -> {
//            while (true) {
//                try {
//                    doRequest();
//                } catch (final InterruptedException interruptedException) {
//                    currentThread().interrupt();
//                    out.println(currentThread().isInterrupted());
//                    out.println(MESSAGE_THREAD_WAS_INTERRUPTED);
//                }
//            }
            try {
//                while (running) {
//                while (!currentThread().isInterrupted()) {
                while (true) {
                    doRequest();
                }
            } catch (final InterruptedException interruptedException) {
                currentThread().interrupt();
                out.println("Have thread been interrupted? - "+currentThread().isInterrupted());
//                out.println(MESSAGE_THREAD_WAS_INTERRUPTED);
            }
        });

        communicatingThread.start();

        final Thread stoppingThread = new Thread(() -> {
            if (isServerSouldBeOffed()) {
//                communicatingThread.stop();  old
//                running = false;
                communicatingThread.interrupt();
                stopServer();
            }
        });
        SECONDS.sleep(TIME_WAITING_IN_SECONDS_TO_STOPPING_THREAD);
        stoppingThread.start();
    }

    private static void doRequest() throws InterruptedException {
        out.println(MESSAGE_REQUEST_WAS_SENT);
        SECONDS.sleep(DURATION_INSECONDS_DELIVERINGRESPONSE);
        out.println(MESSAGE_RESPONSE_WAS_RECEIVED);
    }

    private static boolean isServerSouldBeOffed() {
        return true;
    }

    private static void stopServer() {
        out.println(MESSAGE_SERVER_WAS_STOPPED);
    }
}
