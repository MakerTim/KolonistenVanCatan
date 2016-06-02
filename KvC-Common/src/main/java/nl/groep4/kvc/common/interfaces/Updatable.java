package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Updatable<T> extends Remote {

    public void update(T model) throws RemoteException;

}
