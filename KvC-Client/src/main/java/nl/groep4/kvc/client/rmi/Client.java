package nl.groep4.kvc.client.rmi;

import java.rmi.RemoteException;
import java.util.logging.Logger;

public class Client implements nl.groep4.kvc.common.Client {
	private static final long serialVersionUID = -1934522345656512264L;

	private Logger log = Logger.getGlobal();

	@Override
	public void logFine(String msg) throws RemoteException {
		log.fine(msg);
	}

	@Override
	public void logInfo(String msg) throws RemoteException {
		log.info(msg);
	}

	@Override
	public void logWarn(String msg) throws RemoteException {
		log.warning(msg);
	}

}
