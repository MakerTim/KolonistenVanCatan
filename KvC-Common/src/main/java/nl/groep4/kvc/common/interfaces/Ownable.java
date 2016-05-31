package nl.groep4.kvc.common.interfaces;

import nl.groep4.kvc.common.Player;

/**
 * Tells items ownable by player
 * 
 * @version 1.0 31-5-2016
 * @author Tim
 */
public interface Ownable {
    /**
     * 
     * @return Retrieves owner
     */
    public Player getOwner();

    /**
     * 
     * @param player
     *            sets owner of current object
     */
    public void setOwner(Player player);

}
