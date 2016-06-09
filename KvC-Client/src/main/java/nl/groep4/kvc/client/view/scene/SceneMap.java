package nl.groep4.kvc.client.view.scene;

import java.rmi.RemoteException;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.view.pane.MapPane;
import nl.groep4.kvc.client.view.pane.PaneHolder;
import nl.groep4.kvc.common.interfaces.UpdateMap;
import nl.groep4.kvc.common.map.Map;

public class SceneMap implements SceneHolder, UpdateMap {

    public static /* final */ double scale = 100/* px */;

    private PaneHolder overlayPane = null;
    private MapPane gamepane = new MapPane();
    private Pane layers;

    @Override
    public Scene getScene() {
	if (layers == null) {
	    /* Build layer for the design */
	    layers = new StackPane();
	    // "game.button.buy", "game.button.trade", "game.button.build"
	    Pane board = gamepane.getPane();

	    /* Add all layers */
	    layers.getChildren().addAll(SceneUtil.getBoardBackground(), SceneUtil.getBoard(), board);
	}
	Scene scene = new Scene(layers);
	return scene;

    }

    public void setOverlay(PaneHolder pane) {
	if (overlayPane != null) {
	    layers.getChildren().remove(overlayPane);
	}
	if (pane != null) {
	    Pane thePane = pane.getPane();
	    layers.getChildren().add(thePane);
	    SceneUtil.fadeIn(thePane);
	}
	overlayPane = pane;
    }

    @Override
    public void updateConfig() {
	if (overlayPane != null) {
	    overlayPane.updateTranslation();
	}
	gamepane.updateTranslation();
    }

    @Override
    public void setModel(Map model) throws RemoteException {
	gamepane.updateMap(model);
    }

    @Override
    public void close(String key) throws RemoteException {
	// TODO SceneMap#close
    }

    @Override
    public void popup(String key) throws RemoteException {
	// TODO SceneMap#close
    }
}
