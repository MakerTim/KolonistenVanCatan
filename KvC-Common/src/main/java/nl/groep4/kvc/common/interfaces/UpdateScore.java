package nl.groep4.kvc.common.interfaces;

import java.rmi.RemoteException;

/**
 * Skeleton of UpdateScore.
 * 
 * @author Tim
 * @version 1.0
 */
public interface UpdateScore {

    /**
     * Updates the score from the player.
     * 
     * @param pl
     *            The player in the game
     * @param score
     *            The score from the player
     * @throws RemoteException
     *             Any remotely invoked method
     */
    public void updateScore(Player pl, int score) throws RemoteException;
}
