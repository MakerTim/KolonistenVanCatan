package nl.groep4.kvc.client.debug;

import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.scene.SceneMap;

/**
 * Meant for debugging, has no function in actual application
 * 
 * @author Tim
 * @version 1.0
 */
public class TestMapViewSetup {

    public static void setup() {
	SceneMap view = new SceneMap();
	ViewMaster.setScene(view);
	TestMapController controller = new TestMapController(view);
	view.registerController(controller);
    }

}
