package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.enumeration.GameState;
import nl.groep4.kvc.common.enumeration.Resource;
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

    public void updateModel() throws RemoteException;

    public List<Player> getPlayers() throws RemoteException;

    public List<Player> getPlayersOrded() throws RemoteException;

    public Player getTurn() throws RemoteException;

    public void placeBuilding(Player newOwner, Coordinate coord, BuildingType type) throws RemoteException;

    public void placeStreet(Player newOwner, Coordinate coord) throws RemoteException;

    public void throwDices() throws RemoteException;

    public void distribute() throws RemoteException;

    public void buyStreet() throws RemoteException;

    public void buyVillage() throws RemoteException;

    public void buyCity() throws RemoteException;

    public void buyCard() throws RemoteException;

    public void useCard() throws RemoteException;

    public void addTrade(Player player, java.util.Map<Resource, Integer> request,
	    java.util.Map<Resource, Integer> reward) throws RemoteException;

    public void remvoeTrade(UUID key) throws RemoteException;

    public void openPausePane(Player requester) throws RemoteException;

    public void reconnect() throws RemoteException;
}
