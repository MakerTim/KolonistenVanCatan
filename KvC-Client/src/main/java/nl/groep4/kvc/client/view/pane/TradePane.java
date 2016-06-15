package nl.groep4.kvc.client.view.pane;

import java.rmi.RemoteException;
import java.util.List;

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
    StackPane stackpane = new StackPane();
    MenuButton plaats = new MenuButton(425, 500, TranslationManager.translate("trade.button.place"));
    MenuButton terug = new MenuButton(425, 500, TranslationManager.translate("trade.button.back"));
    HBox hbox = new HBox();
    VBox vbox = new VBox();
    GridPane gp = new GridPane();
    ScrollPane scrollpane = new ScrollPane(gp);
    TableView<String> table = new TableView<>();

    public TradePane(SceneMap sceneMap) {
	// TODO Auto-generated constructor stub
    }

    @SuppressWarnings("unchecked")
    @Override
    public Pane getPane() {
	HBox buttons = new HBox();
	HBox tb = new HBox();
	tb.setAlignment(Pos.TOP_CENTER);
	vbox.setAlignment(Pos.CENTER);

	TableColumn<String, ?> emptyCol = new TableColumn<>("\n");
	TableColumn<String, ?> wheatCol = new TableColumn<>("Wheat");
	TableColumn<String, ?> woodCol = new TableColumn<>("Wood");
	TableColumn<String, ?> woolCol = new TableColumn<>("Wool");
	TableColumn<String, ?> stoneCol = new TableColumn<>("Stone");
	TableColumn<String, ?> oreCol = new TableColumn<>("Ore");

	table.getColumns().addAll(emptyCol, wheatCol, woodCol, woolCol, stoneCol, oreCol);

	scrollpane.getStylesheets().add("/assets/stylesheet.css");
	scrollpane.setHbarPolicy(ScrollBarPolicy.NEVER);
	scrollpane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
	scrollpane.setMinHeight(280);
	scrollpane.setMinWidth(575);

	buttons.setAlignment(Pos.BOTTOM_RIGHT);
	buttons.getChildren().addAll(terug, plaats);

	stackpane.setAlignment(Pos.CENTER);

	tb.getChildren().addAll(table);
	vbox.getChildren().addAll(tb);
	stackpane.getChildren().addAll(SceneUtil.getGamePane(), vbox);

	return stackpane;
    }

    @Override
    public void updateTranslation() {

    }

    @Override
    public void updateTrades(List<Trade> allTrades) throws RemoteException {

    }
}