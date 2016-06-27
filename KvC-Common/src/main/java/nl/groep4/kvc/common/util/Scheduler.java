package nl.groep4.kvc.common.util;

import java.util.List;

import javafx.application.Platform;

/**
 * Utility class for multi threading.
 * 
 * @version 1.1
 * @author Tim
 */
public class Scheduler {

    /**
     * See {@link Scheduler#runAsyncdSync(Runnable...)}
     * 
     * @param runs
     *            A group of methods.
     */
    public static void runAsyncdSync(List<Runnable> runs) {
	runAsyncdSync(runs.toArray(new Runnable[runs.size()]));
    }

    /**
     * This method executes multiple threads asynchronously. All the threads
     * wait until they are all done.
     * 
     * @param runs
     *            A group of methods.
     */
    public static void runAsyncdSync(Runnable... runs) {
	Thread[] threads = new Thread[runs.length];
	for (int i = 0; i < runs.length; i++) {
	    Thread thread = new Thread(runs[i]);
	    thread.setName(String.format("AsyncSynced [%d/%d] from %s.", i, runs.length, caller()));
	    thread.start();
	    threads[i] = thread;
	}
	boolean running;
	do {
	    running = false;
	    for (Thread thread : threads) {
		if (thread.isAlive()) {
		    running = true;
		    break;
		}
	    }
	    if (running) {
		try {
		    Thread.sleep(10L);
		} catch (Exception ex) {
		}
	    }
	} while (running);
    }

    /**
     * See {@link Scheduler#runSyncLater(Runnable, long)}
     * 
     * @param run
     *            A bunch of methods.
     */
    public static void runSync(Runnable run) {
	runSyncLater(run, 0L);
    }

    /**
     * See {@link Scheduler#runAsyncLater(Runnable, long)}
     * 
     * @param run
     *            A bunch of methods.
     */
    public static void runAsync(Runnable run) {
	runAsyncLater(run, 0L);
    }

    /**
     * See {@link Scheduler#runSyncLater(Runnable, long)}
     * 
     * @param run
     *            A bunch of methods.
     */
    public static void runSyncLater(Runnable run) {
	runSyncLater(run, 1L);
    }

    /**
     * See {@link Scheduler#runAsyncLater(Runnable, long)}
     * 
     * @param run
     *            A bunch of methods.
     */
    public static void runAsyncLater(Runnable run) {
	runAsyncLater(run, 1L);
    }

    /**
     * Makes sure that the bunch of methods are in the javaFX.
     * 
     * @param run
     *            A bunch of methods.
     * @param millis
     *            The milliseconds.
     */
    public static void runSyncLater(Runnable run, long millis) {
	runAsyncLater(() -> {
	    Platform.runLater(run);
	}, millis);
    }

    /**
     * Waits a few milliseconds before the thread will start.
     * 
     * @param run
     *            A bunch of methods.
     * @param millis
     *            The milliseconds.
     */
    public static void runAsyncLater(Runnable run, long millis) {
	Thread thread = new Thread(() -> {
	    try {
		Thread.sleep(millis);
	    } catch (Exception ex) {
	    }
	    run.run();
	});

	thread.setName(String.format("runAsyncLater from %s.", caller()));
	thread.start();
    }

    private static String caller() {
	String caller = "";
	for (StackTraceElement call : Thread.currentThread().getStackTrace()) {
	    if (!call.isNativeMethod() && !call.getClassName().equals(Scheduler.class.getName())
		    && call.getLineNumber() > 0) {
		caller = call.getClassName().replaceAll("(.+\\.)", "") + ".java:" + call.getLineNumber() + " ["
			+ call.getMethodName() + "]";
	    }
	}
	return caller;
    }
}
