package nl.groep4.kvc.server;

import nl.groep4.kvc.server.console.ArgumentParser;
import nl.groep4.kvc.server.model.ServerLobby;
import nl.groep4.kvc.server.rmi.RmiServer;

/**
 * Starts the Server
 * 
 * @author Tim
 * @version 1.0
 */
public class ServerStarter {

    private static ServerLobby lobby;

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
	ArgumentParser.startParser();
    }

    public static ServerLobby getLobby() {
	return lobby;
    }

    public static void setLobby(ServerLobby lobby) {
	ServerStarter.lobby = lobby;
    }

}
