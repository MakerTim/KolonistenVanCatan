package nl.groep4.kvc.common.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author Tim
 * @version 1.0
 * @param <T>
 */
public interface Updatable<T> extends Remote {

    /**
     * 
     * @param model
     * @throws RemoteException
     */
    public void update(T model) throws RemoteException;

}
