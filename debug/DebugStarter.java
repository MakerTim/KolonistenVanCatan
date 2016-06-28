import java.rmi.RemoteException;

import nl.groep4.kvc.client.ClientStarter;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.util.Scheduler;
import nl.groep4.kvc.server.LongestRoadAlgorithTester;
import nl.groep4.kvc.server.ServerStarter;

public class DebugStarter {

    public static void main(String[] args) {
	if (args.length > 0) {
	    System.out.println(args[0]);
	    if (args[0].equals("maptester")) {
		Scheduler.runSyncLater(() -> {
		    SceneMap view = (SceneMap) ViewMaster.getLastScene();
		    LongestRoadAlgorithTester test = new LongestRoadAlgorithTester();
		    test.setupMap();
		    try {
			view.setModel(test.getMap());
		    } catch (RemoteException ex) {
			ex.printStackTrace();
		    }
		    Scheduler.runAsyncLater(() -> {
			test.algorithmCheck();
			test.algorithmDoubleCheck();
		    }, 100L);
		}, 1000L);
		ClientStarter.main(new String[] { "--mapstarter" });
	    }
	    if (args[0].equals("fulltest")) {
		Scheduler.runAsync(() -> ServerStarter.main(new String[0]));
		Scheduler.runAsync(() -> ClientStarter.main(new String[0]));
	    }
	}
    }

}
