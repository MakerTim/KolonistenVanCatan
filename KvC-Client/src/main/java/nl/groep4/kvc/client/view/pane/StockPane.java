package nl.groep4.kvc.client.view.pane;

import java.util.EnumMap;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.elements.ResourceCard;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.UpdateStock;

public class StockPane implements PaneHolder, UpdateStock {
    // TODO StockPaneAfmaken

    StackPane cardPane;
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
    VBox allThings;
    VBox buttons;

    private boolean isOpen = false;

    @Override
    public Pane getPane() {
	cards = new ResourceCard();
	wood = new StackPane();
	wheat = new StackPane();
	ore = new StackPane();
	wool = new StackPane();
	stone = new StackPane();
	resCards = new HBox();
	devCards = new HBox();
	cardPane = new StackPane();
	allThings = new VBox();
	buttons = new VBox();
	hideCards = new MenuButton(TranslationManager.translate("map.stock.hide"));
	hideCards.setFont(ViewMaster.FONT);
	showCards = new MenuButton(TranslationManager.translate("map.stock.show"));
	showCards.setFont(ViewMaster.FONT);

	wood.getChildren().addAll(cards.getWoodCard(), cards.getWoodText());
	wheat.getChildren().addAll(cards.getWheatCard(), cards.getWheatText());
	ore.getChildren().addAll(cards.getOreCard(), cards.getOreText());
	stone.getChildren().addAll(cards.getStoneCard(), cards.getStoneText());
	wool.getChildren().addAll(cards.getWoolCard(), cards.getWoolText());

	resCards.getChildren().addAll(wood, wheat, wool, stone, ore);
	resCards.setAlignment(Pos.CENTER);
	buttons.getChildren().addAll(showCards);

	allThings.setAlignment(Pos.CENTER);
	cardPane.setMouseTransparent(true);
	return cardPane;
    }

    public void openStock() {
	isOpen = true;
	allThings.getChildren().addAll(resCards);
	cardPane.getChildren().remove(allThings);
	cardPane.getChildren().addAll(cards.getCardPlank(), allThings);

    }

    public void closeStock() {
	isOpen = false;
	allThings.getChildren().removeAll(resCards);
	cardPane.getChildren().removeAll(cards.getCardPlank());
    }

    public boolean isOpen() {
	return this.isOpen;
    }

    @Override
    public void updateTranslation() {
	hideCards.updateText(TranslationManager.translate("map.stock.hide"));
	showCards.updateText(TranslationManager.translate("map.stock.show"));
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
