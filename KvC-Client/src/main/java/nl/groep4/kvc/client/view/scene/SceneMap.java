package nl.groep4.kvc.client.view.scene;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
import nl.groep4.kvc.client.view.pane.BuyPane;
import nl.groep4.kvc.client.view.pane.CreditsPane;
import nl.groep4.kvc.client.view.pane.DicePane;
import nl.groep4.kvc.client.view.pane.InventionPane;
import nl.groep4.kvc.client.view.pane.MapPane;
import nl.groep4.kvc.client.view.pane.MonopolyPane;
import nl.groep4.kvc.client.view.pane.OptionPane;
import nl.groep4.kvc.client.view.pane.PaneHolder;
import nl.groep4.kvc.client.view.pane.PausePane;
import nl.groep4.kvc.client.view.pane.PlaceTradePane;
import nl.groep4.kvc.client.view.pane.RulesPane;
import nl.groep4.kvc.client.view.pane.ScorePane;
import nl.groep4.kvc.client.view.pane.ScoreRoundPane;
import nl.groep4.kvc.client.view.pane.StockPane;
import nl.groep4.kvc.client.view.pane.TradePane;
import nl.groep4.kvc.client.view.pane.TurnInfoPane;
import nl.groep4.kvc.client.view.pane.WinPane;
import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.enumeration.SelectState;
import nl.groep4.kvc.common.enumeration.TurnState;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.NotCloseable;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Trade;
import nl.groep4.kvc.common.interfaces.UpdateDice;
import nl.groep4.kvc.common.interfaces.UpdateMap;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Map;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.util.Scheduler;

