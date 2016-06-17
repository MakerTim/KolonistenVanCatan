package nl.groep4.kvc.client.controller;

import java.rmi.RemoteException;

import nl.groep4.kvc.client.util.ExceptionManager;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.interfaces.KolonistenVanCatan;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.util.Scheduler;

public class MapController implements Controller {

    private KolonistenVanCatan model;
    private SceneMap view;

    public MapController(KolonistenVanCatan model, SceneMap view) {
	this.model = model;
	this.view = view;
    }

    public void placeBuilding(Coordinate coord) {
	try {
	    model.placeBuilding(coord, ClientRefrence.getThePlayer(), BuildingType.VILLAGE);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    public void placeStreet(Coordinate coord) {
	Scheduler.runAsync(() -> {
	    try {
		model.placeStreet(coord, ClientRefrence.getThePlayer());
	    } catch (RemoteException ex) {
		ExceptionManager.handleRemoteException(ex);
	    }
	});
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
	    // model.setPause();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }
}
