package nl.groep4.kvc.client.view.pane;

import java.rmi.RemoteException;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.common.interfaces.Trade;
import nl.groep4.kvc.common.interfaces.UpdateTrade;

public class TradePane extends Application implements PaneHolder, UpdateTrade {
    StackPane pane = new StackPane();
    Group root = new Group();
    MenuButton plaats = new MenuButton("Plaats");
    MenuButton terug = new MenuButton("Terug");
    HBox hbox = new HBox();
    VBox vbox = new VBox();
    ScrollPane sp = new ScrollPane();
    GridPane gp = new GridPane();

    @Override
    public Pane getPane() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void updateTranslation() {
	// TODO Auto-generated method stub
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

	HBox hbox = new HBox();
	HBox buttons = new HBox();
	HBox tb = new HBox();

	gp.setHgap(10);
	gp.setVgap(10);
	gp.setPadding(new Insets(50, 50, 50, 50));

	Text category = new Text("Wheat");
	category.setFill(Color.WHITE);
	category.setStroke(Color.BLACK);
	category.setFont(ViewMaster.FONT);
	gp.add(category, 1, 0);

	ScrollPane scrollPane = new ScrollPane(hbox);
	scrollPane.getStylesheets().add("/assets/stylesheet.css");
	BorderPane toolbar = new BorderPane();
	toolbar.setTop(tb);

	BorderPane root = new BorderPane(scrollPane);

	BorderPane bp = new BorderPane();

	bp.getChildren().addAll(SceneUtil.getGamePane(), gp, root);
	root.setPadding(new Insets(60));

	scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
	scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
	scrollPane.setMinHeight(280);
	scrollPane.setMinWidth(575);

	buttons.getChildren().addAll(terug, plaats);
	root.setBottom(buttons);

	Scene scene = new Scene(bp, 800, 400);

	primaryStage.setScene(scene);
	primaryStage.show();
    }

    public static void main(String[] args) {
	launch(args);
    }

    @Override
    public void updateTrades(List<Trade> allTrades) throws RemoteException {
	// TODO Auto-generated method stub

    }

}