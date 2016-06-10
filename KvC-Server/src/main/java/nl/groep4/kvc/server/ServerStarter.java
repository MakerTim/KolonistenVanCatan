package nl.groep4.kvc.server;

import nl.groep4.kvc.server.rmi.RmiServer;

/**
 * Starts the Server
 * 
 * @author Tim
 * @version 1.0
 */
public class ServerStarter {

    /**
     * gives port number 1099
     * 
     * @param args
     *            Reference to Main
     *
     */
    public static void main(String[] args) {
	System.out.println("Starting server...");
	int port = 1099;
	if (args.length > 0 && args[0].matches("\\d+")) {
	    port = Integer.parseInt(args[0]);
	}
	RmiServer.init(port);
    }

}
