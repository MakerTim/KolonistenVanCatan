package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;
import java.util.List;

public interface UpdatePlayerOrder {

    public void updatePlayerOrder(List<Player> order) throws RemoteException;

}
