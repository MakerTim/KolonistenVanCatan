package nl.groep4.kvc.client.view.pane;

import java.util.EnumMap;
import java.util.List;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.UpdateStock;

public class StockPane implements PaneHolder, UpdateStock {

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

    @Override
    public void updateStock(EnumMap<Resource, Integer> resources) {
	// TODO Auto-generated method stub

    }

    @Override
    public void updateStock(List<Card> cards) {
	// TODO Auto-generated method stub

    }

}
