package nl.groep4.kvc.server.model;

import java.rmi.Remote;
import java.rmi.RemoteException;

public class ServerDice implements Remote {

    private int dice;

    public void throwDice() throws RemoteException {
	dice = 1 + (int) (Math.random() * 6D);
    }

    public int getValue() throws RemoteException {
	return dice;
    }

}
