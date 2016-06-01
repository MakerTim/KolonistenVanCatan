package nl.groep4.kvc.client.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.client.view.SceneLobby;
import nl.groep4.kvc.common.Lobby;
import nl.groep4.kvc.common.Player;
import nl.groep4.kvc.common.enumeration.Color;

public class LobbyController {

    private Lobby lobby;
    private SceneLobby scene;

    public LobbyController(Lobby lobby) {
	this.lobby = lobby;
    }

    public List<Player> getPlayers() {
	try {
	    return lobby.getConnectedPlayers();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	    // TODO: handle exception
	}
	return new ArrayList<>();
    }

    public void disconnect(Player pl) {
	try {
	    lobby.unregisterPlayer(pl);
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	    // TODO: handle exception
	}
	lobby = null;
    }

    public void changeColor(Player player, Color color) {
	try {
	    lobby.setColor(player, color);
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	    // TODO: handle exception
	}
	update();
    }

    public void update() {
	scene.update();
    }

    public void registerScene(SceneLobby sceneLobby) {
	scene = sceneLobby;
    }

}
