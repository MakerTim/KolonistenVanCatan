package nl.groep4.kvc.client.controller;

import java.rmi.RemoteException;

import nl.groep4.kvc.client.util.ExceptionManager;
import nl.groep4.kvc.common.Lobby;
import nl.groep4.kvc.common.Player;

/**
 * Displays all client-side information
 * 
 * @author Tim
 * @version 1.0
 */
public class ConnectionController {

    private static String username;
    private static Lobby lobby;

    private ConnectionController() {
    }

    /**
     * 
     * @return gets player
     */
    public static Player getThePlayer() {
	try {
	    return lobby.getConnectedPlayers().stream().filter(pl -> pl.getUsername().equals(username)).findFirst()
		    .orElse(null);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
	return null;
    }

    /**
     * 
     * @param username
     */
    public static void setThePlayer(String username) {
	ConnectionController.username = username;
    }

    /**
     * 
     * @param lobby
     */
    public static void setLobby(Lobby lobby) {
	ConnectionController.lobby = lobby;
    }

}
