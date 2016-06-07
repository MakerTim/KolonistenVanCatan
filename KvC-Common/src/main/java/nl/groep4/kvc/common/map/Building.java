package nl.groep4.kvc.common.map;

import java.io.Serializable;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.interfaces.Ownable;

/**
 * Defining building type
 * 
 * @version 1.0
 * @author Tim
 */
public interface Building extends Ownable, Serializable {

    /**
     * 
     * @return gets building type
     */
    public BuildingType getBuildingType();

    /**
     * sets building type
     * 
     * @param type
     */
    public void setBuildingType(BuildingType type);

}