package nl.groep4.kvc.client.controller;

import java.rmi.RemoteException;

import nl.groep4.kvc.client.util.ExceptionManager;
import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.interfaces.KolonistenVanCatan;
import nl.groep4.kvc.common.map.Coordinate;

public class MapController implements Controller {

    private KolonistenVanCatan model;

    public MapController(KolonistenVanCatan model) {
	this.model = model;
    }

    public void placeBuilding(Coordinate coord) {
	try {
	    model.placeBuilding(coord, ClientRefrence.getThePlayer(), BuildingType.VILLAGE);
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }

    public void placeStreet(Coordinate coord) {
	try {
	    model.placeStreet(coord, ClientRefrence.getThePlayer());
	} catch (RemoteException ex) {
	    ExceptionManager.handleRemoteException(ex);
	}
    }
}
