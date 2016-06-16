package nl.groep4.kvc.common.util;

import java.util.List;

import javafx.application.Platform;

/**
 * Utility class for multi threading
 * 
 * @version 1.0
 * @author Tim
 */
public class Scheduler {

    public static void runSyncd(List<Runnable> runs) {
	runSyncd(runs.toArray(new Runnable[runs.size()]));
    }

    public static void runSyncd(Runnable... runs) {
	Thread[] threads = new Thread[runs.length];
	for (int i = 0; i < runs.length; i++) {
	    threads[i] = new Thread(runs[i]);
	    threads[i].run();
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
		    Thread.sleep(100L);
		} catch (Exception ex) {
		}
	    }
	} while (running);
    }

    public static void runSync(Runnable run) {
	runSyncLater(run, 0L);
    }

    public static void runAsync(Runnable run) {
	runAsyncLater(run, 0L);
    }

    public static void runSyncLater(Runnable run) {
	runSyncLater(run, 1L);
    }

    public static void runAsyncLater(Runnable run) {
	runAsyncLater(run, 1L);
    }

    public static void runSyncLater(Runnable run, long millis) {
	runAsyncLater(() -> {
	    Platform.runLater(run);
	}, millis);
    }

    public static void runAsyncLater(Runnable run, long millis) {
	new Thread(() -> {
	    try {
		Thread.sleep(millis);
	    } catch (Exception ex) {
	    }
	    run.run();
	}).start();
    }
}
