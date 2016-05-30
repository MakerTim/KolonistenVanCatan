package nl.groep4.kvc.common;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote, Serializable {

    /**
     * Logs data to the console on Fine level
     * 
     * @version 1.0
     * @author Tim
     */
    public void logFine(String msg) throws RemoteException;

    /**
     * Logs data to the console on Info level
     * 
     * @version 1.0
     * @author Tim
     */
    public void logInfo(String msg) throws RemoteException;

    /**
     * Logs data to the console on Warn level
     * 
     * @version 1.0
     * @author Tim
     */
    public void logWarn(String msg) throws RemoteException;
}
