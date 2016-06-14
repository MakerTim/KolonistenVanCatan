package nl.groep4.kvc.client.debug;

import java.rmi.RemoteException;

import nl.groep4.kvc.client.controller.ClientRefrence;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.scene.SceneMap;

public class TestMapViewSetup {

    public static void setup() {
	SceneMap view = new SceneMap();
	ViewMaster.setScene(view);
	TestMapController controller = new TestMapController(view);
	controller.getMap().createMap();
	ClientRefrence.setThePlayer(controller.getPlayer());
	view.registerController(controller);
	try {
	    view.setModel(controller.getMap());
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

}
