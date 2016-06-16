package nl.groep4.kvc.client.view.pane;

import java.rmi.RemoteException;
import java.util.List;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.interfaces.Trade;
import nl.groep4.kvc.common.interfaces.UpdateTrade;

/**
 * Generates trade pane
 * 
 * @author Matthijs
 * @version 1.0
 */
public class TradePane implements PaneHolder, UpdateTrade {

    private SceneMap sceneMap;
    private MenuButton plaats;
    private MenuButton terug;

    public TradePane(SceneMap sceneMap) {
	this.sceneMap = sceneMap;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Pane getPane() {
	plaats = new MenuButton(425, 500, TranslationManager.translate("trade.button.place"));
	terug = new MenuButton(425, 500, TranslationManager.translate("trade.button.back"));
	GridPane gp = new GridPane();
	ScrollPane scrollpane = new ScrollPane(gp);
	TableView<String> table = new TableView<>();
	StackPane stackpane = new StackPane();
	HBox buttons = new HBox();
	VBox vbox = new VBox();
	HBox tb = new HBox();
	vbox.setAlignment(Pos.CENTER);
	tb.setAlignment(Pos.CENTER);

	TableColumn<String, ?> emptyCol = new TableColumn<>("\n");
	TableColumn<String, ?> wheatCol = new TableColumn<>("Wheat");
	TableColumn<String, ?> woodCol = new TableColumn<>("Wood");
	TableColumn<String, ?> woolCol = new TableColumn<>("Wool");
	TableColumn<String, ?> stoneCol = new TableColumn<>("Stone");
	TableColumn<String, ?> oreCol = new TableColumn<>("Ore");

	table.getColumns().addAll(emptyCol, wheatCol, woodCol, woolCol, stoneCol, oreCol);
	table.setFixedCellSize(300);
	table.prefHeightProperty()
		.bind(table.fixedCellSizeProperty().multiply(Bindings.size(table.getItems()).add(1.01)));
	table.minHeightProperty().bind(table.prefHeightProperty());
	table.maxHeightProperty().bind(table.prefHeightProperty());

	table.getStylesheets().add("/assets/stylesheet.css");
	scrollpane.getStylesheets().add("/assets/stylesheet.css");
	scrollpane.setHbarPolicy(ScrollBarPolicy.NEVER);
	scrollpane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
	scrollpane.setMinHeight(280);
	scrollpane.setMinWidth(575);

	plaats.setFont(ViewMaster.FONT);
	terug.setFont(ViewMaster.FONT);

	buttons.setAlignment(Pos.BOTTOM_RIGHT);
	stackpane.setAlignment(Pos.CENTER);

	buttons.getChildren().addAll(terug, plaats);
	vbox.getChildren().addAll(table, buttons);
	tb.getChildren().addAll(vbox);
	stackpane.getChildren().addAll(SceneUtil.getGamePane(), tb);

	terug.setOnMouseClicked(klick -> onBackClick());
	plaats.setOnMouseClicked(klick -> onPlaceClick());

	return stackpane;
    }

    private void onBackClick() {
	sceneMap.closeOverlay();
    }

    private void onPlaceClick() {

    }

    @Override
    public void updateTranslation() {
	terug.updateText(TranslationManager.translate("trade.button.back"));
	plaats.updateText(TranslationManager.translate("trade.button.plaats"));

    }

    @Override
    public void updateTrades(List<Trade> allTrades) throws RemoteException {

    }
}