package nl.groep4.kvc.client.view.pane;

import java.rmi.RemoteException;
import java.util.EnumMap;
import java.util.Map.Entry;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
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
import nl.groep4.kvc.client.view.elements.KvCText;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.UpdateCosts;

/**
 * The BuyPane, where you can buy a development card.
 * 
 * @author Lisa
 * @version 1.1
 */
public class BuyPane implements PaneHolder, UpdateCosts {
    private Font font = new Font(ViewMaster.FONT.getName(), 30);

    private SceneMap sceneMap;

    private MenuButton yes = new MenuButton(425, 500, TranslationManager.translate("buycard.decision.yes"));
    private MenuButton no = new MenuButton(425, 500, TranslationManager.translate("buycard.decision.no"));

    private Text buy = new Text(TranslationManager.translate("buycard.msg.buycard"));
    private Text brickAmount = new KvCText("0");
    private Text woolAmount = new KvCText("0");
    private Text wheatAmount = new KvCText("0");
    private Text oreAmount = new KvCText("0");
    private Text woodAmount = new KvCText("0");
    private Text brick = new KvCText(TranslationManager.translate("buypane.text.brick"));
    private Text wool = new KvCText(TranslationManager.translate("buypane.text.wool"));
    private Text wheat = new KvCText(TranslationManager.translate("buypane.text.wheat"));
    private Text ore = new KvCText(TranslationManager.translate("buypane.text.ore"));
    private Text wood = new KvCText(TranslationManager.translate("buypane.text.wood"));
    private Text resources = new KvCText(TranslationManager.translate("buypane.text.resources"));
    private Text cards = new KvCText(TranslationManager.translate("buypane.text.cards"));

    private HBox hboxPrices;
    private VBox vboxPrices;
    private VBox vboxWheat;
    private VBox vboxWood;
    private VBox vboxWool;
    private VBox vboxBrick;
    private VBox vboxOre;

    /**
     * Sets up the BuyPane.
     * 
     * @param sceneMap
     *            The view of the map.
     */
    public BuyPane(SceneMap sceneMap) {
	this.sceneMap = sceneMap;
    }

    @Override
    public Pane getPane() {
	StackPane buypane = new StackPane();
	BorderPane border = new BorderPane();

	HBox hbox = new HBox();

	yes = new MenuButton(425, 500, TranslationManager.translate("buycard.decision.yes"));
	no = new MenuButton(425, 500, TranslationManager.translate("buycard.decision.no"));

	vboxPrices = new VBox();
	vboxWheat = new VBox();
	vboxWood = new VBox();
	vboxWool = new VBox();
	vboxBrick = new VBox();
	vboxOre = new VBox();

	hboxPrices = new HBox();

	buy.setFont(font);
	buy.setFill(Color.WHITE);
	buy.setStroke(Color.BLACK);

	vboxPrices.getChildren().addAll(resources, cards);
	vboxWheat.getChildren().addAll(wheat, wheatAmount);
	vboxWood.getChildren().addAll(wood, woodAmount);
	vboxWool.getChildren().addAll(wool, woolAmount);
	vboxBrick.getChildren().addAll(brick, brickAmount);
	vboxOre.getChildren().addAll(ore, oreAmount);

	hboxPrices.getChildren().addAll(vboxPrices, vboxWood, vboxBrick, vboxWool, vboxWheat, vboxOre);
	hboxPrices.setAlignment(Pos.CENTER);
	hboxPrices.setPadding(new Insets(200, 0, 0, 0));
	hboxPrices.setSpacing(20);
	hboxPrices.setPickOnBounds(false);

	hbox.getChildren().addAll(yes, no);
	hbox.setAlignment(Pos.CENTER);
	hbox.setPadding(new Insets(0, 0, 160, 0));
	hbox.setSpacing(150);
	hbox.setPickOnBounds(false);

	border.setTop(hboxPrices);
	border.setCenter(buy);
	border.setBottom(hbox);
	border.setPickOnBounds(false);

	Node background = SceneUtil.getGamePane();

	buypane.getChildren().addAll(background, border);
	yes.registerClick(() -> onYesClick());
	no.registerClick(() -> onNoClick());

	return buypane;
    }

    private void onYesClick() {
	sceneMap.getController().buyCard();
    }

    private void onNoClick() {
	sceneMap.closeOverlay();
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
	buy.setText(TranslationManager.translate("buycard.msg.buycard"));
	no.updateText(TranslationManager.translate("buycard.decision.no"));
	yes.updateText(TranslationManager.translate("buycard.decision.yes"));
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
