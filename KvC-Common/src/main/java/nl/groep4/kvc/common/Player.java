package nl.groep4.kvc.common;

import java.io.Serializable;

import nl.groep4.kvc.common.enumeration.Color;

/**
 * Stores username and color
 * 
 * @version 1.0
 * @author Tim
 */
public interface Player extends Serializable {

    public String getUsername();

    /**
     * Gets username
     * 
     * @return
     * @version 1.0
     * @author Tim
     */
    public Color getColor();

    /**
     * 
     * @param color
     * @version 1.0
     * @author Tim
     */
    public void setColor(Color color);
    /**
     * 
     * @return
     * @version 1.0
     * @author Tim
     */

}
