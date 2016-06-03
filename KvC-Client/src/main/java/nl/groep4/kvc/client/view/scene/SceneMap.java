package nl.groep4.kvc.client.view.scene;

import java.rmi.RemoteException;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import nl.groep4.kvc.client.util.SceneUtil;

public class SceneMap implements SceneHolder {

    public Pane overlayPane = null;
    private Pane layers;

    @Override
    public Scene getScene() throws RemoteException {
	if (layers != null)
	    /* Build layer for the design */
	    layers = new StackPane();
	// "game.button.buy", "game.button.trade", "game.button.build"

	/* Add all layers */
	layers.getChildren().addAll(SceneUtil.getBoardBackground(), SceneUtil.getBoard());
	Scene scene = new Scene(layers);
	return scene;
    }

    public void setOverlay(Pane pane) {
	if (overlayPane != null) {
	    layers.getChildren().remove(overlayPane);
	}
	if (pane != null) {
	    layers.getChildren().add(pane);
	    SceneUtil.fadeIn(pane);
	}
	overlayPane = pane;
    }

}
