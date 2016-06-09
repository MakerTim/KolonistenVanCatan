package nl.groep4.kvc.client;

import javafx.application.Application;
import javafx.application.Platform;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.server.model.map.ServerMap;

/**
 * Starts the client
 * 
 * @version 1.0
 * @author Tim
 */
public class ClientStarter {

    /**
     * Launches the application
     * 
     * @param args
     *            contains the supplied command-line arguments
     */
    public static void main(String[] args) {
	Map servermap = new ServerMap();
	servermap.createMap();
	new Thread(() -> {
	    try {
		Thread.sleep(4000);
	    } catch (Exception ex) {
	    }
	    System.out.println("SceneMap");
	    SceneMap map = new SceneMap();
	    ViewMaster.setScene(map);
	    Platform.runLater(() -> {
		try {
		    map.setModel(servermap);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    });
	}).start();
	Application.launch(ViewMaster.class, args);
    }

}
