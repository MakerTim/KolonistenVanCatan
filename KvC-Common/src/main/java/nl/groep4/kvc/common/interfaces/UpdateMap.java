package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;
import java.util.Collection;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.enumeration.SelectState;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Street;

public interface UpdateMap
	extends Updatable<Map>, UpdateDice, UpdateStock, UpdateCosts, UpdateTrade, UpdateRound, UpdateScore {

    public void closeOverlay() throws RemoteException;

    public void openBuildPane() throws RemoteException;

    public void openOptionPane() throws RemoteException;

    public void openRulesPane() throws RemoteException;

    public void openDicePane(boolean isOwnTurn) throws RemoteException;

    public void openBuyPane() throws RemoteException;

    public void openTradePane() throws RemoteException;

    public void openCreditsPane() throws RemoteException;

    public void openPausePane() throws RemoteException;

    public void openSavePane() throws RemoteException;

    public void highlightStreets(Collection<Street> streets) throws RemoteException;

    public void highlightBuildings(Collection<Building> buildings, BuildingType type) throws RemoteException;

    public void blockActions() throws RemoteException;

    public void unblockActions() throws RemoteException;

    public void setSelectable(SelectState selectables) throws RemoteException;
}
