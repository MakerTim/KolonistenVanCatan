package nl.groep4.kvc.server.model;

import java.rmi.RemoteException;

import nl.groep4.kvc.common.interfaces.Throw;

/**
 * Instance of throw
 * 
 * @author Tim
 * @version 1.0
 */
public class ServerThrow implements Throw {

    private static final long serialVersionUID = 12121994L;
    private ServerDice[] dices = new ServerDice[2];

    @Override
    public void throwDice() throws RemoteException {
	for (int i = 0; i < dices.length; i++) {
	    dices[i] = new ServerDice();
	    dices[i].throwDice();
	}
    }

    @Override
    public int getValue() throws RemoteException {
	int ret = 0;
	for (ServerDice dice : dices) {
	    ret += dice.getValue();
	}
	return ret;
    }

    @Override
    public boolean isBanditThrow() throws RemoteException {
	return getValue() == 7;
    }

    @Override
    public int getDiceLeft() throws RemoteException {
	return dices[0].getValue();
    }

    @Override
    public int getDiceRight() throws RemoteException {
	return dices[1].getValue();
    }
}
