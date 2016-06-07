package nl.groep4.kvc.client;

import javafx.application.Application;
import nl.groep4.kvc.client.view.ViewMaster;

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
     *            Reference to Main
     */
    public static void main(String[] args) {
	Application.launch(ViewMaster.class, args);
    }
}
