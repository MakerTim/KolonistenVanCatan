package nl.groep4.kvc.common.map;

import nl.groep4.kvc.common.enumeration.Resource;

/**
 * Gets resource and number.
 * 
 * @author Tim
 * @version 1.0
 */
public interface TileResource extends TileLand {

    /**
     * Gets resource.
     * 
     * @return Type of resource.
     */
    public Resource getResource();

    /**
     * Gets number.
     * 
     * @return Number.
     */
    public int getNumber();
}
