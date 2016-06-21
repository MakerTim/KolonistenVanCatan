package nl.groep4.kvc.server.controller;

import java.rmi.RemoteException;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.GameState;
import nl.groep4.kvc.common.enumeration.Point;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.enumeration.SelectState;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateMap;
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

    // TODO: Rover verplaats modus.

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

    public void placeBuilding(Player newOwner, Coordinate coord, BuildingType type) {
	try {
	    if ((!newOwner.hasRemainingVillages() && type == BuildingType.VILLAGE)
		    || (!newOwner.hasRemainingCitys() && type == BuildingType.CITY)) {
		newOwner.getUpdateable().popup("nobuilding");
		return;
	    }
	    switch (type) {
	    case VILLAGE:
		if (newOwner.hasRemainingVillages()) {
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
			newOwner.removeRemainingVillages();
			building.setOwner(newOwner);
			building.setBuildingType(type);
			controller.updateModel();
			newOwner.setSelectable(SelectState.TILE);
			if (controller.getState() == GameState.INIT) {
			    controller.turnController.initTurnStreet(building);
			} else if (newOwner.hasRemainingVillages()) {
			    controller.highlightBuildings(newOwner, BuildingType.VILLAGE);
			} else {
			    newOwner.getUpdateable(UpdateMap.class).unblockActions();
			}
		    } else {
			newOwner.getUpdateable().popup("alreadbuilding");
		    }
		}
		break;
	    case CITY:
		if (newOwner.hasRemainingCitys()) {
		    Building building = controller.getMap().getBuilding(coord);
		    if (building != null && !building.getOwner().equals(newOwner)) {
			newOwner.getUpdateable().popup("noowner");
		    }
		    newOwner.removeRemainingCitys();
		    building.setOwner(newOwner);
		    building.setBuildingType(type);
		    controller.updateModel();
		    newOwner.setSelectable(SelectState.TILE);
		    if (controller.getState() == GameState.INIT) {
			controller.turnController.initTurnStreet(building);
		    } else if (newOwner.hasRemainingCitys()) {
			controller.highlightBuildings(newOwner, BuildingType.CITY);
		    } else {
			newOwner.getUpdateable(UpdateMap.class).unblockActions();
		    }
		}
		break;
	    case EMPTY:
		break;
	    }
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    public void placeStreet(Player newOwner, Coordinate coord) {
	try {
	    if (newOwner.hasRemainingStreets()) {
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
		    newOwner.removeRemainingStreet();
		    street.setOwner(newOwner);
		    controller.updateModel();
		    if (controller.getState() == GameState.INIT) {
			controller.nextTurn();
		    } else if (newOwner.hasRemainingStreets()) {
			controller.highlightStreet(newOwner);
		    } else {
			newOwner.getUpdateable(UpdateMap.class).unblockActions();
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
