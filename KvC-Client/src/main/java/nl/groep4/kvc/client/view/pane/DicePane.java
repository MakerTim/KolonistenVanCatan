package nl.groep4.kvc.client.view.pane;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;

public class DicePane implements PaneHolder {
    StackPane dicePane;
    Text clickToThrow;
    Text firstDice;
    Text secondDice;
    MenuButton throwDice;
    HBox dices;
    VBox things;

    @Override
    public Pane getPane() {
	dices = new HBox();
	things = new VBox(20);

	dicePane = new StackPane();

	clickToThrow = new Text(TranslationManager.translate("map.throwdice.clickToThrow"));
	firstDice = new Text("*");
	secondDice = new Text("*");
	throwDice = new MenuButton(0, 0, TranslationManager.translate("map.throwdice.throw"));
	throwDice.setFont(ViewMaster.FONT);

	firstDice.setFont(ViewMaster.FONT);
	secondDice.setFont(ViewMaster.FONT);
	clickToThrow.setFont(ViewMaster.FONT);

	dices.getChildren().addAll(firstDice, secondDice);
	things.getChildren().addAll(clickToThrow, dices, throwDice);

	things.setAlignment(Pos.CENTER);
	dices.setAlignment(Pos.CENTER);
	dicePane.getChildren().addAll(SceneUtil.getLobbyForeground(), things);
	SceneUtil.fadeIn(SceneUtil.getLobbyForeground(), things);
	return dicePane;
    }

    @Override
    public void updateTranslation() {
	// TODO Auto-generated method stub

    }

}
