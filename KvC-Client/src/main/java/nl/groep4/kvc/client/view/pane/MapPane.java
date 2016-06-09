package nl.groep4.kvc.client.view.pane;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import nl.groep4.kvc.client.view.elements.ClientTile;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Map;

public class MapPane implements PaneHolder {

    List<ClientTile> tiles = new ArrayList<>();

    @Override
    public Pane getPane() {
	Pane ret = new HBox(-150);

	for (int col = 0; col < Map.COLUMS; col++) {
	    int rows = Map.COLUMS - Math.abs(col - ((Map.COLUMS - 1) / 2)) - 1;
	    // TODO rows adde
	    for (int row = 0; row < rows; row++) {
		Coordinate coord = new Coordinate(col - Map.COLUMS / 2, row - rows / 2);
		tiles.add(new ClientTile(coord));
	    }
	}

	return ret;
    }

    @Override
    public void updateTranslation() {
    }

    public void updateMap(Map map) {

    }

}
