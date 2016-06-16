package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;

public interface UpdateDice {

    public void updateDices(int dice1, int dice2) throws RemoteException;

}
