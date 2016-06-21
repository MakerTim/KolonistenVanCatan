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
import nl.groep4.kvc.client.view.elements.ResourceCardUtil;

/**
 * The pane when an inventioncard (developmentcard) is used.
 * 
 * @author Lisa
 * @version 1.0
 */
public class InventionPane implements PaneHolder {
    private Font font = new Font(ViewMaster.FONT.getName(), 30);
    private Font font2 = new Font(ViewMaster.FONT.getName(), 50);

    private Text choice = new Text(TranslationManager.translate("invention.text.choice"));
    private Text invention = new Text(TranslationManager.translate("invention.text.invention"));
    private ResourceCardUtil cards;
    private VBox woodText;
    private VBox oreText;
    private VBox stoneText;
    private VBox woolText;
    private VBox wheatText;
    private HBox resCards;
    private VBox allThings;

    @Override
    public Pane getPane() {
	cards = new ResourceCardUtil();
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
	woodText.getChildren().addAll(cards.getWoodText(), cards.getWoodName());
	oreText.setAlignment(Pos.CENTER);
	oreText.getChildren().addAll(cards.getOreText(), cards.getOreName());
	wheatText.setAlignment(Pos.CENTER);
	wheatText.getChildren().addAll(cards.getWheatText(), cards.getWheatName());
	stoneText.setAlignment(Pos.CENTER);
	stoneText.getChildren().addAll(cards.getStoneText(), cards.getStoneName());
	woolText.setAlignment(Pos.CENTER);
	woolText.getChildren().addAll(cards.getWoolText(), cards.getWoolName());

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

    public void onWoodClick() {

    }

    public void onBrickClick() {

    }

    public void onWoolClick() {

    }

    public void onWheatClick() {

    }

    public void onOreClick() {

    }

    @Override
    public void updateTranslation() {
	choice.setText(TranslationManager.translate("invention.text.choice"));
	invention.setText(TranslationManager.translate("invention.text.invention"));

    }
}
