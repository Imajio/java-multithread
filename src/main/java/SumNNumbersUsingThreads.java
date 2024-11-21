package main.java;

import static java.lang.System.*;
import static java.lang.Thread.*;
import static java.util.stream.IntStream.*;

public class SumNNumbersUsingThreads {
    private static final int FROM_NUMBER_FIRST_THREAD = 1;
    private static final int TO_NUMBER_FIRST_THREAD = 500;

    private static final int FROM_NUMBER_SECOND_THREAD = 501;
    private static final int TO_NUMBER_SECOND_THREAD = 1000;

    private static final String TEMPLATE_MESSAGE_THREAD_NAME_AND_NUMBER = "%s : %d\n";

    public static void main(final String... args) throws InterruptedException {

//            final TaskSummingNumbers firstTask = startSubTask(FROM_NUMBER_FIRST_THREAD, TO_NUMBER_FIRST_THREAD);
//            final TaskSummingNumbers secondTask = startSubTask(FROM_NUMBER_SECOND_THREAD, TO_NUMBER_SECOND_THREAD);
//
//            waitForTasksFinished();
//
//            final int resultNumber = firstTask.getResultNumber() + secondTask.getResultNumber();
//            printThreadNameAndNumber(resultNumber);

        final TaskSummingNumbers firstTask = new TaskSummingNumbers(FROM_NUMBER_FIRST_THREAD, TO_NUMBER_FIRST_THREAD);
        final TaskSummingNumbers secondTask = new TaskSummingNumbers(FROM_NUMBER_SECOND_THREAD, TO_NUMBER_SECOND_THREAD);

        final Thread firstThread = new Thread(firstTask);
        final Thread secondThread = new Thread(secondTask);

        firstThread.start();
        secondThread.start();

        waitForTasksFinished(firstThread, secondThread);

        final int resultNumber = firstTask.getResultNumber() + secondTask.getResultNumber();
        printThreadNameAndNumber(resultNumber);

    }

    private static TaskSummingNumbers startSubTask(final int fromNumber, final int toNumber) {
        final TaskSummingNumbers subTask = new TaskSummingNumbers(fromNumber, toNumber);
        final Thread thread = new Thread(subTask);
        thread.start();
        return subTask;
    }

    private static void printThreadNameAndNumber(final  int number) {
        out.printf(TEMPLATE_MESSAGE_THREAD_NAME_AND_NUMBER, currentThread().getName(), number);
    }

    private static void waitForTasksFinished(final Thread... threads) throws InterruptedException {
        for (Thread thread : threads) {
            thread.join();
        }
    }

    private static final class TaskSummingNumbers implements Runnable {
        private static final int INITIAL_VALUE_RESULT_NUMBER = 0;

        private final int fromNumber;
        private final int toNumber;
        private int  resultNumber;

        public TaskSummingNumbers(final int fromNumber, final int toNumber) {
            this.fromNumber = fromNumber;
            this.toNumber = toNumber;
            this.resultNumber = INITIAL_VALUE_RESULT_NUMBER;
        }

        public int getResultNumber() {
            return this.resultNumber;
        }

        @Override
        public void run() {
            rangeClosed(this.fromNumber, this.toNumber).forEach(i -> resultNumber += i);
            printThreadNameAndNumber(this.resultNumber);
        }
    }
}
