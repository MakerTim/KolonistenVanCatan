package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;

import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.map.Map;

public interface UpdateLobby extends Updatable<Lobby> {

    public void updatePlayerColor(Player pl, Color newColor) throws RemoteException;

    public void start(Map map) throws RemoteException;

}
