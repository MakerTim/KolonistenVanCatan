package nl.groep4.kvc.common.interfaces;

/**
 * Tells items ownable by player.
 * 
 * @version 1.2
 * @author Tim
 */
public interface Ownable {
    /**
     * Gets owner.
     * 
     * @return Retrieves owner.
     */
    public Player getOwner();

    /**
     * Sets owner.
     * 
     * @param player
     *            Sets owner of current object.
     */
    public void setOwner(Player player);

}
