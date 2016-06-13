package nl.groep4.kvc.client.view.pane;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.SceneUtil;

public class StockPane implements PaneHolder {

    StackPane cardPane;
    VBox resCards;
    VBox devCards;
    Text amntWood;
    Text amntOre;
    Text amntStone;
    Text amntWheat;
    Text amntWool;

    @Override
    public Pane getPane() {
	cardPane = new StackPane();
	resCards = new VBox();
	devCards = new VBox();

	cardPane.getChildren().addAll(SceneUtil.getCardPlank(), cardPane);
	return cardPane;
    }

    @Override
    public void updateTranslation() {
	// TODO Auto-generated method stub

    }

}
