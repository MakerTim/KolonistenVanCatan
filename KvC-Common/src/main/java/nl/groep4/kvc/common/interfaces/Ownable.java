package nl.groep4.kvc.common.interfaces;

/**
 * Tells items ownable by player.
 * 
 * @version 1.1
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
     * 
     * @param player
     *            Sets owner of current object.
     */
    public void setOwner(Player player);

}
