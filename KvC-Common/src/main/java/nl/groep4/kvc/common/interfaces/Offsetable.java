package nl.groep4.kvc.common.interfaces;

import nl.groep4.kvc.common.map.Coordinate;

public interface Offsetable {

    public Coordinate addTo(Coordinate original);

    public Coordinate offset(Coordinate original);

    public Coordinate offset(boolean isEvenRow);

    public Offsetable[] getConnected();

}
