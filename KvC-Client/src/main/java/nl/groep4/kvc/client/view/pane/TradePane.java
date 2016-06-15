package nl.groep4.kvc.client.view.pane;

import java.rmi.RemoteException;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.common.interfaces.Trade;
import nl.groep4.kvc.common.interfaces.UpdateTrade;

public class TradePane implements PaneHolder, UpdateTrade {
    StackPane pane = new StackPane();
    Group root = new Group();
    MenuButton plaats = new MenuButton(425, 500, TranslationManager.translate("trade.button.place"));
    MenuButton terug = new MenuButton(425, 500, TranslationManager.translate("trade.button.back"));
    HBox hbox = new HBox();
    VBox vbox = new VBox();
    ScrollPane sp = new ScrollPane();
    GridPane gp = new GridPane();

    @Override
    public Pane getPane() {
	HBox hbox = new HBox();
	HBox buttons = new HBox();
	HBox tb = new HBox();

	gp.setHgap(10);
	gp.setVgap(10);
	gp.setPadding(new Insets(50, 50, 50, 50));

	Text wheat = new Text("Wheat");
	wheat.setFill(Color.WHITE);
	wheat.setStroke(Color.BLACK);
	wheat.setFont(ViewMaster.FONT);
	gp.add(wheat, 14, 0);

	Text wood = new Text("Wood");
	wood.setFill(Color.WHITE);
	wood.setStroke(Color.BLACK);
	wood.setFont(ViewMaster.FONT);
	gp.add(wood, 16, 0);

	Text wool = new Text("Wool");
	wool.setFill(Color.WHITE);
	wool.setStroke(Color.BLACK);
	wool.setFont(ViewMaster.FONT);
	gp.add(wool, 18, 0);

	Text stone = new Text("Stone");
	stone.setFill(Color.WHITE);
	stone.setStroke(Color.BLACK);
	stone.setFont(ViewMaster.FONT);
	gp.add(stone, 20, 0);

	Text ore = new Text("Ore");
	ore.setFill(Color.WHITE);
	ore.setStroke(Color.BLACK);
	ore.setFont(ViewMaster.FONT);
	gp.add(ore, 22, 0);

	ScrollPane scrollPane = new ScrollPane(hbox);
	scrollPane.getStylesheets().add("/assets/stylesheet.css");
	scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
	scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
	scrollPane.setMinHeight(280);
	scrollPane.setMinWidth(575);

	BorderPane toolbar = new BorderPane();
	toolbar.setTop(tb);

	BorderPane root = new BorderPane(scrollPane);
	root.setPadding(new Insets(80, 60, 60, 60));

	BorderPane bp = new BorderPane();
	bp.getChildren().addAll(SceneUtil.getGamePane(), gp, root);

	scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
	scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
	scrollPane.setMinHeight(280);
	scrollPane.setMinWidth(575);

	buttons.setAlignment(Pos.BOTTOM_RIGHT);
	buttons.getChildren().addAll(terug, plaats);
	root.setBottom(buttons);

	return bp;
    }

    @Override
    public void updateTranslation() {
	// TODO Auto-generated method stub
    }

    @Override
    public void updateTrades(List<Trade> allTrades) throws RemoteException {
	// TODO Auto-generated method stub

    }

}