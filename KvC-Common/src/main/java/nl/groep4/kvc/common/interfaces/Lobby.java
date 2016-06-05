package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Lobby extends Remote {

    public Player registerPlayer(String player) throws RemoteException;

    public void registerUpdatable(Player pl, Updatable<?> update) throws RemoteException;
}
