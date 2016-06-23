package nl.groep4.kvc.server.controller;

import java.rmi.RemoteException;

import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.GameState;
import nl.groep4.kvc.common.enumeration.Point;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.enumeration.SelectState;
import nl.groep4.kvc.common.enumeration.TurnState;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateMap;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.Tile;
import nl.groep4.kvc.common.map.TileLand;
import nl.groep4.kvc.common.map.TileResource;

public class ServerMapController {

    private ServerKolonistenVanCatan controller;
    private Coordinate roverFrom;

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
	    controller.updateState(TurnState.WAITING);
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
			newOwner.removeRemainingVillage();
			building.setOwner(newOwner);
			building.setBuildingType(type);
			controller.updateMap();
			newOwner.setSelectable(SelectState.TILE);
			controller.updateState(TurnState.WAITING);
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
		    newOwner.removeRemainingCity();
		    building.setOwner(newOwner);
		    building.setBuildingType(type);
		    controller.updateMap();
		    newOwner.setSelectable(SelectState.TILE);
		    controller.updateState(TurnState.WAITING);
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
		    controller.updateMap();
		    newOwner.setSelectable(SelectState.TILE);
		    controller.updateState(TurnState.WAITING);
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

    public void moveRoverTo(Coordinate position) {
	try {
	    if (roverFrom != null) {
		Tile tile = controller.getMap().getTile(position);
		if (tile instanceof TileLand) {
		    TileLand land = (TileLand) tile;
		    if (land.hasRover()) {
			controller.getTurn().getUpdateable().popup("alreadyrover");
			return;
		    }
		    land.placeRover();
		    roverFrom = null;
		    controller.getTurn().getUpdateable(UpdateMap.class).unblockActions();
		    controller.getTurn().setSelectable(SelectState.TILE);
		}
	    }
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
	controller.updateMap();
    }

    public void moveRoverFrom(Coordinate position) {
	try {
	    Tile tile = controller.getMap().getTile(position);
	    if (tile instanceof TileLand) {
		TileLand land = (TileLand) tile;
		if (land.hasRover() && roverFrom.isSameLocation(Short.MAX_VALUE, Short.MAX_VALUE)) {
		    roverFrom = position;
		    controller.getTurn().setSelectable(SelectState.TILE);
		    controller.getTurn().getUpdateable(UpdateMap.class).blockActions();
		    land.removeRover();
		} else {
		    controller.getTurn().getUpdateable().popup("norover");
		}
	    }
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
	controller.updateMap();
    }

    public boolean isMovingRover() {
	return roverFrom != null && !roverFrom.isSameLocation(Short.MAX_VALUE, Short.MAX_VALUE);
    }

    public void moveBanditModus() {
	try {
	    System.out.println("Bandit is on the move! '7'");
	    controller.getTurn().setSelectable(SelectState.BANDIT);
	    controller.getTurn().getUpdateable(UpdateMap.class).blockActions();
	    roverFrom = new Coordinate(Short.MAX_VALUE, Short.MAX_VALUE);
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }
}
