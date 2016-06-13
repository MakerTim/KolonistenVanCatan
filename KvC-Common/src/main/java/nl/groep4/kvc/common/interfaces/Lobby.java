package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import nl.groep4.kvc.common.enumeration.Color;

public interface Lobby extends Remote {

    public static enum State {
	LOBBY, IN_GAME
    }

    public Player registerPlayer(String player) throws RemoteException;

    public List<Player> getPlayers() throws RemoteException;

    public void disconnect(Player pl) throws RemoteException;

    public void setColor(Player pl, Color newColor) throws RemoteException;

    public void startGame() throws RemoteException;

    public void cleanup() throws RemoteException;

    public void loadSave(String save) throws RemoteException;
}
