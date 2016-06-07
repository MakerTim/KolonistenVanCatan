package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;

public interface UpdatablePane<T> extends Updatable<T> {

    @Override
    public default void popup(String key) throws RemoteException {
    }

    @Override
    public default void close(String key) throws RemoteException {
    }

}
