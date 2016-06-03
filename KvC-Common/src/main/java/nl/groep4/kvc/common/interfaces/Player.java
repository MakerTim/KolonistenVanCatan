package nl.groep4.kvc.common.interfaces;

import java.io.Serializable;

import nl.groep4.kvc.common.enumeration.Color;

/**
 * Stores username and color
 * 
 * @version 1.0
 * @author Tim
 */
public interface Player extends Serializable {

    /**
     * 
     * @return gets the name of a player
     */
    public String getUsername();

    /**
     * 
     * @return gets the color of a player
     */
    public Color getColor();

    /**
     * 
     * @param color
     *            reference to set color of a player
     */
    public void setColor(Color color);

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