/**
 * Calls all panes from map.
 * 
 * @author Tim
 * @version 1.1
 */
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
    private ScoreRoundPane scorePane;
    private TurnInfoPane infoPane;
    private BuildPane buildPane;
    private TradePane tradePane;
    private BuyPane buyPane;
    private ScorePane playerPane;

    @Override
    public void registerController(Controller controller) {
	this.controller = (MapController) controller;
	this.gamepane.registerController(this.controller);
    }

    @Override
    public Scene getScene() {
	SoundUtil.stopThemesong();
	SoundUtil.playGamesong();
	if (layers == null) {
	    /* Build layer for the design */
	    layers = new StackPane();
	    BorderPane screenTop = new BorderPane();
	    BorderPane screenBottom = new BorderPane();
	    screenTop.setPickOnBounds(false);
	    screenBottom.setPickOnBounds(false);

	    stockPane = new StockPane(this);
	    scorePane = new ScoreRoundPane();
	    infoPane = new TurnInfoPane(this.getController());
	    buildPane = new BuildPane(this);
	    tradePane = new TradePane(this);
	    buyPane = new BuyPane(this);
	    playerPane = new ScorePane();

	    /* Build top */
	    BorderPane top = new BorderPane();
	    top.setPickOnBounds(false);
	    HBox topLeftCorner = new HBox();
	    topLeftCorner.getChildren().addAll(scorePane.getPane(), infoPane.getPane());
	    top.setLeft(topLeftCorner);
	    top.setRight(playerPane.getPane());
	    screenTop.setTop(top);

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
	    bottom.setPickOnBounds(false);
	    BorderPane.setAlignment(bottom, Pos.BOTTOM_CENTER);
	    screenBottom.setBottom(bottom);

	    /* Add all layers */
	    layers.getChildren().addAll(SceneUtil.getBoardBackground(), SceneUtil.getBoard(), gamepane.getPane(),
		    screenBottom, screenTop);
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

    /**
     * Gets controller.
     * 
     * @return Current controller.
     */
    public MapController getController() {
	return this.controller;
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
    public void openEnd(Player pl) {
	setOverlay(new WinPane(this, pl));
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
    public void openDicePane(boolean isOwnTurn) {
	setOverlay(new DicePane(this, isOwnTurn));
    }

    @Override
    public void openBuyPane() {
	setOverlay(buyPane);
    }

    /**
     * Opens placeTradePane.
     */
    public void openPlaceTradePane() {
	setOverlay(new PlaceTradePane(this));
    }

    @Override
    public void openCreditsPane() {
	setOverlay(new CreditsPane(this));
    }

    @Override
    public void openPausePane(boolean isTurn) {
	setOverlay(new PausePane(this, isTurn));
    }

    @Override
    public void openSavePane() {
	setOverlay(new WinPane(this, null));
    }

    @Override
    public void openInventionPane() throws RemoteException {
	setOverlay(new InventionPane(this));
    }

    @Override
    public void openMonopolyPane() throws RemoteException {
	setOverlay(new MonopolyPane(this));
    }

    @Override
    public void openBuildPane() {
	setOverlay(buildPane);
    }

    @Override
    public void closeOverlay() {
	setOverlay(null);
    }

    /**
     * Overlays other panes.
     * 
     * @param pane
     *            To overlay.
     */
    public void setOverlay(PaneHolder pane) {
	Scheduler.runSync(() -> {
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
		if (!(pane instanceof NotCloseable)) {
		    theOverlayBackground.setOnMouseClicked(click -> closeOverlay());
		}
		layers.getChildren().add(theOverlayBackground);
		layers.getChildren().add(theOverlayPane);
		SceneUtil.fadeIn(theOverlayPane);
	    } else {
		theOverlayPane = null;
	    }
	    overlayPane = pane;
	});
    }

    /**
     * Gets the layers of the scene
     * 
     * @return the pane where everything is stored in
     */
    public Pane getLayers() {
	return layers;
    }

    @Override
    public void updateConfig() {
	Platform.runLater(() -> {
	    if (stockPane.isOpen()) {
		resourceButton.updateText(TranslationManager.translate("map.stock.hide"));
	    } else {
		resourceButton.updateText(TranslationManager.translate("map.stock.show"));
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
	    stockPane.updateTranslation();
	    scorePane.updateTranslation();
	    infoPane.updateTranslation();
	    buildPane.updateTranslation();
	    tradePane.updateTranslation();
	    buyPane.updateTranslation();
	});
    }

    @Override
    public void setModel(Map model) throws RemoteException {
	Platform.runLater(() -> {
	    gamepane.updateMap(model);
	});
    }

    @Override
    public void close(String reason) throws RemoteException {
	Platform.runLater(() -> {
	    ExceptionDialog.warning("kicked." + reason);
	    ViewMaster.setScene(new SceneLogin());
	});
    }

    @Override
    public void popup(String key) throws RemoteException {
	Platform.runLater(() -> ExceptionDialog.warning("gamenote." + key));
    }

    @Override
    public void updateStock(Player pl, EnumMap<Resource, Integer> resources) {
	Platform.runLater(() -> {
	    stockPane.updateStock(pl, resources);
	    playerPane.updateStock(pl, resources);
	});
    }

    @Override
    public void updateStock(Player pl, List<Card> cards) {
	Platform.runLater(() -> {
	    stockPane.updateStock(pl, cards);
	    playerPane.updateStock(pl, cards);
	});
    }

    @Override
    public void updateCityCosts(EnumMap<Resource, Integer> resources) {
	Platform.runLater(() -> {
	    try {
		buildPane.updateCityCosts(resources);
		buyPane.updateCityCosts(resources);
	    } catch (RemoteException ex) {
		ex.printStackTrace();
	    }
	});
    }

    @Override
    public void updateStreetCosts(EnumMap<Resource, Integer> resources) {
	Platform.runLater(() -> {
	    try {
		buildPane.updateStreetCosts(resources);
		buyPane.updateStreetCosts(resources);
	    } catch (RemoteException ex) {
		ex.printStackTrace();
	    }
	});
    }

    @Override
    public void updateVillageCosts(EnumMap<Resource, Integer> resources) {
	Platform.runLater(() -> {
	    try {
		buildPane.updateVillageCosts(resources);
		buyPane.updateVillageCosts(resources);
	    } catch (RemoteException ex) {
		ex.printStackTrace();
	    }
	});
    }

    @Override
    public void updateCardCosts(EnumMap<Resource, Integer> resources) {
	Platform.runLater(() -> {
	    try {
		buildPane.updateCardCosts(resources);
		buyPane.updateCardCosts(resources);
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	});
    }

    @Override
    public void updateTrades(List<Trade> allTrades) {
	Platform.runLater(() -> {
	    try {
		tradePane.updateTrades(allTrades);
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	});
    }

    @Override
    public void updateRound(int round) {
	Platform.runLater(() -> {
	    try {
		scorePane.updateRound(round);
		infoPane.updateRound(round);
	    } catch (RemoteException ex) {
		ex.printStackTrace();
	    }
	});
    }

    @Override
    public void updateTurn(Player who, TurnState what) {
	Platform.runLater(() -> {
	    try {
		scorePane.updateTurn(who, what);
		infoPane.updateTurn(who, what);
	    } catch (RemoteException ex) {
		ex.printStackTrace();
	    }
	});
    }

    @Override
    public void updateScore(Player pl, int score) {
	Platform.runLater(() -> {
	    try {
		scorePane.updateScore(pl, score);
		playerPane.updateScore(pl, score);
	    } catch (RemoteException ex) {
		ex.printStackTrace();
	    }
	});
    }

    @Override
    public void highlightStreets(Collection<Street> streets) {
	Platform.runLater(() -> gamepane.highlightStreet(streets));
    }

    @Override
    public void highlightBuildings(Collection<Building> buildings, BuildingType type) {
	Platform.runLater(() -> gamepane.highlightBuilding(buildings, type));
    }

    private void setBlockedButtons(boolean blocked) {
	if (blocked) {
	    nxtButton.setDisabled();
	    buildButton.setDisabled();
	    buyButton.setDisabled();
	} else {
	    nxtButton.setEnabled();
	    buildButton.setEnabled();
	    buyButton.setEnabled();
	}
    }

    @Override
    public void blockActions() {
	Platform.runLater(() -> setBlockedButtons(true));
    }

    @Override
    public void unblockActions() {
	Platform.runLater(() -> setBlockedButtons(false));
    }

    @Override
    public void updateDices(int dice1, int dice2) {
	Platform.runLater(() -> {
	    if (overlayPane instanceof UpdateDice) {
		try {
		    ((UpdateDice) overlayPane).updateDices(dice1, dice2);
		} catch (RemoteException ex) {
		    ex.printStackTrace();
		}
	    }
	});
    }

    @Override
    public void setSelectable(SelectState selectables) {
	Platform.runLater(() -> {
	    try {
		gamepane.setSelectable(selectables);
	    } catch (RemoteException ex) {
		ex.printStackTrace();
	    }
	});
    }

    @Override
    public void updatePlayerOrder(List<Player> order) {
	Platform.runLater(() -> playerPane.updatePlayerOrder(order));
    }
}
