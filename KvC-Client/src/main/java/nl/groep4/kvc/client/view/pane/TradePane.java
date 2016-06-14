package nl.groep4.kvc.client.view.pane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.view.elements.MenuButton;

public class TradePane extends Application implements PaneHolder {
    StackPane pane = new StackPane();
    Group root = new Group();
    MenuButton plaats = new MenuButton("Plaats");
    MenuButton terug = new MenuButton("Terug");
    HBox hbox = new HBox();
    VBox vbox = new VBox();
    ScrollBar sc = new ScrollBar();
    Text resources = new Text();
    Text speler1 = new Text();
    Text speler2 = new Text();
    Text speler3 = new Text();
    Text speler4 = new Text();
    Text speler5 = new Text();
    Text speler6 = new Text();
    ScrollPane sp = new ScrollPane();

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

	tb.getChildren().addAll(resources);
	resources = new Text("Wheat \t Wood \t Wool \t Stone \t Ore");

	ScrollPane scrollPane = new ScrollPane(hbox);

	BorderPane toolbar = new BorderPane();
	toolbar.setTop(tb);

	BorderPane root = new BorderPane(scrollPane);

	BorderPane bp = new BorderPane();

	bp.getChildren().addAll(SceneUtil.getGamePane(), root);
	root.setPadding(new Insets(60));

	scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
	scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
	scrollPane.setMinHeight(300);
	scrollPane.setMinWidth(575);
	scrollPane.setStyle("-fx-background: grey; -fx-opacity: 0.8; -fx-border-color: black; -fx-border-width: 2px;");

	buttons.getChildren().addAll(terug, plaats);
	root.setBottom(buttons);

	Scene scene = new Scene(bp, 800, 400);
	primaryStage.setScene(scene);
	primaryStage.show();
    }

    public static void main(String[] args) {
	launch(args);
    }

}