package nl.groep4.kvc.client;

import javafx.application.Application;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.scene.SceneMap;

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
	new Thread(() -> {
	    try {
		Thread.sleep(10000);
	    } catch (Exception ex) {
	    }
	    System.out.println("scenemap");
	    ViewMaster.setScene(new SceneMap());
	}).start();
	Application.launch(ViewMaster.class, args);
    }

}
