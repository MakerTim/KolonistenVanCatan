package nl.groep4.kvc.server.model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Updatable;

/**
 * Instance of player
 * 
 * @author Tim
 * @version 1.0
 */
public class ServerPlayer implements Player {

    private String username;
    private Updatable<?> updatable;
    private Color color;
    private List<Card> cards = new ArrayList<>();
    private EnumMap<Resource, Integer> resources;
    private int buildingsToBuild;
    private int streetsToBuild;

    /**
     * Makes a new player on the basis of the username. When the username
     * contains more than 20 characters, it will be cut to 2
     * 
     * @param username
     */
    public ServerPlayer(String username) {
	this.username = username.substring(0, Math.min(20, username.length()));
    }

    @Override
    public String getUsername() {
	return username;
    }

    @Override
    public void registerUpdateable(Updatable<?> updatable) {
	this.updatable = updatable;
    }

    @Override
    public Updatable<?> getUpdateable() {
	return updatable;
    }

    @Override
    public Color getColor() {
	return color;
    }

    @Override
    public void setColor(Color color) {
	this.color = color;
    }

    @Override
    public List<Card> getCards() {
	return cards;
    }

    @Override
    public EnumMap<Resource, Integer> getResources() {
	return resources;
    }

    @Override
    public int getRemainingStreets() {
	return streetsToBuild;
    }

    @Override
    public void addRemainingStreets(int streets) {
	streetsToBuild += streets;
    }

    @Override
    public int getRemainingBuidlings() {
	return buildingsToBuild;
    }

    @Override
    public void addRemainingBuidlings(int buildings) {
	buildingsToBuild += buildings;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj instanceof Player) {
	    Player other = (Player) obj;
	    try {
		return other.getUsername().equals(getUsername());
	    } catch (RemoteException ex) {
		ex.printStackTrace();
	    }
	}
	return super.equals(obj);
    }
}
