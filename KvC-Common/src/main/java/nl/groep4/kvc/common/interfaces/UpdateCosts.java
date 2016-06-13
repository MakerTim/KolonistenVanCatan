package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;
import java.util.EnumMap;

import nl.groep4.kvc.common.enumeration.Resource;

public interface UpdateCosts {

    public void updateStreetCosts(EnumMap<Resource, Integer> resources) throws RemoteException;

    public void updateVillageCosts(EnumMap<Resource, Integer> resources) throws RemoteException;

    public void updateCityCosts(EnumMap<Resource, Integer> resources) throws RemoteException;

}
