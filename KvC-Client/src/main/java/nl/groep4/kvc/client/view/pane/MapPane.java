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

/**
 * Generates the map by setting coordinates for the tiles.
 * 
 * @author Tim
 * @version 1.1
 */
public class MapPane implements PaneHolder {

    private List<ClientTile> tiles = new ArrayList<>();
    private Pane pane;

    /**
     * Sets coordinates for tiles.
     * 
     */
    public MapPane() {
	pane = new StackPane();
	HBox colls = new HBox((-0.60) * SceneMap.scale);
	colls.setAlignment(Pos.CENTER);
	colls.setPickOnBounds(false);
	for (int col = 0; col < Map.COLUMS; col++) {
	    int rows = Map.COLUMS - Math.abs(col - ((Map.COLUMS - 1) / 2)) - 1;
	    VBox rowws = new VBox(-0.33 * SceneMap.scale);
	    rowws.setPickOnBounds(false);
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

    /**
     * Sets controller for each tile.
     * 
     * @param controller
     *            Sets per tile.
     */
    public void registerController(MapController controller) {
	tiles.forEach(tile -> tile.setController(controller));
    }

    @Override
    public Pane getPane() {
	return pane;
    }

    @Override
    public void updateTranslation() {
    }

    /**
     * Gets coordinate of tile.
     * 
     * @param coord
     *            Coordinate of tile.
     * @return Position and location of tile.
     */
    public ClientTile getTile(Coordinate coord) {
	return tiles.stream().filter(tile -> tile.getPosition().equals(coord)).findAny().orElse(null);
    }

    /**
     * Updates the map.
     * 
     * @param map
     *            All the tiles which map contains.
     */
    public void updateMap(Map map) {
	for (Tile tile : map.getTiles()) {
	    ClientTile clientTile = getTile(tile.getPosition());
	    clientTile.setTile(tile);
	}
    }

    /**
     * Highlights the streets.
     * 
     * @param streets
     *            Settings of streets.
     */
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

    /**
     * Highlights buildings.
     * 
     * @param buildings
     *            To be highlight.
     * @param type
     *            Building type.
     */
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

    /**
     * State to select.
     * 
     * @param selectables
     *            State of tile.
     * @throws RemoteException
     *             Any remotely invoked method.
     */
    public void setSelectable(SelectState selectables) throws RemoteException {
	tiles.forEach(clientTile -> clientTile.setSelectState(selectables));
    }
}
