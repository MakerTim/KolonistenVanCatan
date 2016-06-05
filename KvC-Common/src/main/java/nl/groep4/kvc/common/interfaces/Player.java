package nl.groep4.kvc.common.interfaces;

import java.io.Serializable;

/**
 * Stores username and color
 * 
 * @version 1.0
 * @author Tim
 */
public interface Player extends Serializable {

    public default boolean hasConnectionErrors() {
	return getUpdateable() == null;
    }

    /**
     * 
     * @return gets the name of a player
     */
    public String getUsername();

    /**
     * 
     * @param updatable
     *            reference to update the server lobby
     */
    public void registerUpdateable(Updatable<?> updatable);

    /**
     * 
     * @return gets update for the server lobby
     */
    public Updatable<?> getUpdateable();
}
