package nl.groep4.kvc.client.view.scene;

import java.rmi.RemoteException;
import java.util.EnumMap;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import nl.groep4.kvc.client.controller.Controller;
import nl.groep4.kvc.client.controller.MapController;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.SoundUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ExceptionDialog;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.pane.BuildPane;
import nl.groep4.kvc.client.view.pane.CreditsPane;
import nl.groep4.kvc.client.view.pane.DicePane;
import nl.groep4.kvc.client.view.pane.MapPane;
import nl.groep4.kvc.client.view.pane.OptionPane;
import nl.groep4.kvc.client.view.pane.PaneHolder;
import nl.groep4.kvc.client.view.pane.PausePane;
import nl.groep4.kvc.client.view.pane.RulesPane;
import nl.groep4.kvc.client.view.pane.StockPane;
import nl.groep4.kvc.client.view.pane.TradePane;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.Trade;
import nl.groep4.kvc.common.interfaces.UpdateMap;
import nl.groep4.kvc.common.map.Map;

public class SceneMap implements SceneHolder, UpdateMap {

    public static /* final */ double scale = 100/* px */;

    private MapController controller;
    private PaneHolder overlayPane = null;
    private Pane theOverlayPane = null;
    private Rectangle theOverlayBackground = null;
    private MapPane gamepane = new MapPane();
    private MenuButton nxtButton;
    private MenuButton resourceButton;
    private MenuButton optionButton;
    private MenuButton buildButton;
    private MenuButton tradeButton;
    private MenuButton buyButton;
    private Pane layers;

    private StockPane stockPane;
    private BuildPane buildPane = new BuildPane(this);
    private TradePane tradePane = new TradePane(this);

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
	    stockPane = new StockPane();
	    nxtButton = new MenuButton(TranslationManager.translate("game.button.next"));
	    optionButton = new MenuButton(TranslationManager.translate("game.button.settings"));
	    nxtButton.setFont(ViewMaster.FONT);
	    optionButton.setFont(ViewMaster.FONT);
	    nxtButton.setOnMouseClicked(mouse -> onNxtTurnClick());
	    optionButton.setOnMouseClicked(mouse -> onOptionClick());
	    optionPane.setAlignment(Pos.BOTTOM_RIGHT);
	    optionPane.getChildren().addAll(nxtButton, optionButton);

	    VBox buttons = new VBox();
	    resourceButton = new MenuButton(TranslationManager.translate("map.stock.show"));
	    buildButton = new MenuButton(TranslationManager.translate("game.button.build"));
	    tradeButton = new MenuButton(TranslationManager.translate("game.button.trade"));
	    buyButton = new MenuButton(TranslationManager.translate("game.button.buy"));
	    resourceButton.setFont(ViewMaster.FONT);
	    buildButton.setFont(ViewMaster.FONT);
	    tradeButton.setFont(ViewMaster.FONT);
	    buyButton.setFont(ViewMaster.FONT);
	    resourceButton.setOnMouseClicked(mouse -> onToggleResourceClick());
	    buildButton.setOnMouseClicked(mouse -> onBuildClick());
	    tradeButton.setOnMouseClicked(mouse -> onTradeClick());
	    buyButton.setOnMouseClicked(mouse -> onBuyClick());

	    buttons.setAlignment(Pos.BOTTOM_RIGHT);
	    buttons.getChildren().addAll(resourceButton, buildButton, tradeButton, buyButton);

	    bottom.setLeft(optionPane);
	    bottom.setCenter(stockPane.getPane());
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

    private void onToggleResourceClick() {
	if (stockPane.isOpen()) {
	    resourceButton.updateText(TranslationManager.translate("map.stock.show"));
	    stockPane.closeStock();
	} else {
	    resourceButton.updateText(TranslationManager.translate("map.stock.hide"));
	    stockPane.openStock();
	}
    }

    private void onNxtTurnClick() {
	controller.nextTurn();
	SoundUtil.playNextTurn();
    }

    private void onOptionClick() {
	openOptionPane();
    }

    private void onTradeClick() {
	openTradePane();
    }

    private void onBuildClick() {
	openBuildPane();
    }

    private void onBuyClick() {
	openBuyPane();
    }

    @Override
    public void openTradePane() {
	setOverlay(tradePane);
    }

    @Override
    public void openRulesPane() {
	setOverlay(new RulesPane(this));
    }

    @Override
    public void openOptionPane() {
	setOverlay(new OptionPane(this));
    }

    @Override
    public void openDicePane() {
	setOverlay(new DicePane());
    }

    @Override
    public void openBuyPane() {
	// setOverlay(new BuyPane());
    }

    @Override
    public void openCreditsPane() {
	setOverlay(new CreditsPane(this));
    }

    @Override
    public void openPausePane() {
	setOverlay(new PausePane(this));
    }

    @Override
    public void openSavePane() {
	// setOverlay(new SavePane());
    }

    @Override
    public void openBuildPane() {
	setOverlay(buildPane);
    }

    @Override
    public void closeOverlay() {
	setOverlay(null);
    }

    public void setOverlay(PaneHolder pane) {
	if (theOverlayPane != null) {
	    layers.getChildren().remove(theOverlayBackground);
	    layers.getChildren().remove(theOverlayPane);
	}
	if (pane != null) {
	    theOverlayPane = pane.getPane();
	    theOverlayPane.setPickOnBounds(false);
	    for (Node node : theOverlayPane.getChildren()) {
		node.setPickOnBounds(false);
	    }
	    theOverlayBackground = new Rectangle(0, 0, ViewMaster.GAME_WIDHT, ViewMaster.GAME_HEIGHT);
	    theOverlayBackground.setFill(new Color(0.1, 0.1, 0.1, 0.5));
	    theOverlayBackground.setOnMouseClicked(click -> closeOverlay());
	    layers.getChildren().add(theOverlayBackground);
	    layers.getChildren().add(theOverlayPane);
	    SceneUtil.fadeIn(theOverlayPane);
	} else {
	    theOverlayPane = null;
	}
	overlayPane = pane;
    }

    @Override
    public void updateConfig() {
	if (stockPane.isOpen()) {
	    resourceButton.updateText(TranslationManager.translate("map.stock.show"));
	} else {
	    resourceButton.updateText(TranslationManager.translate("map.stock.hide"));
	}
	nxtButton.updateText(TranslationManager.translate("game.button.next"));
	optionButton.updateText(TranslationManager.translate("game.button.settings"));
	buildButton.updateText(TranslationManager.translate("game.button.build"));
	tradeButton.updateText(TranslationManager.translate("game.button.trade"));
	buyButton.updateText(TranslationManager.translate("game.button.buy"));
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

    @Override
    public void updateTrades(List<Trade> allTrades) throws RemoteException {
	tradePane.updateTrades(allTrades);
    }
}
