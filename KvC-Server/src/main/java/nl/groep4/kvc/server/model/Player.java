package nl.groep4.kvc.server.model;

import nl.groep4.kvc.common.enumeration.Color;

/**
 * Sets color and name for player
 * 
 * @version 1.0
 * @author Tim
 *
 */

public class Player implements nl.groep4.kvc.common.Player {

    private static final long serialVersionUID = 2304199625693960750L;
    private final String username;
    private Color color;

    public Player(String username) {
	this.username = username.substring(0, Math.min(username.length(), 20));
    }

    @Override
    public String getUsername() {
	return username;
    }

    @Override
    public Color getColor() {
	return color;
    }

    @Override
    public void setColor(Color color) {
	this.color = color;
    }
}