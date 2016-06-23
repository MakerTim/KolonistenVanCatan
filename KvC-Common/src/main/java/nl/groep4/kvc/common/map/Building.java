package nl.groep4.kvc.common.map;

import java.io.Serializable;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.interfaces.Ownable;

/**
 * Defining building type.
 * 
 * @version 1.1
 * @author Tim
 */
public interface Building extends Ownable, Serializable, Locatable {

    /**
     * Registers tile.
     * 
     * @param tile
     *            Tile to register.
     */
    public void registerTile(Tile tile);

    /**
     * Gets connected tiles.
     * 
     * @return The connected tiles.
     */
    public Tile[] getConnectedTiles();

    /**
     * Gets building types.
     * 
     * @return Sets building type.
     */
    public BuildingType getBuildingType();

    /**
     * Sets building type.
     * 
     * @param type
     *            Kind of building.
     */
    public void setBuildingType(BuildingType type);

}