package nl.groep4.kvc.client.view.pane;

import java.rmi.RemoteException;
import java.util.EnumMap;
import java.util.Map.Entry;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.UpdateCosts;

public class BuyPane extends Application implements PaneHolder, UpdateCosts {
    private Font font = new Font(ViewMaster.FONT.getName(), 30);

    private MenuButton yes;
    private MenuButton no;

    private Text buy;
    private Text brickAmount;
    private Text woolAmount;
    private Text wheatAmount;
    private Text oreAmount;
    private Text woodAmount;
    private Text brick;
    private Text wool;
    private Text wheat;
    private Text ore;
    private Text wood;
    private Text resources;
    private Text cards;

    private HBox hboxPrices;
    private VBox vboxPrices;
    private VBox vboxWheat;
    private VBox vboxWood;
    private VBox vboxWool;
    private VBox vboxBrick;
    private VBox vboxOre;

    @Override
    public void start(Stage primaryStage) throws Exception {

	Scene scene = new Scene(getPane());
	primaryStage.setScene(scene);
	primaryStage.show();

    }

    @Override
    public Pane getPane() {
	StackPane buypane = new StackPane();
	BorderPane border = new BorderPane();
	HBox hbox = new HBox();

	yes = new MenuButton(425, 500, TranslationManager.translate("buycard.decision.yes"));
	no = new MenuButton(425, 500, TranslationManager.translate("buycard.decision.no"));
	buy = new Text(TranslationManager.translate("buycard.msg.buycard"));

	yes.setFont(ViewMaster.FONT);
	no.setFont(ViewMaster.FONT);
	buy.setFont(font);
	buy.setFill(Color.WHITE);
	buy.setStroke(Color.BLACK);

	brick = new Text(TranslationManager.translate("buypane.text.brick"));
	wool = new Text(TranslationManager.translate("buypane.text.wool"));
	wheat = new Text(TranslationManager.translate("buypane.text.wheat"));
	ore = new Text(TranslationManager.translate("buypane.text.ore"));
	wood = new Text(TranslationManager.translate("buypane.text.wood"));
	resources = new Text(TranslationManager.translate("buypane.text.resources"));
	cards = new Text(TranslationManager.translate("buypane.text.cards"));

	brickAmount = new Text("0");
	woolAmount = new Text("0");
	wheatAmount = new Text("0");
	oreAmount = new Text("0");
	woodAmount = new Text("0");

	brick.setFont(ViewMaster.FONT);
	wool.setFont(ViewMaster.FONT);
	wheat.setFont(ViewMaster.FONT);
	ore.setFont(ViewMaster.FONT);
	wood.setFont(ViewMaster.FONT);
	resources.setFont(ViewMaster.FONT);
	cards.setFont(ViewMaster.FONT);

	brickAmount.setFont(ViewMaster.FONT);
	woolAmount.setFont(ViewMaster.FONT);
	wheatAmount.setFont(ViewMaster.FONT);
	oreAmount.setFont(ViewMaster.FONT);
	woodAmount.setFont(ViewMaster.FONT);

	brick.setFill(Color.WHITE);
	wool.setFill(Color.WHITE);
	wheat.setFill(Color.WHITE);
	ore.setFill(Color.WHITE);
	wood.setFill(Color.WHITE);
	resources.setFill(Color.WHITE);
	cards.setFill(Color.WHITE);

	brickAmount.setFill(Color.WHITE);
	woolAmount.setFill(Color.WHITE);
	wheatAmount.setFill(Color.WHITE);
	oreAmount.setFill(Color.WHITE);
	woodAmount.setFill(Color.WHITE);

	brick.setStroke(Color.BLACK);
	wool.setStroke(Color.BLACK);
	wheat.setStroke(Color.BLACK);
	ore.setStroke(Color.BLACK);
	wood.setStroke(Color.BLACK);
	resources.setStroke(Color.BLACK);
	cards.setStroke(Color.BLACK);

	brickAmount.setStroke(Color.BLACK);
	woolAmount.setStroke(Color.BLACK);
	wheatAmount.setStroke(Color.BLACK);
	oreAmount.setStroke(Color.BLACK);
	woodAmount.setStroke(Color.BLACK);

	vboxPrices = new VBox();
	vboxWheat = new VBox();
	vboxWood = new VBox();
	vboxWool = new VBox();
	vboxBrick = new VBox();
	vboxOre = new VBox();

	hboxPrices = new HBox();

	vboxPrices.getChildren().addAll(resources, cards);
	vboxWheat.getChildren().addAll(wheat, wheatAmount);
	vboxWood.getChildren().addAll(wood, woodAmount);
	vboxWool.getChildren().addAll(wool, woolAmount);
	vboxBrick.getChildren().addAll(brick, brickAmount);
	vboxOre.getChildren().addAll(ore, oreAmount);

	hboxPrices.getChildren().addAll(vboxPrices, vboxWheat, vboxWood, vboxWool, vboxBrick, vboxOre);
	hboxPrices.setAlignment(Pos.CENTER);
	hboxPrices.setPadding(new Insets(200, 0, 0, 0));
	hboxPrices.setSpacing(20);

	hbox.setPadding(new Insets(0, 0, 220, 0));
	hbox.setSpacing(150);
	hbox.setAlignment(Pos.CENTER);
	hbox.getChildren().addAll(yes, no);

	border.setTop(hboxPrices);
	border.setCenter(buy);
	border.setBottom(hbox);

	Node background = SceneUtil.getGamePane();

	buypane.getChildren().addAll(background, border);

	return buypane;
    }

    @Override
    public void updateTranslation() {

	brick.setText(TranslationManager.translate("buypane.text.brick"));
	wool.setText(TranslationManager.translate("buypane.text.wool"));
	wheat.setText(TranslationManager.translate("buypane.text.wheat"));
	ore.setText(TranslationManager.translate("buypane.text.ore"));
	wood.setText(TranslationManager.translate("buypane.text.wood"));
	resources.setText(TranslationManager.translate("buypane.text.resources"));
	cards.setText(TranslationManager.translate("buypane.text.cards"));
    }

    public static void main(String[] args) {
	launch(args);
    }

    @Override
    public void updateCardCosts(EnumMap<Resource, Integer> resources) throws RemoteException {

	for (Entry<Resource, Integer> resource : resources.entrySet()) {
	    Resource res = resource.getKey();
	    String amount = resource.getValue().toString();
	    switch (res) {
	    case BRICK:
		brickAmount.setText(amount);
		break;
	    case ORE:
		oreAmount.setText(amount);
		break;
	    case WHEAT:
		wheatAmount.setText(amount);
		break;
	    case WOOD:
		woodAmount.setText(amount);
		break;
	    case WOOL:
		woolAmount.setText(amount);
		break;
	    }
	}
    }

    @Override
    public void updateCityCosts(EnumMap<Resource, Integer> resources) throws RemoteException {
    }

    @Override
    public void updateStreetCosts(EnumMap<Resource, Integer> resources) throws RemoteException {
    }

    @Override
    public void updateVillageCosts(EnumMap<Resource, Integer> resources) throws RemoteException {
    }

}
