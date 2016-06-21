package nl.groep4.kvc.client.controller;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.UUID;

import nl.groep4.kvc.client.util.ExceptionManager;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.KolonistenVanCatan;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.map.Coordinate;

public class MapController implements Controller {

    private KolonistenVanCatan model;
    private SceneMap view;

    public MapController(KolonistenVanCatan model, SceneMap view) {
	this.model = model;
	this.view = view;
    }

    public void doTrade(UUID tradeID) {
	try {
	    model.trade(tradeID, ClientRefrence.getThePlayer());
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    public void targetMonopoly(Resource resource) {
	try {
	    model.targetMonopoly(resource);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    public void targetInvention(Resource resource) {
	try {
	    model.targetInvention(resource);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    public void placeTrade(Player who, Map<Resource, Integer> request, Map<Resource, Integer> reward) {
	try {
	    model.addTrade(who, request, reward);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    public void placeBuilding(Coordinate coord) {
	try {
	    model.placeBuilding(ClientRefrence.getThePlayer(), coord, BuildingType.VILLAGE);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    public void placeStreet(Coordinate coord) {
	try {
	    model.placeStreet(ClientRefrence.getThePlayer(), coord);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    public void openBuyPane() {
	view.openBuildPane();
    }

    public void nextTurn() {
	try {
	    model.nextTurn();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    public void throwDice() {
	try {
	    model.throwDices();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    public void setPause() {
	try {
	    model.openPausePane(ClientRefrence.getThePlayer());
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void unpause() {
	try {
	    model.closePausePane();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void buyStreet() {
	try {
	    model.buyStreet();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void buyVillage() {
	try {
	    model.buyVillage();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void buyCity() {
	try {
	    model.buyCity();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void buyCard() {
	try {
	    model.buyCard();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
}
