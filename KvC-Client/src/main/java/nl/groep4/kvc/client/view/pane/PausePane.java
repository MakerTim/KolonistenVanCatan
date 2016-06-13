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

public class PausePane implements PaneHolder {

    MenuButton continueButton = new MenuButton(425, 500, TranslationManager.translate("pause.button.continue"));
    Text pause;

    @Override
    public Pane getPane() { // TODO Auto-generated method stub

	continueButton.setFont(ViewMaster.FONT);
	VBox pausebox = new VBox();
	pausebox.setAlignment(Pos.CENTER);
	StackPane pausepane = new StackPane();
	pause = new Text(TranslationManager.translate("pause.label.pause"));
	pause.setFill(Color.WHITE);
	pause.setStroke(Color.BLACK);
	pause.setFont(ViewMaster.TITLE_FONT);

	Node background = SceneUtil.getGamePane();
	pausebox.getChildren().addAll(pause, continueButton);
	pausepane.getChildren().addAll(background, pausebox);

	return pausepane;
    }

    @Override
    public void updateTranslation() {
	// TODO Auto-generated method
	continueButton.updateText(TranslationManager.translate("pause.label.pause"));
	pause.setText(TranslationManager.translate("pause.label.pause"));

    }
}