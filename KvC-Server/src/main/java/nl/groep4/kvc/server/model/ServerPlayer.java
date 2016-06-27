package nl.groep4.kvc.server.model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nl.groep4.kvc.common.enumeration.Color;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Updatable;

/**
 * Instance of player.
 * 
 * @author Tim
 * @version 1.1
 */
public class ServerPlayer implements Player {

    private String username;
    private Updatable<?> updatable;
    private Color color;
    private List<Card> cards = new ArrayList<>();
    private EnumMap<Resource, Integer> resources;;
    private int villagesToBuild;
    private int citysToBuild;
    private int streetsToBuild;
    private int score;
    private boolean hasLongestRoad;
    private boolean hasMostRidders;

    /**
     * Makes a new player on the basis of the username. When the username
     * contains more than 20 characters, it will be cut to 2.
     * 
     * @param username
     *            Name of the player.
     */
    public ServerPlayer(String username) {
	this.username = username.substring(0, Math.min(20, username.length()));
	Map<Resource, Integer> resources = new HashMap<>();
	for (Resource resource : Resource.values()) {
	    resources.put(resource, 0);
	}
	this.resources = new EnumMap<>(resources);
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
    public void addCard(Card drawnCard) throws RemoteException {
	cards.add(drawnCard);
    }

    @Override
    public boolean useCard(Card usedCard) throws RemoteException {
	boolean hasCard = false;
	Iterator<Card> cardIT = cards.iterator();
	while (cardIT.hasNext()) {
	    Card card = cardIT.next();
	    if (card.getType() == usedCard.getType()) {
		cardIT.remove();
		hasCard = true;
		break;
	    }
	}
	return hasCard;
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
    public int getRemainingVillages() {
	return villagesToBuild;
    }

    @Override
    public void addRemainingVillages(int villages) {
	villagesToBuild += villages;
    }

    @Override
    public int getRemainingCitys() throws RemoteException {
	return citysToBuild;
    }

    @Override
    public void addRemainingCitys(int citys) throws RemoteException {
	citysToBuild += citys;
    }

    @Override
    public int getPoints() throws RemoteException {
	return score;
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

    @Override
    public void setPoints(int score) throws RemoteException {
	this.score = score;
    }

    @Override
    public boolean hasMostKnights() throws RemoteException {
	return hasMostRidders;
    }

    @Override
    public void setMostRidder(boolean hasMost) throws RemoteException {
	this.hasMostRidders = hasMost;
    }

    @Override
    public boolean hasLongestRoad() throws RemoteException {
	return hasLongestRoad;
    }

    @Override
    public void setHasLongestRoad(boolean hasLongest) throws RemoteException {
	this.hasLongestRoad = hasLongest;
    }
}
