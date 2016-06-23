package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;

import nl.groep4.kvc.common.enumeration.TurnState;

/**
 * Skeleton fot the UpdateRound
 * 
 * @author Tim
 * @version
 */
public interface UpdateRound {

    /**
     * Updates the round in the game
     * 
     * @param round
     *            number of the round
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void updateRound(int round) throws RemoteException;

    /**
     * Updates the turn in the game. When a player ends his turn the next player
     * is in line, and the game has to update that
     * 
     * @param who
     *            the player whose turn it is
     * @param what
     *            the state of the player, is it his turn or not
     * @throws RemoteException
     *             any remotely invoked method
     */
    public void updateTurn(Player who, TurnState what) throws RemoteException;
}
