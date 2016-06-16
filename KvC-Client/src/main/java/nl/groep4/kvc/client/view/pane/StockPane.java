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
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.UpdateStock;

public class StockPane implements PaneHolder, UpdateStock {

    StackPane cardPane;
    HBox resCards;
    HBox devCards;
    VBox woodText;
    VBox oreText;
    VBox stoneText;
    VBox woolText;
    VBox wheatText;
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

    private SceneMap view;

    public StockPane(SceneMap view) {
	this.view = view;
	this.view.toString();
	// TODO: Luc: Controller aanroepen via view.getController().useCard()
    }

    @Override
    public Pane getPane() {
	cards = new ResourceCard();
	wood = new StackPane();
	wheat = new StackPane();
	ore = new StackPane();
	wool = new StackPane();
	stone = new StackPane();

	woodText = new VBox();
	wheatText = new VBox();
	oreText = new VBox();
	woolText = new VBox();
	stoneText = new VBox();

	woodText.setAlignment(Pos.CENTER);
	woodText.getChildren().addAll(cards.getWoodText(), cards.getWoodName());
	oreText.setAlignment(Pos.CENTER);
	oreText.getChildren().addAll(cards.getOreText(), cards.getOreName());
	wheatText.setAlignment(Pos.CENTER);
	wheatText.getChildren().addAll(cards.getWheatText(), cards.getWheatName());
	stoneText.setAlignment(Pos.CENTER);
	stoneText.getChildren().addAll(cards.getStoneText(), cards.getStoneName());
	woolText.setAlignment(Pos.CENTER);
	woolText.getChildren().addAll(cards.getWoolText(), cards.getWoolName());

	resCards = new HBox();
	devCards = new HBox();
	cardPane = new StackPane();
	allThings = new VBox();
	buttons = new VBox();
	hideCards = new MenuButton(TranslationManager.translate("map.stock.hide"));
	hideCards.setFont(ViewMaster.FONT);
	showCards = new MenuButton(TranslationManager.translate("map.stock.show"));
	showCards.setFont(ViewMaster.FONT);

	wood.getChildren().addAll(cards.getWoodCard(), woodText);
	wheat.getChildren().addAll(cards.getWheatCard(), wheatText);
	ore.getChildren().addAll(cards.getOreCard(), oreText);
	stone.getChildren().addAll(cards.getStoneCard(), stoneText);
	wool.getChildren().addAll(cards.getWoolCard(), woolText);

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
	cards.updateTranslation();
    }

    @Override
    public void updateStock(EnumMap<Resource, Integer> resources) {
	cards.updateStock(resources);
    }

    @Override
    public void updateStock(List<Card> cards) {
	// TODO Auto-generated method stub

    }

}
