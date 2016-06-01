package nl.groep4.kvc.client.controller;

import nl.groep4.kvc.client.view.ExceptionDialog;
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
		    .get();
	} catch (Exception ex) {
	    ExceptionDialog.error(ex);
	}
	return null;
    }

    public static Player setThePlayer(Player thePlayer) {
	ConnectionController.username = thePlayer.getUsername();
	return thePlayer;
    }

    public static void setLobby(Lobby lobby) {
	ConnectionController.lobby = lobby;
    }

}
