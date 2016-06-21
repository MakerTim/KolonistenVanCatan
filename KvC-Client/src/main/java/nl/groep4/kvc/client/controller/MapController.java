package nl.groep4.kvc.client.controller;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.UUID;

import nl.groep4.kvc.client.util.ExceptionManager;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.KolonistenVanCatan;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.map.Coordinate;

/**
 * All functions that can be called while you are in the game
 * 
 * @author Tim
 * @version 1.0
 */
public class MapController implements Controller {

    private KolonistenVanCatan model;
    private SceneMap view;

    /**
     * Gets the current model and view
     * 
     * @param model
     *            the model which is used
     * @param view
     *            the view which is used
     */
    public MapController(KolonistenVanCatan model, SceneMap view) {
	this.model = model;
	this.view = view;
    }

    /**
     * Calls trade with unique trade id
     * 
     * @param tradeID
     *            unique trade id
     */
    public void doTrade(UUID tradeID) {
	try {
	    model.trade(tradeID, ClientRefrence.getThePlayer());
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    public void useCard(Card card) {
	try {
	    model.useCard(card);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    public void targetMonopoly(Resource resource) {
	try {
	    model.targetMonopoly(ClientRefrence.getThePlayer(), resource);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    public void targetInvention(Resource resource) {
	try {
	    model.targetInvention(ClientRefrence.getThePlayer(), resource);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    /**
     * Adds player, request and reward to trade list
     * 
     * @param who
     *            the player
     * @param request
     *            the resources the player wants to get
     * @param reward
     *            the resources the player wants to give for it
     */
    public void placeTrade(Player who, Map<Resource, Integer> request, Map<Resource, Integer> reward) {
	try {
	    model.addTrade(who, request, reward);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    /**
     * Places building at the given coordinate
     * 
     * @param coord
     *            coordinate to place building
     */
    public void placeBuilding(Coordinate coord) {
	try {
	    model.placeBuilding(ClientRefrence.getThePlayer(), coord, BuildingType.VILLAGE);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    /**
     * Places street on given coordinate
     * 
     * @param coord
     *            coordinate to place street
     */
    public void placeStreet(Coordinate coord) {
	try {
	    model.placeStreet(ClientRefrence.getThePlayer(), coord);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    /**
     * Opens BuyPane
     */
    public void openBuyPane() {
	view.openBuildPane();
    }

    /**
     * Goes to the next turn
     */
    public void nextTurn() {
	try {
	    model.nextTurn();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * Calls the trowDices() class
     */
    public void throwDice() {
	try {
	    model.throwDices();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * Sets the game on pause
     */
    public void setPause() {
	try {
	    model.openPausePane(ClientRefrence.getThePlayer());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * Resumes the game
     */
    public void unpause() {
	try {
	    model.closePausePane();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * Buys a street
     */
    public void buyStreet() {
	try {
	    model.buyStreet();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * Buys village
     */
    public void buyVillage() {
	try {
	    model.buyVillage();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * Buys city
     */
    public void buyCity() {
	try {
	    model.buyCity();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * Buys card
     */
    public void buyCard() {
	try {
	    model.buyCard();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
}
