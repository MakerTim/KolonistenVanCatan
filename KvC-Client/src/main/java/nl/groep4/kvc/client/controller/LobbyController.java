package nl.groep4.kvc.client.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.client.util.ExceptionManager;
import nl.groep4.kvc.client.util.SoundUtil;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.scene.SceneLogin;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.KolonistenVanCatan;
import nl.groep4.kvc.common.interfaces.Lobby;
import nl.groep4.kvc.common.interfaces.Player;

/**
 * Controls all lobby features.
 * 
 * @author Tim
 * @version 1.1
 */
public class LobbyController implements Controller {

    private Lobby model;

    /**
     * Sets model.
     * 
     * @param model
     *            The lobby.
     */
    public LobbyController(Lobby model) {
	this.model = model;
    }

    /**
     * Generates a list of players which are connected to the lobby.
     * 
     * @return A list of players.
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
     * Disconnects the player from the server.
     * 
     * @param pl
     *            The player in the lobby.
     */
    public void disconnect(Player pl) {
	try {
	    model.disconnect(pl);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
	model = null;
	ViewMaster.setScene(new SceneLogin());
	ClientRefrence.setThePlayer(null);
    }

    /**
     * Changes the color of a player.
     * 
     * @param player
     *            The player in the lobby.
     * @param color
     *            The color of the player.
     * 
     */
    public void changeColor(Player player, Color color) {
	try {
	    model.setColor(player, color);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    /**
     * Starts the game and stops the themesong from the lobby.
     */
    public void startGame() {
	SoundUtil.stopThemesong();
	try {
	    model.startGame();
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    /**
     * Lets the player reconnect into the game.
     * 
     * @param pl
     *            Name of player that will be compared with all players that
     *            joined the game.
     */
    public void reopenGame(Player pl) {
	try {
	    for (Player player : model.getGame().getPlayers()) {
		if (player.equals(pl)) {
		    try {
			player.getUpdateable().close("other");
		    } catch (RemoteException ex) {
		    }
		}
	    }
	    start();
	    model.getGame().reconnect();
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    /**
     * When clicked on, the map will be set and the game will start.
     */
    public void start() {
	try {
	    KolonistenVanCatan model = this.model.getGame();
	    SceneMap mapview = new SceneMap();
	    mapview.registerController(new MapController(model, mapview));
	    ViewMaster.setScene(mapview);
	    try {
		mapview.setModel(model.getMap());
	    } catch (RemoteException ex) {
		ex.printStackTrace();
	    }
	    ClientRefrence.registerUpdateable(mapview);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }
}
