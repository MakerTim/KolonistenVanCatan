package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Throw extends Remote {

    public void throwDice() throws RemoteException;

    public int getValue() throws RemoteException;

    public int getDiceLeft() throws RemoteException;

    public int getDiceRight() throws RemoteException;

    public boolean isBanditThrow() throws RemoteException;
}
