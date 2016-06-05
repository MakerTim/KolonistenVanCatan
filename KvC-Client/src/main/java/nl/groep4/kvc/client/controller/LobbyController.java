package nl.groep4.kvc.client.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.client.util.ExceptionManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.scene.SceneLogin;
import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.Lobby;
import nl.groep4.kvc.common.interfaces.Player;

/**
 * 
 * 
 * @author Tim
 * @version 1.0
 */
public class LobbyController {

    private Lobby model;

    /**
     * 
     * @param lobby
     *            references to lobby for connection
     */
    public LobbyController(Lobby model) {
	this.model = model;
    }

    /**
     * 
     * @return a list of players which are connected
     */
    public List<Player> getPlayers() {
	try {
	    return model.getPlayers();
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
	    model.disconnect(pl);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
	model = null;
	ViewMaster.setScene(new SceneLogin());
    }

    /**
     * 
     * @param player
     * @param color
     */
    public void changeColor(Player player, Color color) {
	try {
	    model.setColor(player, color);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    public void startGame() {
	try {
	    model.startGame();
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }
}
