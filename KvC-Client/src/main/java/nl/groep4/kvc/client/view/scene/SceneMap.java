package nl.groep4.kvc.client.view.scene;

import java.rmi.RemoteException;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import nl.groep4.kvc.client.controller.Controller;
import nl.groep4.kvc.client.controller.MapController;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.view.ExceptionDialog;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.pane.MapPane;
import nl.groep4.kvc.client.view.pane.PaneHolder;
import nl.groep4.kvc.common.interfaces.UpdateMap;
import nl.groep4.kvc.common.map.Map;

public class SceneMap implements SceneHolder, UpdateMap {

    public static /* final */ double scale = 100/* px */;

    private MapController controller;
    private PaneHolder overlayPane = null;
    private Pane theOverlayPane = null;
    private MapPane gamepane = new MapPane();
    private MenuButton buyButton;
    private Pane layers;

    @Override
    public void registerController(Controller controller) {
	this.controller = (MapController) controller;
	this.gamepane.registerController(this.controller);
    }

    @Override
    public Scene getScene() {
	if (layers == null) {
	    /* Build layer for the design */
	    layers = new StackPane();
	    BorderPane screen = new BorderPane();

	    BorderPane bottom = new BorderPane();

	    /* "game.button.buy", "game.button.trade", "game.button.build" */
	    Pane buttons = new VBox();
	    buyButton = new MenuButton("game.button.buy");
	    buttons.getChildren().addAll(buyButton);

	    bottom.setLeft(null);
	    bottom.setCenter(null);
	    bottom.setRight(buttons);
	    screen.setBottom(bottom);

	    /* Add all layers */
	    layers.getChildren().addAll(SceneUtil.getBoardBackground(), SceneUtil.getBoard(), gamepane.getPane(),
		    screen);
	}
	Scene scene = new Scene(layers);
	return scene;

    }

    public void setOverlay(PaneHolder pane) {
	if (theOverlayPane != null) {
	    layers.getChildren().remove(theOverlayPane);
	}
	if (pane != null) {
	    theOverlayPane = pane.getPane();
	    layers.getChildren().add(theOverlayPane);
	    SceneUtil.fadeIn(theOverlayPane);
	} else {
	    theOverlayPane = null;
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
    public void close(String reason) throws RemoteException {
	ExceptionDialog.warning("kicked." + reason);
	ViewMaster.setScene(new SceneLogin());
    }

    @Override
    public void popup(String key) throws RemoteException {
	ExceptionDialog.warning("gamenote." + key);
    }

    @Override
    public void closeOverlay() throws RemoteException {
	setOverlay(null);
    }
}
