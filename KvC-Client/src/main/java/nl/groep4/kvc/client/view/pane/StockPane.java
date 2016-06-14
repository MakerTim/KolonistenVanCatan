package nl.groep4.kvc.client.view.pane;

import java.util.EnumMap;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.elements.ResourceCard;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.UpdateStock;

public class StockPane implements PaneHolder, UpdateStock {
    // TODO StockPaneAfmaken

    StackPane cardPane;
    Pane allThings;
    HBox resCards;
    HBox devCards;
    Text amntWood;
    Text amntOre;
    Text amntStone;
    Text amntWheat;
    Text amntWool;
    StackPane wood;
    StackPane ore;
    StackPane stone;
    StackPane wheat;
    StackPane wool;
    MenuButton hideCards;
    MenuButton showCards;
    ResourceCard cards;

    @Override
    public Pane getPane() {
	cards = new ResourceCard();

	wood = new StackPane();
	wheat = new StackPane();
	ore = new StackPane();
	wool = new StackPane();
	stone = new StackPane();

	hideCards = new MenuButton(0, 0, "Hide");
	hideCards.setFont(ViewMaster.FONT);
	hideCards.setAlignment(Pos.BOTTOM_CENTER);

	showCards = new MenuButton(0, 0, "Show");
	showCards.setFont(ViewMaster.FONT);
	showCards.setAlignment(Pos.BOTTOM_CENTER);

	wood.getChildren().addAll(cards.getWoodCard(), cards.getWoodText());
	wheat.getChildren().addAll(cards.getWheatCard(), cards.getWheatText());
	ore.getChildren().addAll(cards.getOreCard(), cards.getOreText());
	stone.getChildren().addAll(cards.getStoneCard(), cards.getStoneText());
	wool.getChildren().addAll(cards.getWoolCard(), cards.getWoolText());

	resCards = new HBox();
	devCards = new HBox();
	cardPane = new StackPane();
	allThings = new Pane();

	resCards.getChildren().addAll(wood, wheat, wool, stone, ore);
	resCards.setAlignment(Pos.CENTER);

	cardPane.getChildren().addAll(showCards);

	hideCards.registerClick(() -> {
	    hideCards.setDisabled();
	    showCards.setEnabled();
	    cardPane.getChildren().removeAll(cards.getCardPlank(), resCards, devCards, hideCards);

	    cardPane.getChildren().add(showCards);
	});

	showCards.registerClick(() -> {
	    hideCards.setEnabled();
	    showCards.setDisabled();
	    cardPane.getChildren().addAll(cards.getCardPlank(), resCards, devCards, hideCards);
	    cardPane.getChildren().remove(showCards);
	});
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
