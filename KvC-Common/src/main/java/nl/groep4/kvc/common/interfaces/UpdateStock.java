package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;
import java.util.EnumMap;
import java.util.List;

import nl.groep4.kvc.common.enumeration.Resource;

public interface UpdateStock {

    public void updateStock(EnumMap<Resource, Integer> resources) throws RemoteException;

    public void updateStock(List<Card> cards) throws RemoteException;
}
