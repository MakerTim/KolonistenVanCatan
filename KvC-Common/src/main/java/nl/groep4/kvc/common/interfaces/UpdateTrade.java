package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;
import java.util.List;

public interface UpdateTrade {

    public void updateTrades(List<Trade> allTrades) throws RemoteException;

}
