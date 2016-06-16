package nl.groep4.kvc.client.view.pane;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.scene.SceneMap;

public class PausePane implements PaneHolder {

    private MenuButton continueButton;
    private Text pause;

    public PausePane(SceneMap sceneMap) {
	// TODO Auto-generated constructor stub
    }

    @Override
    public Pane getPane() {

	StackPane pausepane = new StackPane();
	VBox pausebox = new VBox();

	continueButton = new MenuButton(425, 500, TranslationManager.translate("pause.button.continue"));
	pause = new Text(TranslationManager.translate("pause.label.pause"));

	continueButton.setFont(ViewMaster.FONT);
	pause.setFill(Color.WHITE);
	pause.setStroke(Color.BLACK);
	pause.setFont(ViewMaster.TITLE_FONT);

	pausebox.setAlignment(Pos.CENTER);

	Node background = SceneUtil.getGamePane();

	pausebox.getChildren().addAll(pause, continueButton);
	pausepane.getChildren().addAll(background, pausebox);

	return pausepane;
    }

    @Override
    public void updateTranslation() {
	continueButton.updateText(TranslationManager.translate("pause.label.pause"));
	pause.setText(TranslationManager.translate("pause.label.pause"));

    }
}