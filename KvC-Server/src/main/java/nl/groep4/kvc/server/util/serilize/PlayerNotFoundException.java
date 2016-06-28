package nl.groep4.kvc.server.util.serilize;

import nl.groep4.kvc.common.enumeration.Color;

public class PlayerNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 8071996;

    public PlayerNotFoundException(Color color) {
	super(String.format("A player with the color '%s' is missing", color.toString()));
    }

}
