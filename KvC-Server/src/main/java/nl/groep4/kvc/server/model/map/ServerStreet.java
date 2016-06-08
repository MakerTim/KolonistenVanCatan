package nl.groep4.kvc.server.model.map;

import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Street;

public class ServerStreet implements Street {

    private static final long serialVersionUID = 7162302038488828886L;

    private Coordinate location;
    private Player owner;

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

}
