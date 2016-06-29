package nl.groep4.kvc.server;

import nl.groep4.kvc.common.interfaces.Lobby;
import nl.groep4.kvc.server.console.ArgumentParser;
import nl.groep4.kvc.server.rmi.RmiServer;

/**
 * Starts the Server
 * 
 * @author Tim
 * @version 1.1
 */
public class ServerStarter {

    private static Lobby lobby;

    /**
     * Gives port number 1099.
     * 
     * @param args
     *            Reference to Main.
     *
     */
    public static void main(String[] args) {
	//
	System.out.println("Starting server...");
	int port = 1099;
	if (args.length > 0 && args[0].matches("\\d+")) {
	    port = Integer.parseInt(args[0]);
	}
	if (args.length > 1 && !args[1].isEmpty()) {
	    System.setProperty("java.rmi.server.hostname", args[1]);
	    RmiServer.init(port, args[1]);
	}
	RmiServer.init(port, null);
	ArgumentParser.startParser();
    }

    /**
     * Gets the Lobby object.
     * 
     * @return The Lobby.
     */
    public static Lobby getLobby() {
	return lobby;
    }

    /**
     * 
     * @param lobby
     *            The Lobby will be given here.
     * 
     */

    public static void setLobby(Lobby lobby) {
	ServerStarter.lobby = lobby;
    }

}
