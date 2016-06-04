package nl.groep4.kvc.common.util;

/**
 * Utility class for multi threading
 * 
 * @version 1.0
 * @author Tim
 */
public class Scheduler {

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
