package nl.groep4.kvc.common.map;

import com.sun.istack.internal.Nullable;

import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.Point;

public interface Tile {

	public String getPosition();

	@Nullable
	public Tile getRelativeTile(Direction direction);

	@Nullable
	public Street getStreet(Direction direction);

	@Nullable
	public Building getBuilding(Point point);

}
