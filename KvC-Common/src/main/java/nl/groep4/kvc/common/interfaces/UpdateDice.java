package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;

/**
 * Updates dice.
 * 
 * @author Tim
 * @version 1.1
 */
public interface UpdateDice {

    /**
     * Updates dice1 and dice2.
     * 
     * @param dice1
     *            Left dice value.
     * @param dice2
     *            Right dice value.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void updateDices(int dice1, int dice2) throws RemoteException;

}
