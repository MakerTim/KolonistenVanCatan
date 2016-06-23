package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;

/**
 * Updates panes and contains pop-up and close
 * 
 * @author Tim
 * @version 1.0
 * 
 * @param <T>
 *            pane class which used
 */
public interface UpdatablePane<T> extends Updatable<T> {

    @Override
    public default void popup(String key) throws RemoteException {
    }

    @Override
    public default void close(String key) throws RemoteException {
    }

}
