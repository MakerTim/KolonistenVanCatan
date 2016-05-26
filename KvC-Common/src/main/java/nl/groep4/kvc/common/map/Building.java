package nl.groep4.kvc.common.map;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.interfaces.Ownable;

public interface Building extends Ownable {

	public BuildingType getBuildingType();

	public void setBuildingType(BuildingType type);

}
