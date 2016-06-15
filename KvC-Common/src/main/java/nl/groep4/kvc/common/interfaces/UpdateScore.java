package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;

public interface UpdateScore {

    public void updateScore(Player pl, int score) throws RemoteException;
}
