package nl.groep4.kvc.client.view.pane;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.elements.MenuButton;

@SuppressWarnings("unused")
public class CardPane extends Application implements PaneHolder {

    MenuButton yes = new MenuButton(425, 500, TranslationManager.translate("decision.yes"));
    MenuButton no = new MenuButton(425, 500, TranslationManager.translate("decision.no"));

    Group root = new Group();
    Scene scene = new Scene(root);

    public static void main(String[] args) {
	launch(args);
    }

    @Override
    public Pane getPane() {
	// TODO Auto-generated method stub

	return null;
    }

    @Override
    public void updateTranslation() {
	// TODO Auto-generated method stub

	MenuButton yes = new MenuButton(425, 500, TranslationManager.translate("decision.yes"));
	MenuButton no = new MenuButton(425, 500, TranslationManager.translate("decision.no"));

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
	// TODO Auto-generated method stub

	MenuButton yes = new MenuButton(425, 500, TranslationManager.translate("decision.yes"));
	MenuButton no = new MenuButton(425, 500, TranslationManager.translate("decision.no"));
	Group root = new Group();
	Scene cards = new Scene(root, 1366, 768);

	StackPane cardPane = new StackPane();
	cardPane.setAlignment(Pos.CENTER);

	HBox hbox = new HBox(8);
	hbox.setPrefWidth(300);
	hbox.setPrefHeight(450);
	hbox.setAlignment(Pos.CENTER);

	VBox vbox = new VBox(8);
	vbox.setPrefWidth(1366);
	vbox.setPrefHeight(768);
	vbox.setAlignment(Pos.CENTER);

	Text msg = new Text("msg.buycard?");

	hbox.getChildren().addAll(yes, no);
	vbox.getChildren().addAll(hbox, msg);
	cardPane.getChildren().addAll(SceneUtil.getBuildMenuBackground(), vbox);
	root.getChildren().add(cardPane);
	primaryStage.setScene(cards);
	primaryStage.show();

    }

}
