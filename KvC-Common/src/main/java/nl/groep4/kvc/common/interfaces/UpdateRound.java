package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;

import nl.groep4.kvc.common.enumeration.TurnState;

/**
 * Skeleton for the UpdateRound.
 * 
 * @author Tim
 * @version 1.1
 */
public interface UpdateRound {

    /**
     * Updates the round in the game.
     * 
     * @param round
     *            Number of the round.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void updateRound(int round) throws RemoteException;

    /**
     * Updates the turn in the game. When a player ends his turn the next player
     * is in line, and the game has to update that.
     * 
     * @param who
     *            The player whose turn it is.
     * @param what
     *            The state of the player, is it his turn or not.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void updateTurn(Player who, TurnState what) throws RemoteException;
}
