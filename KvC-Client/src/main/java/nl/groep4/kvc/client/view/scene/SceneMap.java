package nl.groep4.kvc.client.view.scene;

import java.rmi.RemoteException;
import java.util.EnumMap;
import java.util.List;

import javafx.geometry.Pos;
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
import nl.groep4.kvc.client.view.pane.StockPane;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.UpdateMap;
import nl.groep4.kvc.common.map.Map;

public class SceneMap implements SceneHolder, UpdateMap {

    public static /* final */ double scale = 100/* px */;

    private MapController controller;
    private PaneHolder overlayPane = null;
    private Pane theOverlayPane = null;
    private MapPane gamepane = new MapPane();
    private MenuButton nxtButton;
    private MenuButton optionButton;
    private MenuButton buildButton;
    private MenuButton tradeButton;
    private MenuButton buyButton;
    private Pane layers;
    private StockPane stock;

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

	    /* Build bottom */
	    BorderPane bottom = new BorderPane();

	    VBox optionPane = new VBox();
	    nxtButton = new MenuButton("game.button.next");
	    optionButton = new MenuButton("game.button.settings");
	    nxtButton.setFont(ViewMaster.FONT);
	    optionButton.setFont(ViewMaster.FONT);
	    optionPane.setAlignment(Pos.BOTTOM_RIGHT);
	    optionPane.getChildren().addAll(nxtButton, optionButton);

	    stock = new StockPane();

	    VBox buttons = new VBox();
	    buildButton = new MenuButton("game.button.build");
	    tradeButton = new MenuButton("game.button.trade");
	    buyButton = new MenuButton("game.button.buy");
	    buildButton.setFont(ViewMaster.FONT);
	    tradeButton.setFont(ViewMaster.FONT);
	    buyButton.setFont(ViewMaster.FONT);
	    buttons.setAlignment(Pos.BOTTOM_RIGHT);
	    buttons.getChildren().addAll(buildButton, tradeButton, buyButton);

	    bottom.setLeft(optionPane);
	    bottom.setCenter(stock.getPane());
	    bottom.setRight(buttons);
	    BorderPane.setAlignment(bottom, Pos.BOTTOM_CENTER);
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

    @Override
    public void updateStock(EnumMap<Resource, Integer> resources) {
	stock.updateStock(resources);
    }

    @Override
    public void updateStock(List<Card> cards) {
	stock.updateStock(cards);
    }
}
