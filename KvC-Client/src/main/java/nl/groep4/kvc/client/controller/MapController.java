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
import nl.groep4.kvc.common.interfaces.Throw;
import nl.groep4.kvc.common.map.Coordinate;

/**
 * All functions that can be called while you are in the game.
 * 
 * @author Tim
 * @version 1.1
 */
public class MapController implements Controller {

    private KolonistenVanCatan model;
    private SceneMap view;

    /**
     * Gets the current model and view.
     * 
     * @param model
     *            The model which is used.
     * @param view
     *            The view which is used.
     */
    public MapController(KolonistenVanCatan model, SceneMap view) {
	this.model = model;
	this.view = view;
    }

    /**
     * Calls trade with unique trade ID.
     * 
     * @param tradeID
     *            Unique trade ID.
     */
    public void doTrade(UUID tradeID) {
	try {
	    model.trade(tradeID, ClientRefrence.getThePlayer());
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    /**
     * Use a selected card.
     * 
     * @param card
     *            The card that is selected.
     */
    public void useCard(Card card) {
	try {
	    model.useCard(card);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    /**
     * The player gets all the resource cards of one kind of choice from all the
     * players.
     * 
     * @param resource
     *            The resource of choice.
     */
    public void targetMonopoly(Resource resource) {
	try {
	    model.targetMonopoly(ClientRefrence.getThePlayer(), resource);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    /**
     * The player gets two of the same resource cards of choice from the bank.
     * 
     * @param resource
     *            The resource of choice.
     */
    public void targetInvention(Resource resource) {
	try {
	    model.targetInvention(ClientRefrence.getThePlayer(), resource);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    /**
     * Adds player, request and reward to trade list.
     * 
     * @param who
     *            The player.
     * @param request
     *            The resources the player wants to get.
     * @param reward
     *            The resources the player wants to give for it.
     */
    public void placeTrade(Player who, Map<Resource, Integer> request, Map<Resource, Integer> reward) {
	try {
	    model.addTrade(who, request, reward);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    /**
     * Places building at the given coordinate.
     * 
     * @param coord
     *            Coordinate to place building.
     */
    public void placeBuilding(Coordinate coord, BuildingType type) {
	try {
	    model.placeBuilding(ClientRefrence.getThePlayer(), coord, type);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    /**
     * Places street on given coordinate.
     * 
     * @param coord
     *            Coordinate to place street.
     */
    public void placeStreet(Coordinate coord) {
	try {
	    model.placeStreet(ClientRefrence.getThePlayer(), coord);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    /**
     * Opens BuyPane.
     */
    public void openBuyPane() {
	view.openBuildPane();
    }

    /**
     * Goes to the next turn.
     */
    public void nextTurn() {
	try {
	    model.nextTurn();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * Calls the trowDices class.
     */
    public void throwDice() {
	try {
	    model.throwDices();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * Sets the game on pause.
     */
    public void setPause() {
	try {
	    model.openPausePane(ClientRefrence.getThePlayer());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * Resumes the game.
     */
    public void unpause() {
	try {
	    model.closePausePane();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * Buys a street.
     */
    public void buyStreet() {
	try {
	    model.buyStreet();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * Buys a village.
     */
    public void buyVillage() {
	try {
	    model.buyVillage();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * Buys a city.
     */
    public void buyCity() {
	try {
	    model.buyCity();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * Buys a card.
     */
    public void buyCard() {
	try {
	    model.buyCard();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * Moves the bandit from a tile.
     * 
     * @param position
     *            The coordinate of the tile.
     */
    public void moveFromRover(Coordinate position) {
	try {
	    model.moveFromRover(ClientRefrence.getThePlayer(), position);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * Moves the bandit to a chosen tile.
     * 
     * @param position
     *            The coordinate of the chosen tile
     */
    public void moveToRover(Coordinate position) {
	try {
	    model.moveToRover(ClientRefrence.getThePlayer(), position);
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    /**
     * True or false on if the Bandit has moved.
     * 
     * @return True or false on if the Bandit has moved.
     */
    public boolean isMovingRover() {
	try {
	    return model.isMovingRover();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return false;
    }

    public Throw lastThrow() {
	try {
	    return model.getLastThrow();
	} catch (NullPointerException ex) {
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
	return null;
    }
}
