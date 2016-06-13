package nl.groep4.kvc.client.view.pane;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;

public class PausePane extends Application implements PaneHolder {

    MenuButton continueButton = new MenuButton(425, 500, TranslationManager.translate("pause.button.continue"));
    Text pause;

    /*
     * @Override public Pane getPane() { // TODO Auto-generated method stub
     *
     * VBox pausebox = new VBox(); pausebox.setAlignment(Pos.CENTER); StackPane
     * pausepane = new StackPane(); pause = new
     * Text(TranslationManager.translate("pause.label.pause"));
     * pausebox.getChildren().addAll(pause, continueButton);
     * pausepane.getChildren().add(pausebox); Scene scene = new Scene(pausepane,
     * 500, 600);
     *
     * return pausepane; }
     *
     * @Override public void updateTranslation() { // TODO Auto-generated method
     * stub
     *
     * continueButton.updateText(TranslationManager.translate(
     * "pause.label.pause"));
     * pause.setText(TranslationManager.translate("pause.label.pause")); }
     */
    @Override
    public void start(Stage arg0) throws Exception {
	// TODO Auto-generated method stub
	continueButton.setFont(ViewMaster.FONT);
	VBox pausebox = new VBox();
	pausebox.setAlignment(Pos.CENTER);
	StackPane pausepane = new StackPane();
	pause = new Text(TranslationManager.translate("pause.label.pause"));
	pause.setFont(ViewMaster.FONT);

	Node background = SceneUtil.getGamePane();
	pausebox.getChildren().addAll(pause, continueButton);
	pausepane.getChildren().addAll(background, pausebox);
	Scene scene = new Scene(pausepane, 700, 600);
	arg0.setScene(scene);
	arg0.show();
    }

    @Override
    public Pane getPane() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void updateTranslation() {
	// TODO Auto-generated method stub

    }

    public static void main(String[] args) {
	launch(args);

    }
}