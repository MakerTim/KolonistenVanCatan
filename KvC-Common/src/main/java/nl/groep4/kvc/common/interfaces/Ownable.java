package nl.groep4.kvc.common.interfaces;

import nl.groep4.kvc.common.Player;

/**
 * Defines items ownable by player
 * 
 * @version 1.0
 * @author Tim
 */
public interface Ownable {

    public Player getOwner();

    public void setOwner(Player player);

}
