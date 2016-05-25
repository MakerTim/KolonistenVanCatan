package nl.groep4.kvc.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

import nl.groep4.kvc.common.map.Map;

public interface KolonistenVanCatan extends Remote {

	public void registerClient(Client client) throws RemoteException;

	public void unregisterClient(Client client) throws RemoteException;

	public Map getMap() throws RemoteException;

}
