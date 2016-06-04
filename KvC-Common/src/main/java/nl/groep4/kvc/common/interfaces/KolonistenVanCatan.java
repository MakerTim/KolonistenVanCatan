package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import nl.groep4.kvc.common.map.Map;

public interface KolonistenVanCatan extends Remote {

    public Map getMap() throws RemoteException;

    public List<Player> getPlayers() throws RemoteException;

}
