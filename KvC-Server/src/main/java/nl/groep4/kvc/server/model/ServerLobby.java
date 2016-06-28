package nl.groep4.kvc.server.model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.KolonistenVanCatan;
import nl.groep4.kvc.common.interfaces.Lobby;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.server.controller.ServerLobbyController;
import nl.groep4.kvc.server.util.LoadHelper;

/**
 * Instance of Lobby.
 * 
 * @author Tim
 * @version 1.0
 */
public class ServerLobby implements Lobby {

    protected final List<Player> players = new ArrayList<>();
    private State state = State.LOBBY;
    private KolonistenVanCatan kvc;
    private ServerLobbyController controller;

    public void setController(ServerLobbyController controller) {
	this.controller = controller;
    }

    @Override
    public void setGame(KolonistenVanCatan kvc) throws RemoteException {
	this.kvc = kvc;
    }

    @Override
    public State getState() throws RemoteException {
	return state;
    }

    @Override
    public void setState(State state) throws RemoteException {
	this.state = state;
    }

    @Override
    public Player registerPlayer(String playerName) throws RemoteException {
	return controller.registerPlayer(playerName);
    }

    @Override
    public List<Player> getPlayers() throws RemoteException {
	return players;
    }

    @Override
    public void disconnect(Player pl) throws RemoteException {
	boolean removed = controller.removePlayer(pl, true);
	System.out.printf("Player %s has %s logged off.\n", pl.getUsername(),
		removed ? "succesfully" : "unsuccesfully");
    }

    @Override
    public void setColor(Player pl, Color newColor) throws RemoteException {
	controller.setColor(pl, newColor);
    }

    @Override
    public void startGame() throws RemoteException {
	controller.startGame();
    }

    @Override
    public void loadSave(String save) throws RemoteException {
	// TODO: call this method.
	kvc = LoadHelper.loadFromSave(save);
    }

    @Override
    public KolonistenVanCatan getGame() throws RemoteException {
	return kvc;
    }
}
