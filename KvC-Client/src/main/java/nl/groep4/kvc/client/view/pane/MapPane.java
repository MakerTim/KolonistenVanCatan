package nl.groep4.kvc.client.view.pane;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import nl.groep4.kvc.client.view.elements.ClientTile;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Tile;

public class MapPane implements PaneHolder {

    private List<ClientTile> tiles = new ArrayList<>();

    @Override
    public Pane getPane() {
	Pane stacked = new StackPane();
	HBox colls = new HBox((-0.40) * SceneMap.scale);
	colls.setAlignment(Pos.CENTER);
	for (int col = 0; col < Map.COLUMS; col++) {
	    int rows = Map.COLUMS - Math.abs(col - ((Map.COLUMS - 1) / 2)) - 1;
	    VBox rowws = new VBox(-0.15 * SceneMap.scale);
	    rowws.setAlignment(Pos.CENTER);
	    for (int row = 0; row < rows; row++) {
		Coordinate coord = new Coordinate(col - Map.COLUMS / 2, row - rows / 2);
		ClientTile tile = new ClientTile(coord);
		rowws.getChildren().add(tile);
		tiles.add(tile);
	    }
	    colls.getChildren().add(rowws);
	}
	stacked.getChildren().add(colls);
	return stacked;
    }

    @Override
    public void updateTranslation() {
    }

    public ClientTile getTile(Coordinate coord) {
	return tiles.stream().filter(tile -> tile.getPosition().equals(coord)).findAny().orElse(null);
    }

    public void updateMap(Map map) {
	for (Tile tile : map.getTiles()) {
	    getTile(tile.getPosition()).setTile(tile);
	}
    }

}
