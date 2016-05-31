package nl.groep4.kvc.common;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Enables logging of data
 * 
 * @version 1.0
 * @author Tim
 *
 */
public interface Client extends Remote, Serializable {

    /**
     * Logs data to the console on Fine level
     * 
     * @param msg
     *            Unspecified return message
     * @throws RemoteException
     */
    public void logFine(String msg) throws RemoteException;

    /**
     * Logs data to the console on Info level
     * 
     * @param msg
     *            Unspecified return message
     * @throws RemoteException
     */
    public void logInfo(String msg) throws RemoteException;

    /**
     * Logs data to the console on Warn level
     * 
     * @param msg
     *            Unspecified return message
     * @throws RemoteException
     */
    public void logWarn(String msg) throws RemoteException;
}
