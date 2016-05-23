package nl.groep4.kvk.common;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote, Serializable {

	/**
	 * @param mssg
	 *            TIJDELIJK - om melding door te sturen
	 * @throws RemoteException
	 *             mocht er een remote probleem zijn, throw die
	 */
	public void debugClient(String mssg) throws RemoteException;

}
