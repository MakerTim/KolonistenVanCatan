package nl.groep4.kvc.client.view.pane;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.ResourceCard;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.NotCloseable;

/**
 * The pane when an invention card (development card) is used.
 * 
 * @author Lisa
 * @version 1.1
 */
public class InventionPane implements PaneHolder, NotCloseable {
    private Font font = new Font(ViewMaster.FONT.getName(), 30);
    private Font font2 = new Font(ViewMaster.FONT.getName(), 50);

    private Text choice = new Text(TranslationManager.translate("invention.text.choice"));
    private Text invention = new Text(TranslationManager.translate("invention.text.invention"));
    private ResourceCard cards;
    private VBox woodText;
    private VBox oreText;
    private VBox stoneText;
    private VBox woolText;
    private VBox wheatText;
    private HBox resCards;
    private VBox allThings;

    private SceneMap scenemap;

    /**
     * Sets scenemap.
     * 
     * @param sceneMap
     *            View of the map.
     */
    public InventionPane(SceneMap sceneMap) {
	this.scenemap = sceneMap;
    }

    @Override
    public Pane getPane() {
	cards = new ResourceCard();
	StackPane inventionpane = new StackPane();

	choice.setFont(font);
	invention.setFont(font2);
	choice.setFill(Color.WHITE);
	invention.setFill(Color.WHITE);
	choice.setStroke(Color.BLACK);
	invention.setStroke(Color.BLACK);

	woodText = new VBox();
	wheatText = new VBox();
	oreText = new VBox();
	woolText = new VBox();
	stoneText = new VBox();
	allThings = new VBox();
	resCards = new HBox();

	StackPane wood = new StackPane();
	StackPane wheat = new StackPane();
	StackPane ore = new StackPane();
	StackPane wool = new StackPane();
	StackPane stone = new StackPane();

	woodText.setAlignment(Pos.CENTER);
	woodText.getChildren().add(cards.getWoodName());
	oreText.setAlignment(Pos.CENTER);
	oreText.getChildren().add(cards.getOreName());
	wheatText.setAlignment(Pos.CENTER);
	wheatText.getChildren().add(cards.getWheatName());
	stoneText.setAlignment(Pos.CENTER);
	stoneText.getChildren().add(cards.getStoneName());
	woolText.setAlignment(Pos.CENTER);
	woolText.getChildren().add(cards.getWoolName());

	wood.setOnMouseClicked(click -> onWoodClick());
	wheat.setOnMouseClicked(click -> onWheatClick());
	ore.setOnMouseClicked(click -> onOreClick());
	stone.setOnMouseClicked(click -> onBrickClick());
	wool.setOnMouseClicked(click -> onWoolClick());

	wood.getChildren().addAll(cards.getWoodCard(), woodText);
	wheat.getChildren().addAll(cards.getWheatCard(), wheatText);
	ore.getChildren().addAll(cards.getOreCard(), oreText);
	stone.getChildren().addAll(cards.getStoneCard(), stoneText);
	wool.getChildren().addAll(cards.getWoolCard(), woolText);

	Node background = SceneUtil.getGamePane();

	resCards.getChildren().addAll(wood, stone, wool, wheat, ore);
	resCards.setAlignment(Pos.CENTER);
	allThings.setAlignment(Pos.CENTER);
	allThings.setSpacing(30);
	allThings.getChildren().addAll(invention, choice, resCards);
	inventionpane.getChildren().addAll(background, allThings);

	return inventionpane;
    }

    /**
     * when clicked on wood, two cards will be given to the player
     */
    public void onWoodClick() {
	scenemap.getController().targetInvention(Resource.WOOD);
    }

    /**
     * when clicked on brick, two cards will be given to the player
     */
    public void onBrickClick() {
	scenemap.getController().targetInvention(Resource.BRICK);
    }

    /**
     * when clicked on wool, two cards will be given to the player
     */
    public void onWoolClick() {
	scenemap.getController().targetInvention(Resource.WOOL);
    }

    /**
     * when clicked on wheat, two cards will be given to the player
     */
    public void onWheatClick() {
	scenemap.getController().targetInvention(Resource.WHEAT);
    }

    /**
     * when clicked on ore, two cards will be given to the player
     */
    public void onOreClick() {
	scenemap.getController().targetInvention(Resource.ORE);
    }

    @Override
    public void updateTranslation() {
	choice.setText(TranslationManager.translate("invention.text.choice"));
	invention.setText(TranslationManager.translate("invention.text.invention"));

    }
}