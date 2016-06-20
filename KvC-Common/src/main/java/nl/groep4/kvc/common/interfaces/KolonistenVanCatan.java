package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.enumeration.GameState;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.util.Scheduler;

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

    public void placeBuilding(Coordinate coord, BuildingType type) throws RemoteException;

    public void placeStreet(Coordinate coord) throws RemoteException;

    public void throwDices() throws RemoteException;

    public void distribute() throws RemoteException;

    public void buyStreet() throws RemoteException;

    public void buyVillage() throws RemoteException;

    public void buyCity() throws RemoteException;

}
