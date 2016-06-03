package nl.groep4.kvc.client.view.pane;

import java.rmi.RemoteException;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import nl.groep4.kvc.common.map.Map;

public class MapPane implements PaneHolder {

    private Map map;

    public MapPane(Map map) {
	this.map = map;
    }

    @Override
    public Pane getPane() throws RemoteException {
	VBox lines = new VBox();
	System.out.println(map);
	return new StackPane(lines);
    }

}
