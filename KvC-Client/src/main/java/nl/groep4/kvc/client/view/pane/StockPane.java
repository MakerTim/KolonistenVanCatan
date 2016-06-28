package nl.groep4.kvc.client.view.pane;

import java.util.List;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.controller.ClientRefrence;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.KvCText;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.elements.ResourceCard;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.enumeration.CardType;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateStock;
import nl.groep4.kvc.common.interfaces.VictoryCard;

/**
 * The card pane show all obtained cards.
 * 
 * @author Luc
 * @version 1.1
 * 
 * 
 **/
public class StockPane implements PaneHolder, UpdateStock {

    StackPane cardPane;
    ScrollPane development;
    HBox devCards = new HBox();
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
    ResourceCard cards;
    VBox allThings;
    VBox buttons;
    Text information;

    private boolean isOpen = false;

    private SceneMap view;

    /**
     * View of StockPane.
     * 
     * @param view
     *            View of the map.
     */
    public StockPane(SceneMap view) {
	this.view = view;
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

	resCards.getChildren().addAll(wood, stone, wool, wheat, ore);
	resCards.setAlignment(Pos.CENTER);
	buttons.getChildren().addAll(showCards);
	information = new KvCText("");

	devCards.setMaxHeight(150);
	devCards.setMaxWidth(600);
	devCards.setAlignment(Pos.CENTER);
	devCards.setPadding(new Insets(8, 0, 8, 0));
	development = new ScrollPane(devCards);
	development.setMaxWidth(600);

	allThings.setAlignment(Pos.BOTTOM_CENTER);
	cardPane.setMouseTransparent(true);
	cardPane.getStylesheets().add("/assets/stylesheet.css");
	development.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
	development.setVbarPolicy(ScrollBarPolicy.NEVER);
	development.setMinHeight(100);
	return cardPane;
    }

    /**
     * Method to open stock.
     * 
     */
    public void openStock() {
	isOpen = true;
	allThings.getChildren().addAll(resCards, development, information);
	cardPane.getChildren().remove(allThings);
	cardPane.getChildren().addAll(cards.getCardPlank(), allThings);
	cardPane.setMouseTransparent(false);

    }

    /**
     * Closes stock.
     * 
     */
    public void closeStock() {
	isOpen = false;
	allThings.getChildren().removeAll(resCards, development, information);
	cardPane.getChildren().removeAll(cards.getCardPlank());
	cardPane.setMouseTransparent(true);
    }

    /**
     * Returns true if open and false when closed.
     * 
     * @return True or false.
     */
    public boolean isOpen() {
	return this.isOpen;
    }

    /**
     * Updates info.
     * 
     * @param info
     *            To be updated.
     */
    public void updateInfo(String info) {
	information.setText(info);
    }

    @Override
    public void updateTranslation() {
	if (cards != null) {
	    cards.updateTranslation();
	}
	if (information != null) {
	    information.setText("");
	}
    }

    @Override
    public void updateStock(Player pl, Map<Resource, Integer> resources) {
	if (ClientRefrence.getThePlayer() == null || ClientRefrence.getThePlayer().equals(pl)) {
	    cards.updateStock(resources);
	}
    }

    @Override
    public void updateStock(Player pl, List<Card> cards) {
	if (ClientRefrence.getThePlayer() == null || ClientRefrence.getThePlayer().equals(pl)) {
	    devCards.getChildren().clear();
	    for (Card card : cards) {
		CardType type = card.getType();
		switch (type) {
		case FREE_STREETS:
		    devCards.getChildren().add(getRoadCard(card));
		    break;
		case INVENTION:
		    devCards.getChildren().add(getInventCard(card));
		    break;
		case KNIGHT:
		    devCards.getChildren().add(getKnightCard(card));
		    break;
		case MONOPOLY:
		    devCards.getChildren().add(getMonoCard(card));
		    break;
		case VICTORY:
		    VictoryCard victoryCard = (VictoryCard) card;
		    switch (victoryCard.getVictoryType()) {
		    case CHAPEL:
			devCards.getChildren().add(getCathCard(card));
			break;
		    case LIBARY:
			devCards.getChildren().add(getLibraryCard(card));
			break;
		    case MARKET:
			devCards.getChildren().add(getMarketCard(card));
			break;
		    case PARLIAMENT:
			devCards.getChildren().add(getParlemCard(card));
			break;
		    case UNIVERSITY:
			devCards.getChildren().add(getUniCard(card));
			break;
		    default:
			break;
		    }
		    break;
		}
	    }
	}
    }

    /**
     * Gets the resource for the cathedral card.
     * 
     * @return Image of the cathedral card.
     * @param card
     *            this is a cathedral card
     * 
     */
    public Node getCathCard(Card card) {
	ImageView cathCard;
	cathCard = new ImageView("img/cards/card_cathedral.png");
	cathCard.setFitHeight(100);
	cathCard.setFitWidth(80);
	cathCard.setOnMouseEntered(e -> updateInfo(TranslationManager.translate("cards.victory.info")));
	cathCard.setOnMouseExited(e -> updateInfo(""));
	cathCard.setOnMouseClicked(e -> {
	    view.getController().useCard(card);
	});
	return cathCard;
    }

