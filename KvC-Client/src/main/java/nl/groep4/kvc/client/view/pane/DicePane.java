package nl.groep4.kvc.client.view.pane;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.elements.MenuButton;

public class DicePane implements PaneHolder {
    Pane dicePane;
    Text clickToThrow;
    Text firstDice;
    Text secondDice;
    MenuButton throwDice;

    @Override
    public Pane getPane() {
	dicePane = new Pane();
	clickToThrow = new Text(TranslationManager.translate("map.throwdice.clickToThrow"));
	firstDice = new Text("*");
	secondDice = new Text("*");
	firstDice.setLayoutX(400);
	firstDice.setLayoutY(350);
	secondDice.setLayoutX(600);
	secondDice.setLayoutY(350);
	clickToThrow.setLayoutX(500);
	clickToThrow.setLayoutY(300);
	clickToThrow.setTextAlignment(TextAlignment.CENTER);
	throwDice = new MenuButton(300, 300, "map.throwdice.throw");
	dicePane.getChildren().addAll(SceneUtil.getLobbyForeground(), clickToThrow, throwDice);
	SceneUtil.fadeIn(SceneUtil.getLobbyForeground(), clickToThrow, throwDice);
	return dicePane;
    }

    @Override
    public void updateTranslation() {
	// TODO Auto-generated method stub

    }

}
