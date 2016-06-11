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
 * Controls all lobby features
 * 
 * @author Tim
 * @version 1.0
 */
public class LobbyController implements Controller {

    private Lobby model;

    /**
     * sets model
     * 
     * @param model
     *            references to lobby for connection
     */
    public LobbyController(Lobby model) {
	this.model = model;
    }

    /**
     * generates a list of players which are connected to the lobby
     * 
     * @return a list of players
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
     * disconnects the player from the server
     * 
     * @param pl
     *            references to ClientRefrence for disconnect
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
     * changes the color of a player
     * 
     * @param player
     *            name of player
     * @param color
     *            color to change
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
     * Starts the game
     */
    public void startGame() {
	SoundUtil.stopThemesong();
	try {
	    model.startGame();
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    public void start(KolonistenVanCatan model) {
	SceneMap mapview = new SceneMap();
	mapview.registerController(new MapController(model));
	ViewMaster.setScene(mapview);
	try {
	    mapview.setModel(model.getMap());
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
	ClientRefrence.registerUpdateable(mapview);
    }
}
