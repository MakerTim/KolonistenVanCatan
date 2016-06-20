package nl.groep4.kvc.server.controller;

import java.rmi.RemoteException;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.GameState;
import nl.groep4.kvc.common.enumeration.Point;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.Tile;
import nl.groep4.kvc.common.map.TileResource;

public class ServerMapController {

    private ServerKolonistenVanCatan controller;

    public ServerMapController(ServerKolonistenVanCatan serverKolonistenVanCatan) {
	this.controller = serverKolonistenVanCatan;
    }

    public void distribute() {
	try {
	    System.out.printf("Giving players resources for tiles with number '%d'\n",
		    controller.getLastThrow().getValue());
	    for (Tile tile : controller.getMap().getTiles()) {
		if (tile instanceof TileResource) {
		    TileResource tileResource = (TileResource) tile;
		    if (!tileResource.hasRover() && tileResource.getNumber() == controller.getLastThrow().getValue()) {
			Resource resource = tileResource.getResource();
			for (Building building : tile.getBuildings()) {
			    if (building != null && building.getOwner() != null) {
				Player pl = building.getOwner();
				switch (building.getBuildingType()) {
				case CITY:
				    pl.giveResource(resource);
				case VILLAGE:
				    pl.giveResource(resource);
				case EMPTY:
				    break;
				}
			    }
			}
		    }
		}
	    }
	    controller.updateResources();
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void placeBuilding(Coordinate coord, BuildingType type) {
	Player newOwner = controller.getTurn();
	try {
	    if (newOwner.getRemainingVillages() > 0) {
		Building building = controller.getMap().getBuilding(coord);
		boolean validAction = true;
		for (Tile tile : building.getConnectedTiles()) {
		    for (Point point : Point.values()) {
			if (building.equals(tile.getBuilding(point))
				&& !tile.isValidPlace(controller.getMap(), point)) {
			    validAction = false;
			    break;
			}
		    }
		}
		if (validAction) {
		    building.setOwner(newOwner);
		    building.setBuildingType(type);
		    controller.updateModel();
		    if (controller.getState() == GameState.INIT) {
			controller.turnController.initTurnStreet(building);
		    }
		} else {
		    newOwner.getUpdateable().popup("alreadbuilding");
		}
	    } else {
		newOwner.getUpdateable().popup("nobuilding");
	    }
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    public void placeStreet(Coordinate coord) {
	Player newOwner = controller.getTurn();
	try {
	    if (newOwner.getRemainingStreets() > 0) {
		Street street = controller.getMap().getStreet(coord);
		boolean validAction = true;
		for (Tile tile : street.getConnectedTiles()) {
		    for (Direction direction : Direction.values()) {
			if (street.equals(tile.getStreet(direction))) {
			    if (!tile.isValidPlace(controller.getMap(), direction)) {
				validAction = false;
				break;
			    }
			}
		    }
		}
		if (validAction) {
		    street.setOwner(newOwner);
		    controller.updateModel();
		    if (controller.getState() == GameState.INIT) {
			controller.nextTurn();
		    }
		} else {
		    newOwner.getUpdateable().popup("alreadstreet");
		}
	    } else {
		newOwner.getUpdateable().popup("nostreet");
	    }
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }
}
