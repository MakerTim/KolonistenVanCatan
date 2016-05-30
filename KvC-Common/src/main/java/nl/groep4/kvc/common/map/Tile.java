package nl.groep4.kvc.common.map;

import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.Point;

public interface Tile {

    public String getPosition();

    public Tile getRelativeTile(Direction direction);

    public Street getStreet(Direction direction);

    public Building getBuilding(Point point);

}
