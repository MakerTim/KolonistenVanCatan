package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import nl.groep4.kvc.common.map.Map;

public interface KolonistenVanCatan extends Remote {

    public void createMap() throws RemoteException;

    /**
     * 
     * @return gets current round
     */
    public int getRound() throws RemoteException;

    public void nextRound() throws RemoteException;

    public void nextTurn() throws RemoteException;

    public Map getMap() throws RemoteException;

    public List<Player> getPlayers() throws RemoteException;

    public List<Player> getPlayersOrded() throws RemoteException;

}
