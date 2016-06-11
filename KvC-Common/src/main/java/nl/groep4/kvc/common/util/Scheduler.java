package nl.groep4.kvc.common.util;

import java.util.List;

/**
 * Utility class for multi threading
 * 
 * @version 1.0
 * @author Tim
 */
public class Scheduler {

    public static void runSync(List<Runnable> runs) {
	runSync(runs.toArray(new Runnable[runs.size()]));
    }

    public static void runSync(Runnable... runs) {
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
		    Thread.sleep(100);
		} catch (Exception ex) {
		}
	    }
	} while (running);
    }

    public static void runAsync(Runnable run) {
	new Thread(run).start();
    }

    public static void runAsyncLater(Runnable run) {
	runAsyncLater(run, 1);
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
