package nl.groep4.kvc.client.view.pane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.controller.ClientRefrence;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.KvCText;
import nl.groep4.kvc.client.view.elements.ResourceCard;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.Card;
import nl.groep4.kvc.common.interfaces.NotCloseable;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.UpdateStock;

/**
 * The pane when a monopoly card (development card) is used.
 * 
 * @author Lisa
 * @version 1.2
 */
public class MonopolyPane implements PaneHolder, NotCloseable, UpdateStock {
    private Font font = new Font(ViewMaster.FONT.getName(), 30);
    private Font font2 = new Font(ViewMaster.FONT.getName(), 50);

    private Text choice = new Text(TranslationManager.translate("monopoly.text.choice"));
    private Text monopoly = new Text(TranslationManager.translate("monopoly.text.monopoly"));
    private Text woodAmount = new KvCText("0");
    private Text stoneAmount = new KvCText("0");
    private Text woolAmount = new KvCText("0");
    private Text wheatAmount = new KvCText("0");
    private Text oreAmount = new KvCText("0");

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

	woodAmount.setFont(ViewMaster.TITLE_FONT);
	stoneAmount.setFont(ViewMaster.TITLE_FONT);
	woolAmount.setFont(ViewMaster.TITLE_FONT);
	wheatAmount.setFont(ViewMaster.TITLE_FONT);
	oreAmount.setFont(ViewMaster.TITLE_FONT);

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
	woodText.getChildren().addAll(woodAmount, cards.getWoodName());
	oreText.setAlignment(Pos.CENTER);
	oreText.getChildren().addAll(oreAmount, cards.getOreName());
	wheatText.setAlignment(Pos.CENTER);
	wheatText.getChildren().addAll(wheatAmount, cards.getWheatName());
	stoneText.setAlignment(Pos.CENTER);
	stoneText.getChildren().addAll(stoneAmount, cards.getStoneName());
	woolText.setAlignment(Pos.CENTER);
	woolText.getChildren().addAll(woolAmount, cards.getWoolName());

	updateStock(ClientRefrence.getThePlayer(), new HashMap<>());

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
    public void updateConfig() {
	choice.setText(TranslationManager.translate("monopoly.text.choice"));
	monopoly.setText(TranslationManager.translate("monopoly.text.monopoly"));

    }

    @Override
    public void updateStock(Player pl, Map<Resource, Integer> resources) {

	int woodam = 0;
	int stoneam = 0;
	int woolam = 0;
	int wheatam = 0;
	int oream = 0;

	for (Resource resourceType : Resource.values()) {
	    for (Player player : scenemap.getController().getPlayers()) {
		if (player.equals(ClientRefrence.getThePlayer())) {
		    continue;
		}
		try {
		    switch (resourceType) {
		    case BRICK:
			stoneam += player.getResourceAmount(resourceType);
			break;
		    case ORE:
			oream += player.getResourceAmount(resourceType);
			break;
		    case WHEAT:
			wheatam += player.getResourceAmount(resourceType);
			break;
		    case WOOD:
			woodam += player.getResourceAmount(resourceType);
			break;
		    case WOOL:
			woolam += player.getResourceAmount(resourceType);
			break;
		    }
		} catch (Exception e) {

		    e.printStackTrace();
		}
	    }
	}
	woodAmount.setText(Integer.toString(woodam));
	stoneAmount.setText(Integer.toString(stoneam));
	woolAmount.setText(Integer.toString(woolam));
	wheatAmount.setText(Integer.toString(wheatam));
	oreAmount.setText(Integer.toString(oream));
    }

    @Override
    public void updateStock(Player pl, List<Card> cards) {
    }
}
