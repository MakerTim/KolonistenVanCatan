package nl.groep4.kvc.client.view.scene;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.view.pane.PaneHolder;

public class SceneMap implements SceneHolder {

    public PaneHolder overlayPane = null;
    private Pane layers;

    @Override
    public Scene getScene() {
	if (layers == null) {
	    /* Build layer for the design */
	    layers = new StackPane();
	    // "game.button.buy", "game.button.trade", "game.button.build"

	    /* Add all layers */
	    layers.getChildren().addAll(SceneUtil.getBoardBackground(), SceneUtil.getBoard());
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
    }
}
