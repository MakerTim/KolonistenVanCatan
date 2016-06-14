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
import nl.groep4.kvc.client.util.SoundUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ExceptionDialog;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.pane.BuildPane;
import nl.groep4.kvc.client.view.pane.DicePane;
import nl.groep4.kvc.client.view.pane.MapPane;
import nl.groep4.kvc.client.view.pane.OptionPane;
import nl.groep4.kvc.client.view.pane.PaneHolder;
import nl.groep4.kvc.client.view.pane.RulesPane;
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
    private Pane theOverlayBackground = null;
    private MapPane gamepane = new MapPane();
    private MenuButton nxtButton;
    private MenuButton optionButton;
    private MenuButton buildButton;
    private MenuButton tradeButton;
    private MenuButton buyButton;
    private Pane layers;

    private StockPane stockPane;
    private BuildPane buildPane = new BuildPane();

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
	    nxtButton = new MenuButton(TranslationManager.translate("game.button.next"));
	    optionButton = new MenuButton(TranslationManager.translate("game.button.settings"));
	    nxtButton.setFont(ViewMaster.FONT);
	    optionButton.setFont(ViewMaster.FONT);
	    nxtButton.setOnMouseClicked(mouse -> onNxtTurnClick());
	    optionButton.setOnMouseClicked(mouse -> onOptionClick());
	    optionPane.setAlignment(Pos.BOTTOM_RIGHT);
	    optionPane.getChildren().addAll(nxtButton, optionButton);

	    VBox buttons = new VBox();
	    buildButton = new MenuButton(TranslationManager.translate("game.button.build"));
	    tradeButton = new MenuButton(TranslationManager.translate("game.button.trade"));
	    buyButton = new MenuButton(TranslationManager.translate("game.button.buy"));
	    buildButton.setFont(ViewMaster.FONT);
	    tradeButton.setFont(ViewMaster.FONT);
	    buyButton.setFont(ViewMaster.FONT);
	    buildButton.setOnMouseClicked(mouse -> onBuildClick());
	    tradeButton.setOnMouseClicked(mouse -> onTradeClick());
	    buyButton.setOnMouseClicked(mouse -> onBuyClick());

	    buttons.setAlignment(Pos.BOTTOM_RIGHT);
	    buttons.getChildren().addAll(buildButton, tradeButton, buyButton);

	    bottom.setLeft(optionPane);
	    bottom.setCenter((stockPane = new StockPane()).getPane());
	    bottom.setRight(buttons);
	    BorderPane.setAlignment(bottom, Pos.BOTTOM_CENTER);
	    screen.setBottom(bottom);
	    screen.setPickOnBounds(false);
	    bottom.setPickOnBounds(false);

	    /* Add all layers */
	    layers.getChildren().addAll(SceneUtil.getBoardBackground(), SceneUtil.getBoard(), gamepane.getPane(),
		    screen);
	}
	Scene scene = new Scene(layers);
	return scene;
    }

    private void onNxtTurnClick() {
	controller.nextTurn();
	SoundUtil.playNextTurn();
    }

    private void onOptionClick() {
	try {
	    openOptionPane();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    private void onTradeClick() {
	try {
	    openTradePane();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    private void onBuildClick() {
	try {
	    openBuildPane();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    private void onBuyClick() {
	try {
	    openBuyPane();
	} catch (RemoteException ex) {
	    ex.printStackTrace();
	}
    }

    @Override
    public void openTradePane() throws RemoteException {
	// setOverlay(new TradePane());
    }

    @Override
    public void openRulesPane() throws RemoteException {
	setOverlay(new RulesPane(this));
    }

    @Override
    public void openOptionPane() throws RemoteException {
	setOverlay(new OptionPane(this));
    }

    @Override
    public void openDicePane() throws RemoteException {
	setOverlay(new DicePane());
    }

    @Override
    public void openBuyPane() throws RemoteException {
	// setOverlay(new Buypane());
    }

    @Override
    public void openBuildPane() throws RemoteException {
	setOverlay(buildPane);
    }

    @Override
    public void closeOverlay() throws RemoteException {
	setOverlay(null);
    }

    public void setOverlay(PaneHolder pane) {
	if (theOverlayPane != null) {
	    layers.getChildren().remove(theOverlayBackground);
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
    public void updateStock(EnumMap<Resource, Integer> resources) {
	stockPane.updateStock(resources);
    }

    @Override
    public void updateStock(List<Card> cards) {
	stockPane.updateStock(cards);
    }

    @Override
    public void updateCityCosts(EnumMap<Resource, Integer> resources) throws RemoteException {
	buildPane.updateCityCosts(resources);
    }

    @Override
    public void updateStreetCosts(EnumMap<Resource, Integer> resources) throws RemoteException {
	buildPane.updateStreetCosts(resources);
    }

    @Override
    public void updateVillageCosts(EnumMap<Resource, Integer> resources) throws RemoteException {
	buildPane.updateVillageCosts(resources);
    }
}
