package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;

/**
 * Updates dice
 * 
 * @author Tim
 * @version 1.0
 */
public interface UpdateDice {

    /**
     * Updates dice1 and dice2
     * 
     * @param dice1
     *            left dice value
     * @param dice2
     *            right dice value
     * @throws RemoteException
     *             signals when exception occurs
     */
    public void updateDices(int dice1, int dice2) throws RemoteException;

}
