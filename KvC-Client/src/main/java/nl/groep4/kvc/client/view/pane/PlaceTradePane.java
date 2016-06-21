package nl.groep4.kvc.client.view.pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import nl.groep4.kvc.client.controller.ClientRefrence;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.elements.KvCText;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.enumeration.Resource;

public class PlaceTradePane implements PaneHolder {

    private MenuButton place = new MenuButton(425, 400, TranslationManager.translate("trade.button.place"));
    private MenuButton back = new MenuButton(425, 400, TranslationManager.translate("trade.button.back"));

    private KvCText search = new KvCText(TranslationManager.translate("trade.text.search"));
    private KvCText offer = new KvCText(TranslationManager.translate("trade.text.offer"));
    private KvCText resources = new KvCText(TranslationManager.translate("trade.text.resources"));
    private KvCText wheat = new KvCText(TranslationManager.translate("trade.text.wheat"));
    private KvCText wood = new KvCText(TranslationManager.translate("trade.text.wood"));
    private KvCText wool = new KvCText(TranslationManager.translate("trade.text.wool"));
    private KvCText brick = new KvCText(TranslationManager.translate("trade.text.brick"));
    private KvCText ore = new KvCText(TranslationManager.translate("trade.text.ore"));

    private VBox vboxtrade;
    private VBox vboxwheat;
    private VBox vboxwood;
    private VBox vboxwool;
    private VBox vboxbrick;
    private VBox vboxore;
    private VBox vbox;

    private HBox hbox;
    private HBox hboxbuttons;

    private SceneMap scenemap;

    public PlaceTradePane(SceneMap scenemap) {
	this.scenemap = scenemap;
    }

