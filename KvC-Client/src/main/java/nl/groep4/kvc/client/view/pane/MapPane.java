package nl.groep4.kvc.client.view.pane;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import nl.groep4.kvc.common.map.Map;

/**
 * 
 * @author Tim
 * @version 1.0
 */
public class MapPane implements PaneHolder {

    private Map map;

    public MapPane(Map map) {
	this.map = map;
    }

    @Override
    public Pane getPane() {
	VBox lines = new VBox();
	System.out.println(map);
	// TODO: mappane#pane
	return new StackPane(lines);
    }

    @Override
    public void updateTranslation() {
    }

}
