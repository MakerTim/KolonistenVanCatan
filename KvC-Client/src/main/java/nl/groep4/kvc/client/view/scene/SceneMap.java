package nl.groep4.kvc.client.view.scene;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.pane.DicePane;
import nl.groep4.kvc.client.view.pane.PaneHolder;

public class SceneMap implements SceneHolder {

    public PaneHolder overlayPane = null;
    private Pane layers;
    DicePane dicePane;

    @Override
    public Scene getScene() {
	dicePane = new DicePane();
	// allemaal dingen die we kunnen verwijderen
	MenuButton turn = new MenuButton(0, 0, "Start Turn");
	turn.setFont(ViewMaster.FONT);
	turn.registerClick(() -> layers.getChildren().addAll(dicePane.getPane()));
	// tot hier

	if (layers == null) {
	    /* Build layer for the design */
	    layers = new StackPane();
	    // "game.button.buy", "game.button.trade", "game.button.build"

	    /* Add all layers */
	    layers.getChildren().addAll(SceneUtil.getBoardBackground(), SceneUtil.getBoard(), turn);
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
