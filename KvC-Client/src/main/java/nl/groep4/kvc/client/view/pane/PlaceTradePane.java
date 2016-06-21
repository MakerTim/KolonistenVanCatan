package nl.groep4.kvc.client.view.pane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.groep4.kvc.client.controller.ClientRefrence;
import nl.groep4.kvc.client.util.SceneUtil;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.elements.KvCText;
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.common.enumeration.Resource;

public class PlaceTradePane extends Application implements PaneHolder {

    private MenuButton place;
    private MenuButton back;

    private KvCText give;
    private KvCText receive;
    private KvCText resources;
    private KvCText wheat;
    private KvCText wood;
    private KvCText wool;
    private KvCText brick;
    private KvCText ore;

    private TextField givewheat;
    private TextField givewood;
    private TextField givewool;
    private TextField givebrick;
    private TextField giveore;
    private TextField receivewheat;
    private TextField receivewood;
    private TextField receivewool;
    private TextField receivebrick;
    private TextField receiveore;

    private VBox vboxtrade;
    private VBox vboxwheat;
    private VBox vboxwood;
    private VBox vboxwool;
    private VBox vboxbrick;
    private VBox vboxore;
    private VBox vbox;

    private HBox hbox;
    private HBox hboxbuttons;

    @Override
    public void start(Stage stage) throws Exception {
	// TODO Auto-generated method stub

	place = new MenuButton(425, 400, TranslationManager.translate("trade.button.place"));
	back = new MenuButton(425, 400, TranslationManager.translate("trade.button.back"));

	StackPane placetradepane = new StackPane();

	give = new KvCText(TranslationManager.translate("trade.text.give"));
	receive = new KvCText(TranslationManager.translate("trade.text.receive"));
	resources = new KvCText(TranslationManager.translate("trade.text.resources"));
	wheat = new KvCText(TranslationManager.translate("trade.text.wheat"));
	wood = new KvCText(TranslationManager.translate("trade.text.wood"));
	wool = new KvCText(TranslationManager.translate("trade.text.wool"));
	brick = new KvCText(TranslationManager.translate("trade.text.brick"));
	ore = new KvCText(TranslationManager.translate("trade.text.ore"));

	givewheat = new TextField();
	givewood = new TextField();
	givewool = new TextField();
	givebrick = new TextField();
	giveore = new TextField();
	receivewheat = new TextField();
	receivewood = new TextField();
	receivewool = new TextField();
	receivebrick = new TextField();
	receiveore = new TextField();

	SpinnerValueFactory spinWheat = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
		ClientRefrence.getThePlayer().getResourceAmount(Resource.WHEAT));
	SpinnerValueFactory spinWood = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
		ClientRefrence.getThePlayer().getResourceAmount(Resource.WOOD));
	SpinnerValueFactory spinWool = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
		ClientRefrence.getThePlayer().getResourceAmount(Resource.WOOL));
	SpinnerValueFactory spinBrick = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
		ClientRefrence.getThePlayer().getResourceAmount(Resource.BRICK));
	SpinnerValueFactory spinOre = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,
		ClientRefrence.getThePlayer().getResourceAmount(Resource.ORE));

	Spinner spinnerWheat = new Spinner<Integer>(spinBrick);
	Spinner spinnerwood = new Spinner<Integer>(spinWheat);
	Spinner spinnerWhool = new Spinner<Integer>(spinWood);
	Spinner spinnerBrick = new Spinner<Integer>(spinWool);
	Spinner spinnerOre = new Spinner<Integer>(spinOre);

	givewood.setMaxWidth(60);
	givewool.setMaxWidth(60);
	givebrick.setMaxWidth(60);
	giveore.setMaxWidth(60);
	receivewheat.setMaxWidth(60);
	receivewood.setMaxWidth(60);
	receivewool.setMaxWidth(60);
	receivebrick.setMaxWidth(60);
	receiveore.setMaxWidth(60);

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

	vboxtrade.getChildren().addAll(resources, give, receive);
	vboxwheat.getChildren().addAll(wheat, givewheat, receivewheat);
	vboxwood.getChildren().addAll(wood, givewood, receivewood);
	vboxwool.getChildren().addAll(wool, givewool, receivewool);
	vboxbrick.getChildren().addAll(brick, givebrick, receivebrick);
	vboxore.getChildren().addAll(ore, giveore, receiveore);

	vboxtrade.setSpacing(15);
	vboxwheat.setSpacing(15);
	vboxwood.setSpacing(15);
	vboxwool.setSpacing(15);
	vboxbrick.setSpacing(15);
	vboxore.setSpacing(15);

	hboxbuttons.setAlignment(Pos.CENTER);
	hboxbuttons.setPadding(new Insets(120, 0, 0, 200));

	hbox.setAlignment(Pos.CENTER);
	hbox.setSpacing(20);
	hbox.setPadding(new Insets(150, 0, 0, 0));

	hbox.getChildren().addAll(vboxtrade, vboxwheat, vboxwood, vboxwool, vboxbrick, vboxore);
	hboxbuttons.getChildren().addAll(place, back);
	vbox.getChildren().addAll(hbox, hboxbuttons);
	placetradepane.getChildren().addAll(background, vbox);

	Scene scene = new Scene(placetradepane);
	stage.setScene(scene);
	stage.show();

    }

    @Override
    public Pane getPane() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void updateTranslation() {
	// TODO Auto-generated method stub

    }

    public static void main(String[] args) {
	launch(args);
    }

}
