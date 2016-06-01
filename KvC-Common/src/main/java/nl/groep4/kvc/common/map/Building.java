package nl.groep4.kvc.common.map;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.interfaces.Ownable;

/**
 * Defining building type
 * 
 * @version 1.0
 * @author Tim
 */
public interface Building extends Ownable {

    public BuildingType getBuildingType();

    /**
     * 
     * @param type
     * @version 1.0
     * @author Tim
     */
    public void setBuildingType(BuildingType type);
    /**
     * Sets buildingtype
     * 
     * @version 1.0
     * @author Tim
     */
}
/**
* 
*
*/