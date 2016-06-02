package nl.groep4.kvc.client.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.client.util.ExceptionManager;
import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.Lobby;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Updatable;

/**
 * 
 * 
 * @author Tim
 * @version 1.0
 */
public class LobbyController {

    private Lobby lobby;

    /**
     * 
     * @param lobby
     */
    public LobbyController(Lobby lobby) {
	this.lobby = lobby;
    }

    /**
     * 
     * @return a list of players which are connected
     */
    public List<Player> getPlayers() {
	try {
	    return lobby.getConnectedPlayers();
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
	return new ArrayList<>();
    }

    /**
     * 
     * @param pl
     */
    public void disconnect(Player pl) {
	try {
	    lobby.unregisterPlayer(pl);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
	lobby = null;
    }

    /**
     * 
     * @param player
     * @param color
     */
    public void changeColor(Player player, Color color) {
	try {
	    lobby.setColor(player, color);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    /**
     * 
     * @param sceneLobby
     */
    public void registerScene(Updatable<Lobby> sceneLobby) {
	try {
	    lobby.registerView(ConnectionController.getThePlayer(),
		    (Updatable<Lobby>) UnicastRemoteObject.exportObject(sceneLobby, 0));
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    public void startGame() {
	try {
	    lobby.startGame();
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }
}
