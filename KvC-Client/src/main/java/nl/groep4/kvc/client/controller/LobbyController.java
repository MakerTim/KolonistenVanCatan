package nl.groep4.kvc.client.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.Lobby;
import nl.groep4.kvc.common.Player;
import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.Updatable;

public class LobbyController {

    private Lobby lobby;

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
    }

    public void registerScene(Updatable<Lobby> sceneLobby) {
	try {
	    lobby.registerUpdateable((Updatable<Lobby>) UnicastRemoteObject.exportObject(sceneLobby, 0));
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	    // TODO: handle exception
	}
    }

}
