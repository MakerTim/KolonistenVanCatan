package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;

import nl.groep4.kvc.common.map.Map;

public interface UpdateMap extends Updatable<Map>, UpdateStock, UpdateCosts {

    public void closeOverlay() throws RemoteException;

    public void openBuildPane() throws RemoteException;

    public void openOptionPane() throws RemoteException;

    public void openRulesPane() throws RemoteException;

    public void openDicePane() throws RemoteException;

    public void openBuyPane() throws RemoteException;

    public void openTradePane() throws RemoteException;

}
