package nl.groep4.kvc.common;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote, Serializable {

	/**
	 * @param msg
	 *            De message die in een log naar client op Level#FINE
	 * @throws RemoteException
	 *             mocht er een remote probleem zijn
	 * @see java.util.logging.Level
	 */
	public void logFine(String msg) throws RemoteException;

	/**
	 * @param msg
	 *            De message die in een log naar client op Level#INFO
	 * @throws RemoteException
	 *             mocht er een remote probleem zijn
	 * @see java.util.logging.Level
	 */
	public void logInfo(String msg) throws RemoteException;

	/**
	 * @param msg
	 *            De message die in een log naar client op Level#WARNING
	 * @throws RemoteException
	 *             mocht er een remote probleem zijn
	 * @see java.util.logging.Level
	 */
	public void logWarn(String msg) throws RemoteException;
}
