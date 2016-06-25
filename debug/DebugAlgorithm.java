import java.rmi.RemoteException;

import nl.groep4.kvc.client.ClientStarter;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.util.Scheduler;
import nl.groep4.kvc.server.LongestRoadAlgorithTester;

public class DebugAlgorithm {

    public static void main(String[] args) {
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

}
