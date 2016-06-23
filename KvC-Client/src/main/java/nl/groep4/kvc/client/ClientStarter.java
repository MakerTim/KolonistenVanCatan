package nl.groep4.kvc.client;

import javafx.application.Application;
import nl.groep4.kvc.client.view.ViewMaster;

/**
 * Starts the client.
 * 
 * @version 1.1
 * @author Tim
 */
public class ClientStarter {

    public static String[] args;

    /**
     * Launches the application.
     * 
     * @param args
     *            Contains the supplied command-line arguments.
     */
    public static void main(String[] args) {
	ClientStarter.args = args;
	Application.launch(ViewMaster.class, args);
    }
}
