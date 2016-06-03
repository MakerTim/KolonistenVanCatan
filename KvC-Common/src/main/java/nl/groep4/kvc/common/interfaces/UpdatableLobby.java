package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;

import nl.groep4.kvc.common.enumeration.Color;

public interface UpdatableLobby extends Updatable<Lobby> {

    public void start() throws RemoteException;

    public void updateColor(Player player, Color color) throws RemoteException;

}
