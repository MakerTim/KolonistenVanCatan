package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;

import nl.groep4.kvc.common.enumeration.Color;

public interface UpdateLobby extends Updatable<Lobby> {

    public void updatePlayerColor(Player pl, Color newColor) throws RemoteException;

    public void start(KolonistenVanCatan model) throws RemoteException;

}
