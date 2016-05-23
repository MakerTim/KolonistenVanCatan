package nl.groep4.kvk.client.rmi;

import java.rmi.RemoteException;

public class Client implements nl.groep4.kvk.common.Client {
	private static final long serialVersionUID = -1934522345656512264L;

	@Override
	public void debugClient(String mssg) throws RemoteException {
		System.out.println(mssg);
	}

}
