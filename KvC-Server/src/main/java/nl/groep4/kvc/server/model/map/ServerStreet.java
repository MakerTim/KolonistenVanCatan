package nl.groep4.kvc.server.model.map;

import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Street;

/**
 * Instance of street
 * 
 * @author Tim
 * @version 1.0
 *
 */
public class ServerStreet implements Street {

    private static final long serialVersionUID = 7162302038488828886L;

    private Coordinate location;
    private Player owner;

    /**
     * Makes empty streets at the points where the streets can be placed
     * 
     * @param location
     */
    public ServerStreet(Coordinate location) {
	this.location = location;
    }

    @Override
    public Player getOwner() {
	return owner;
    }

    @Override
    public void setOwner(Player player) {
	owner = player;
    }

    @Override
    public Coordinate getPosition() {
	return location;
    }

    @Override
    public String toString() {
	return "Street @" + getPosition().toString();
    }

}
