package nl.groep4.kvc.client.view.pane;

import java.util.EnumMap;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.KvCText;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.elements.ResourceCardUtil;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.UpdateStock;

public class StockPane implements PaneHolder, UpdateStock {

    StackPane cardPane;
    HBox devCards;
    HBox resCards;
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
    ResourceCardUtil cards;
    VBox allThings;
    VBox buttons;
    Text information;

    private boolean isOpen = false;

    private SceneMap view;

    public StockPane(SceneMap view) {
	this.view = view;
	this.view.toString();
	// TODO: Luc: Controller aanroepen via view.getController().useCard()
    }

    @Override
    public Pane getPane() {
	cards = new ResourceCardUtil();
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
	cardPane = new StackPane();
	devCards = new HBox();
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
	information = new KvCText();

	devCards.setMaxHeight(150);
	devCards.setMaxWidth(600);
	devCards.getChildren().addAll(getKnightCard(), getInventCard(), getMonoCard(), getCathCard(), getRoadCard());
	devCards.setAlignment(Pos.CENTER);
	devCards.setPadding(new Insets(8, 0, 8, 0));

	allThings.setAlignment(Pos.BOTTOM_CENTER);
	cardPane.setMouseTransparent(true);
	return cardPane;
    }

    public void openStock() {
	isOpen = true;
	allThings.getChildren().addAll(resCards, devCards, information);
	cardPane.getChildren().remove(allThings);
	cardPane.getChildren().addAll(cards.getCardPlank(), allThings);
	cardPane.setMouseTransparent(false);

    }

    public void closeStock() {
	isOpen = false;
	allThings.getChildren().removeAll(resCards, devCards, information);
	cardPane.getChildren().removeAll(cards.getCardPlank());
	cardPane.setMouseTransparent(true);
    }

    public boolean isOpen() {
	return this.isOpen;
    }

    public void updateInfo(String info) {
	information.setText(info);
    }

    @Override
    public void updateTranslation() {
	cards.updateTranslation();
	information.setText(TranslationManager.translate("cards.monopoly.info"));
	information.setText(TranslationManager.translate("cards.knight.info"));
	information.setText(TranslationManager.translate("cards.road.info"));
	information.setText(TranslationManager.translate("cards.cath.info"));
	information.setText(TranslationManager.translate("cards.invent.info"));
    }

    @Override
    public void updateStock(EnumMap<Resource, Integer> resources) {
	cards.updateStock(resources);
    }

    @Override
    public void updateStock(List<Card> cards) {
	// TODO Auto-generated method stub

    }

    /**
     * Gets the resource for the cathedral card
     * 
     * @return image of the cathedral card
     */
    public Node getCathCard() {
	ImageView cathCard;
	cathCard = new ImageView("img/cards/card_cathedral.png");
	cathCard.setFitHeight(100);
	cathCard.setFitWidth(80);
	cathCard.setOnMouseEntered(e -> updateInfo(TranslationManager.translate("cards.cath.info")));
	cathCard.setOnMouseExited(e -> updateInfo(""));
	return cathCard;
    }

    /**
     * Gets the resource for the monopoly card
     * 
     * @return image of the monopoly card
     */
    public Node getMonoCard() {
	ImageView monoCard;
	monoCard = new ImageView("img/cards/card_monopoly.png");
	monoCard.setFitHeight(100);
	monoCard.setFitWidth(80);
	monoCard.setOnMouseEntered(e -> updateInfo(TranslationManager.translate("cards.monopoly.info")));
	monoCard.setOnMouseExited(e -> updateInfo(""));
	return monoCard;
    }

    /**
     * Gets the resource for the knight card
     * 
     * @return image of the knight card
     */
    public Node getKnightCard() {
	ImageView knightCard;
	knightCard = new ImageView("img/cards/card_knight.png");
	knightCard.setFitHeight(100);
	knightCard.setFitWidth(80);
	knightCard.setOnMouseEntered(e -> updateInfo(TranslationManager.translate("cards.knight.info")));
	knightCard.setOnMouseExited(e -> updateInfo(""));
	return knightCard;
    }

    /**
     * Gets the resource for the invention card
     * 
     * @return image of the invention card
     */
    public Node getInventCard() {
	ImageView inventCard;
	inventCard = new ImageView("img/cards/card_invention.png");
	inventCard.setFitHeight(100);
	inventCard.setFitWidth(80);
	inventCard.setOnMouseEntered(e -> updateInfo(TranslationManager.translate("cards.invent.info")));
	inventCard.setOnMouseExited(e -> updateInfo(""));
	return inventCard;
    }

    /**
     * Gets the resource for the road card
     * 
     * @return image of the road card
     */
    public Node getRoadCard() {
	ImageView roadCard;
	roadCard = new ImageView("img/cards/card_road.png");
	roadCard.setFitHeight(100);
	roadCard.setFitWidth(80);
	roadCard.setOnMouseEntered(e -> updateInfo(TranslationManager.translate("cards.road.info")));
	roadCard.setOnMouseExited(e -> updateInfo(""));
	return roadCard;
    }
}
