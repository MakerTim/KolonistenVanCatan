package nl.groep4.kvc.common;

import java.io.Serializable;

import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.interfaces.Updatable;

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
     * @version 1.0
     * @author Tim
     */
    public String getUsername();

    /**
     * 
     * @return gets the color of a player
     * @version 1.0
     * @author Tim
     */
    public Color getColor();

    /**
     * sets color of a player
     * 
     * @param color
     * @version 1.0
     * @author Tim
     */
    public void setColor(Color color);

    public void registerUpdateable(Updatable<?> updatable);

    public Updatable<?> getUpdateable();
}
