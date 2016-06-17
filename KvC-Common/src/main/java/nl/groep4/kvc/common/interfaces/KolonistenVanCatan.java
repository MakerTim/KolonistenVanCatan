package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.enumeration.GameState;
import nl.groep4.kvc.common.map.Coordinate;
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

    public void start() throws RemoteException;

    public GameState getState() throws RemoteException;

    public Map getMap() throws RemoteException;

    public default void update() throws RemoteException {
	for (Player pl : getPlayers()) {
	    try {
		pl.getUpdateable(UpdateMap.class).setModel(getMap());
	    } catch (Exception ex) {
	    }
	}
    }

    public List<Player> getPlayers() throws RemoteException;

    public List<Player> getPlayersOrded() throws RemoteException;

    public void placeBuilding(Coordinate coord, Player newOwner, BuildingType type) throws RemoteException;

    public void placeStreet(Coordinate coord, Player newOwner) throws RemoteException;

    public void throwDices() throws RemoteException;

    public void distrube() throws RemoteException;
}
