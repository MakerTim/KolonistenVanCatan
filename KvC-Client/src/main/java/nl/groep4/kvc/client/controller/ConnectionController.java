package nl.groep4.kvc.client.controller;

import java.rmi.RemoteException;

import nl.groep4.kvc.client.util.ExceptionManager;
import nl.groep4.kvc.common.Lobby;
import nl.groep4.kvc.common.Player;

public class ConnectionController {

    private static String username;
    private static Lobby lobby;

    private ConnectionController() {
    }

    public static Player getThePlayer() {
	try {
	    return lobby.getConnectedPlayers().stream().filter(pl -> pl.getUsername().equals(username)).findFirst()
		    .orElse(null);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
	return null;
    }

    public static void setThePlayer(String username) {
	ConnectionController.username = username;
    }

    public static void setLobby(Lobby lobby) {
	ConnectionController.lobby = lobby;
    }

}
