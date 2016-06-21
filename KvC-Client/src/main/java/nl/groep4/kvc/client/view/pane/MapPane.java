package nl.groep4.kvc.client.view.pane;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import nl.groep4.kvc.client.controller.MapController;
import nl.groep4.kvc.client.view.elements.ClientTile;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.Point;
import nl.groep4.kvc.common.enumeration.SelectState;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.Tile;

public class MapPane implements PaneHolder {

    private List<ClientTile> tiles = new ArrayList<>();
    private Pane pane;
    private MapController controller;

    public MapPane() {
	pane = new StackPane();
	HBox colls = new HBox((-0.60) * SceneMap.scale);
	colls.setAlignment(Pos.CENTER);
	for (int col = 0; col < Map.COLUMS; col++) {
	    int rows = Map.COLUMS - Math.abs(col - ((Map.COLUMS - 1) / 2)) - 1;
	    VBox rowws = new VBox(-0.33 * SceneMap.scale);
	    rowws.setAlignment(Pos.CENTER);
	    for (int row = 0; row < rows; row++) {
		Coordinate coord = new Coordinate(col - Map.COLUMS / 2, row - rows / 2);
		ClientTile tile = new ClientTile(coord);
		rowws.getChildren().add(tile);
		tiles.add(tile);
	    }
	    colls.getChildren().add(rowws);
	}
	pane.getChildren().add(colls);
    }

    public void registerController(MapController controller) {
	this.controller = controller;
	tiles.forEach(tile -> tile.setController(controller));
    }

    @Override
    public Pane getPane() {
	return pane;
    }

    @Override
    public void updateTranslation() {
    }

    public ClientTile getTile(Coordinate coord) {
	return tiles.stream().filter(tile -> tile.getPosition().equals(coord)).findAny().orElse(null);
    }

    public void updateMap(Map map) {
	for (Tile tile : map.getTiles()) {
	    ClientTile clientTile = getTile(tile.getPosition());
	    clientTile.setTile(tile);
	}
    }

    public void highlightStreet(Collection<Street> streets) {
	for (ClientTile tile : tiles) {
	    for (Direction direction : Direction.values()) {
		tile.highLightStreet(direction, false);
		for (Street street : streets) {
		    if (street == null) {
			continue;
		    }
		    if (street.equals(tile.getTile().getStreet(direction))) {
			tile.highLightStreet(direction, true);
		    }
		}
	    }
	}
    }

    public void highlightBuilding(Collection<Building> buildings, BuildingType type) {
	for (ClientTile tile : tiles) {
	    for (Point point : Point.values()) {
		tile.highLightBuilding(point, BuildingType.EMPTY);
		for (Building building : buildings) {
		    if (building.equals(tile.getTile().getBuilding(point))) {
			tile.highLightBuilding(point, type);
		    }
		}
	    }
	}
    }

    public void setSelectable(SelectState selectables) throws RemoteException {
	tiles.forEach(clientTile -> clientTile.setSelectState(selectables));
    }
}