    /**
     * Gets the resource for the cathedral card.
     * 
     * @param card
     *            this is a university card.
     * @return Image of the cathedral card.
     */
    public Node getUniCard(Card card) {
	ImageView uniCard;
	uniCard = new ImageView("img/cards/ont_uni.png");
	uniCard.setFitHeight(100);
	uniCard.setFitWidth(80);
	uniCard.setOnMouseEntered(e -> updateInfo(TranslationManager.translate("cards.victory.info")));
	uniCard.setOnMouseExited(e -> updateInfo(""));
	uniCard.setOnMouseClicked(e -> {
	    view.getController().useCard(card);
	});
	return uniCard;
    }

    /**
     * Gets the resource for the cathedral card.
     * 
     * @param card
     *            this is a market card.
     * @return Image of the cathedral card.
     */
    public Node getMarketCard(Card card) {
	ImageView marketCard;
	marketCard = new ImageView("img/cards/card_market.png");
	marketCard.setFitHeight(100);
	marketCard.setFitWidth(80);
	marketCard.setOnMouseEntered(e -> updateInfo(TranslationManager.translate("cards.victory.info")));
	marketCard.setOnMouseExited(e -> updateInfo(""));
	marketCard.setOnMouseClicked(e -> {
	    view.getController().useCard(card);
	});
	return marketCard;
    }

    /**
     * Gets the resource for the cathedral card.
     * 
     * @param card
     *            this is a parlemcard.
     * @return Image of the cathedral card.
     */
    public Node getParlemCard(Card card) {
	ImageView parlemCard;
	parlemCard = new ImageView("img/cards/ont_parlem.png");
	parlemCard.setFitHeight(100);
	parlemCard.setFitWidth(80);
	parlemCard.setOnMouseEntered(e -> updateInfo(TranslationManager.translate("cards.victory.info")));
	parlemCard.setOnMouseExited(e -> updateInfo(""));
	parlemCard.setOnMouseClicked(e -> {
	    view.getController().useCard(card);
	});
	return parlemCard;
    }

    /**
     * Gets the resource for the cathedral card.
     * 
     * @param card
     *            This is library card.
     * @return Image of the cathedral card.
     */
    public Node getLibraryCard(Card card) {
	ImageView libCard;
	libCard = new ImageView("img/cards/card_library.png");
	libCard.setFitHeight(100);
	libCard.setFitWidth(80);
	libCard.setOnMouseEntered(e -> updateInfo(TranslationManager.translate("cards.victory.info")));
	libCard.setOnMouseExited(e -> updateInfo(""));
	libCard.setOnMouseClicked(e -> {
	    view.getController().useCard(card);
	});
	return libCard;
    }

    /**
     * Gets the resource for the monopoly card.
     * 
     * @param card
     *            This is a monopoly card.
     * @return Image of the monopoly card.
     */
    public Node getMonoCard(Card card) {
	ImageView monoCard;
	monoCard = new ImageView("img/cards/card_monopoly.png");
	monoCard.setFitHeight(100);
	monoCard.setFitWidth(80);
	monoCard.setOnMouseEntered(e -> updateInfo(TranslationManager.translate("cards.monopoly.info")));
	monoCard.setOnMouseExited(e -> updateInfo(""));
	monoCard.setOnMouseClicked(e -> {
	    view.getController().useCard(card);
	});
	return monoCard;
    }

    /**
     * Gets the resource for the knight card.
     * 
     * @param card
     *            This is a knight card.
     * @return Image of the knight card.
     */
    public Node getKnightCard(Card card) {
	ImageView knightCard;
	knightCard = new ImageView("img/cards/card_knight.png");
	knightCard.setFitHeight(100);
	knightCard.setFitWidth(80);
	knightCard.setOnMouseEntered(e -> updateInfo(TranslationManager.translate("cards.knight.info")));
	knightCard.setOnMouseExited(e -> updateInfo(""));
	knightCard.setOnMouseClicked(e -> {
	    view.getController().useCard(card);
	});
	return knightCard;
    }

    /**
     * Gets the resource for the invention card.
     * 
     * @param card
     *            This is an invention card.
     * @return Image of the invention card.
     */
    public Node getInventCard(Card card) {
	ImageView inventCard;
	inventCard = new ImageView("img/cards/card_invention.png");
	inventCard.setFitHeight(100);
	inventCard.setFitWidth(80);
	inventCard.setOnMouseEntered(e -> updateInfo(TranslationManager.translate("cards.invent.info")));
	inventCard.setOnMouseExited(e -> updateInfo(""));
	inventCard.setOnMouseClicked(e -> {
	    view.getController().useCard(card);
	});
	return inventCard;
    }

    /**
     * Gets the resource for the road card.
     * 
     * @param card
     *            This is a road card.
     * @return Image of the road card.
     */
    public Node getRoadCard(Card card) {
	ImageView roadCard;
	roadCard = new ImageView("img/cards/card_road.png");
	roadCard.setFitHeight(100);
	roadCard.setFitWidth(80);
	roadCard.setOnMouseEntered(e -> updateInfo(TranslationManager.translate("cards.road.info")));
	roadCard.setOnMouseExited(e -> updateInfo(""));
	roadCard.setOnMouseClicked(e -> {
	    view.getController().useCard(card);
	});
	return roadCard;
    }

}