    @Override
    public Pane getPane() {
	StackPane placetradepane = new StackPane();

	SpinnerValueFactory spinOfferWheat;
	SpinnerValueFactory spinOfferWood;
	SpinnerValueFactory spinOfferWool;
	SpinnerValueFactory spinOfferBrick;
	SpinnerValueFactory spinOfferOre;
	SpinnerValueFactory spinSearchWheat = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
	SpinnerValueFactory spinSearchWood = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
	SpinnerValueFactory spinSearchWool = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
	SpinnerValueFactory spinSearchBrick = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
	SpinnerValueFactory spinSearchOre = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
	try {
	    spinOfferWheat = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
		    ClientRefrence.getThePlayer().getResourceAmount(Resource.WHEAT));
	    spinOfferWood = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
		    ClientRefrence.getThePlayer().getResourceAmount(Resource.WOOD));
	    spinOfferWool = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
		    ClientRefrence.getThePlayer().getResourceAmount(Resource.WOOL));
	    spinOfferBrick = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
		    ClientRefrence.getThePlayer().getResourceAmount(Resource.BRICK));
	    spinOfferOre = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
		    ClientRefrence.getThePlayer().getResourceAmount(Resource.ORE));

	} catch (Exception ex) {
	    spinOfferWheat = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
	    spinOfferWood = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
	    spinOfferWool = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
	    spinOfferBrick = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
	    spinOfferOre = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
	}

	Spinner<Integer> spinnerOfferWheat = new Spinner<>(spinOfferWheat);
	Spinner<Integer> spinnerOfferWood = new Spinner<>(spinOfferWood);
	Spinner<Integer> spinnerOfferWool = new Spinner<>(spinOfferWool);
	Spinner<Integer> spinnerOfferBrick = new Spinner<>(spinOfferBrick);
	Spinner<Integer> spinnerOfferOre = new Spinner<>(spinOfferOre);
	Spinner<Integer> spinnerSearchWheat = new Spinner<>(spinSearchWheat);
	Spinner<Integer> spinnerSearchWood = new Spinner<>(spinSearchWood);
	Spinner<Integer> spinnerSearchWool = new Spinner<>(spinSearchWool);
	Spinner<Integer> spinnerSearchBrick = new Spinner<>(spinSearchBrick);
	Spinner<Integer> spinnerSearchOre = new Spinner<>(spinSearchOre);

	spinnerOfferWheat.setEditable(true);
	spinnerOfferWood.setEditable(true);
	spinnerOfferWool.setEditable(true);
	spinnerOfferBrick.setEditable(true);
	spinnerOfferOre.setEditable(true);
	spinnerSearchWheat.setEditable(true);
	spinnerSearchWood.setEditable(true);
	spinnerSearchWool.setEditable(true);
	spinnerSearchBrick.setEditable(true);
	spinnerSearchOre.setEditable(true);

	spinnerOfferWheat.setMaxWidth(80);
	spinnerOfferWood.setMaxWidth(80);
	spinnerOfferWool.setMaxWidth(80);
	spinnerOfferBrick.setMaxWidth(80);
	spinnerOfferOre.setMaxWidth(80);
	spinnerSearchWheat.setMaxWidth(80);
	spinnerSearchWood.setMaxWidth(80);
	spinnerSearchWool.setMaxWidth(80);
	spinnerSearchBrick.setMaxWidth(80);
	spinnerSearchOre.setMaxWidth(80);

	vboxtrade = new VBox();
	vboxwheat = new VBox();
	vboxwood = new VBox();
	vboxwool = new VBox();
	vboxbrick = new VBox();
	vboxore = new VBox();
	vbox = new VBox();

	hboxbuttons = new HBox();
	hbox = new HBox();

	Node background = SceneUtil.getGamePane();

	vboxtrade.getChildren().addAll(resources, search, offer);
	vboxwheat.getChildren().addAll(wheat, spinnerSearchWheat, spinnerOfferWheat);
	vboxwood.getChildren().addAll(wood, spinnerSearchWood, spinnerOfferWood);
	vboxwool.getChildren().addAll(wool, spinnerSearchWool, spinnerOfferWool);
	vboxbrick.getChildren().addAll(brick, spinnerSearchBrick, spinnerOfferBrick);
	vboxore.getChildren().addAll(ore, spinnerSearchOre, spinnerOfferOre);

	vboxtrade.setSpacing(15);
	vboxwheat.setSpacing(15);
	vboxwood.setSpacing(15);
	vboxwool.setSpacing(15);
	vboxbrick.setSpacing(15);
	vboxore.setSpacing(15);

	hboxbuttons.setAlignment(Pos.CENTER);
	hboxbuttons.setPadding(new Insets(160, 0, 0, 200));

	hbox.setAlignment(Pos.CENTER);
	hbox.setSpacing(20);
	hbox.setPadding(new Insets(230, 0, 0, 0));

	hbox.getChildren().addAll(vboxtrade, vboxwheat, vboxwood, vboxwool, vboxbrick, vboxore);
	hboxbuttons.getChildren().addAll(place, back);
	vbox.getChildren().addAll(hbox, hboxbuttons);
	placetradepane.getChildren().addAll(background, vbox);

	back.setOnMouseClicked(klick -> onBackClick());

	return placetradepane;
    }

    public void onBackClick() {
	scenemap.openTradePane();
    }

    @Override
    public void updateTranslation() {
	place.updateText(TranslationManager.translate("trade.button.place"));
	back.updateText(TranslationManager.translate("trade.button.back"));
	search.setText(TranslationManager.translate("trade.text.search"));
	offer.setText(TranslationManager.translate("trade.text.offer"));
	resources.setText(TranslationManager.translate("trade.text.resources"));
	wheat.setText(TranslationManager.translate("trade.text.wheat"));
	wood.setText(TranslationManager.translate("trade.text.wood"));
	wool.setText(TranslationManager.translate("trade.text.wool"));
	brick.setText(TranslationManager.translate("trade.text.brick"));
	ore.setText(TranslationManager.translate("trade.text.ore"));

    }

}
