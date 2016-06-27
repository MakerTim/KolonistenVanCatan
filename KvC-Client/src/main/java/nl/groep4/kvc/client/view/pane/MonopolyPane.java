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
 * The pane when an monopoly card (development card) is used.
 * 
 * @author Lisa
 * @version 1.1
 */
public class MonopolyPane implements PaneHolder, NotCloseable {
    private Font font = new Font(ViewMaster.FONT.getName(), 30);
    private Font font2 = new Font(ViewMaster.FONT.getName(), 50);

    private Text choice = new Text(TranslationManager.translate("monopoly.text.choice"));
    private Text monopoly = new Text(TranslationManager.translate("monopoly.text.monopoly"));
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
     * Development card settings.
     * 
     * @param sceneMap
     *            The view of the map.
     */
    public MonopolyPane(SceneMap sceneMap) {
	this.scenemap = sceneMap;
    }

    @Override
    public Pane getPane() {
	cards = new ResourceCard();
	StackPane monopolypane = new StackPane();

	choice.setFont(font);
	monopoly.setFont(font2);
	choice.setFill(Color.WHITE);
	monopoly.setFill(Color.WHITE);
	choice.setStroke(Color.BLACK);
	monopoly.setStroke(Color.BLACK);

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

	wood.setOnMouseClicked(click -> onWoodClick());
	wheat.setOnMouseClicked(click -> onWheatClick());
	ore.setOnMouseClicked(click -> onOreClick());
	stone.setOnMouseClicked(click -> onBrickClick());
	wool.setOnMouseClicked(click -> onWoolClick());

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
	allThings.getChildren().addAll(monopoly, choice, resCards);
	monopolypane.getChildren().addAll(background, allThings);

	return monopolypane;
    }

    /**
     * When clicked on wood, the player gets all the wood cards of every player.
     * 
     */
    public void onWoodClick() {
	scenemap.getController().targetMonopoly(Resource.WOOD);
    }

    /**
     * When clicked on brick, the player gets all the brick cards of every
     * player.
     * 
     */
    public void onBrickClick() {
	scenemap.getController().targetMonopoly(Resource.BRICK);
    }

    /**
     * When clicked on wool, the player gets all the wool cards of every player.
     * 
     */
    public void onWoolClick() {
	scenemap.getController().targetMonopoly(Resource.WOOL);
    }

    /**
     * When clicked on wheat, the player gets all the wheat cards of every
     * player.
     * 
     */
    public void onWheatClick() {
	scenemap.getController().targetMonopoly(Resource.WHEAT);
    }

    /**
     * When clicked on ore, the player gets all the ore cards of every player.
     * 
     */
    public void onOreClick() {
	scenemap.getController().targetMonopoly(Resource.ORE);
    }

    @Override
    public void updateTranslation() {
	choice.setText(TranslationManager.translate("monopoly.text.choice"));
	monopoly.setText(TranslationManager.translate("monopoly.text.monopoly"));

    }

}
