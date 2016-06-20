package nl.groep4.kvc.client.view.pane;

import java.rmi.RemoteException;
import java.util.EnumMap;
import java.util.Map.Entry;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
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
import nl.groep4.kvc.client.view.elements.MenuButton;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.UpdateCosts;

public class BuildPane implements PaneHolder, UpdateCosts {
    MenuButton streetbutton = new MenuButton(425, 500, TranslationManager.translate("build.button.road"));
    MenuButton villagebutton = new MenuButton(425, 500, TranslationManager.translate("build.button.village"));
    MenuButton citybutton = new MenuButton(425, 500, TranslationManager.translate("build.button.city"));
    MenuButton backbutton = new MenuButton(425, 500, TranslationManager.translate("build.button.back"));

    private Text city;
    private Text street;
    private Text village;
    private Text prices;

    private Text brstreet;
    private Text orstreet;
    private Text whstreet;
    private Text wolstreet;
    private Text wodstreet;

    private Text brvillage;
    private Text orvillage;
    private Text whvillage;
    private Text wolvillage;
    private Text wodvillage;

    private Text brcity;
    private Text orcity;
    private Text whcity;
    private Text wolcity;
    private Text wodcity;

    private HBox hboxprices;
    private VBox vboxprices;
    private VBox vboxwheat;
    private VBox vboxwood;
    private VBox vboxwool;
    private VBox vboxbrick;
    private VBox vboxore;

    private Text wheat;
    private Text wood;
    private Text wool;
    private Text brick;
    private Text ore;

    /*
     * private Text brickAmount; private Text woolAmount; private Text
     * wheatAmount; private Text oreAmount; private Text woodAmount; private
     * Text brick; private Text wool; private Text wheat; private Text ore;
     * private Text wood; private Text resources; private Text street; private
     * Text village; private Text city;
     * 
     * private Text wheatStreet; private Text wheatVillage; private Text
     * wheatCity; private Text woeatStreet; private Text woodVillage; private
     * Text woodStreet; private Text woodCity; private Text woolStreet; private
     * Text woolVillage; private Text woolCity; private Text brickStreet;
     * private Text brickVillage; private Text brickCity; private Text
     * oreStreet; private Text oreVillage; private Text oreCity;
     * 
     * private HBox hboxPrices; private VBox vboxBuilding; private VBox
     * vboxWheat; private VBox vboxWood; private VBox vboxWool; private VBox
     * vboxBrick; private VBox vboxOre;
     */

    private SceneMap sceneMap;

    public BuildPane(SceneMap sceneMap) {
	this.sceneMap = sceneMap;
    }

    @Override
    public Pane getPane() {
	MenuButton streetbutton = new MenuButton(425, 500, TranslationManager.translate("build.button.road"));
	MenuButton villagebutton = new MenuButton(425, 500, TranslationManager.translate("build.button.village"));
	MenuButton citybutton = new MenuButton(425, 500, TranslationManager.translate("build.button.city"));
	MenuButton backbutton = new MenuButton(425, 500, TranslationManager.translate("build.button.back"));

	wood = new Text(TranslationManager.translate("build.materials.wood"));
	wheat = new Text(TranslationManager.translate("build.materials.wheat"));
	wool = new Text(TranslationManager.translate("build.materials.wool"));
	brick = new Text(TranslationManager.translate("build.materials.stone"));
	ore = new Text(TranslationManager.translate("build.materials.ore"));
	prices = new Text(TranslationManager.translate("build.text.prices:"));

	prices = new Text("prices");
	city = new Text("city");
	village = new Text("village");
	street = new Text("street");

	brstreet = new Text("0");
	orstreet = new Text("0");
	whstreet = new Text("0");
	wodstreet = new Text("0");
	wolstreet = new Text("0");

	brvillage = new Text("0");
	orvillage = new Text("0");
	whvillage = new Text("0");
	wodvillage = new Text("0");
	wolvillage = new Text("0");

	brcity = new Text("0");
	orcity = new Text("0");
	whcity = new Text("0");
	wodcity = new Text("0");
	wolcity = new Text("0");

	VBox vbox = new VBox(8);
	vbox.setPrefWidth(300);
	vbox.setPrefHeight(450);
	vbox.setAlignment(Pos.CENTER);

	GridPane gp = new GridPane();
	gp.setHgap(10);
	gp.setVgap(10);
	gp.setPadding(new Insets(50, 50, 50, 50));

	StackPane Build = new StackPane();
	Build.setAlignment(Pos.CENTER);
	Build.setMinSize(1366, 768);
	Text empty2 = new Text("");
	Text empty = new Text("");

	city = new Text("city");
	// Prices tonen in pane
	vboxprices = new VBox();
	vboxwheat = new VBox();
	vboxwood = new VBox();
	vboxwool = new VBox();
	vboxbrick = new VBox();
	vboxore = new VBox();

	hboxprices = new HBox();

	vboxprices.getChildren().addAll(prices, city, village, street);
	vboxwheat.getChildren().addAll(wheat, whstreet, whvillage, whcity);
	vboxwood.getChildren().addAll(wood, wodstreet, wodvillage, wodcity);
	vboxwool.getChildren().addAll(wool, wolstreet, wolvillage, wolcity);
	vboxbrick.getChildren().addAll(brick, brstreet, brvillage, brcity);
	vboxore.getChildren().addAll(ore, orstreet, orvillage, orcity);
	hboxprices.getChildren().addAll(vboxprices, vboxwheat, vboxwood, vboxwool, vboxbrick, vboxore);
	hboxprices.setAlignment(Pos.CENTER);
	hboxprices.setPadding(new Insets(200, 0, 0, 0));
	hboxprices.setSpacing(20);

	Font BuildFont = new Font("Impact", 22);
	prices.setFont(BuildFont);
	street.setFont(BuildFont);
	street.setFill(Color.WHITE);
	street.setStroke(Color.BLACK);
	village.setFont(BuildFont);
	village.setFill(Color.WHITE);
	village.setStroke(Color.BLACK);
	city.setFont(BuildFont);
	city.setFill(Color.WHITE);
	city.setStroke(Color.BLACK);
	prices.setFill(Color.WHITE);
	prices.setStroke(Color.BLACK);
	streetbutton.setFont(ViewMaster.FONT);
	villagebutton.setFont(ViewMaster.FONT);
	citybutton.setFont(ViewMaster.FONT);

	vbox.getChildren().addAll(hboxprices, streetbutton, villagebutton, citybutton, backbutton);
	Build.getChildren().addAll(SceneUtil.getGamePane(), vbox);

	return Build;

    }

    @Override
    public void updateTranslation() {
	streetbutton.updateText(TranslationManager.translate("build.button.road"));
	villagebutton.updateText(TranslationManager.translate("build.button.village"));

	citybutton.updateText(TranslationManager.translate("build.button.city"));

	prices.setText(TranslationManager.translate("build.text.prices:"));
	city.setText(TranslationManager.translate("build.text.city"));
	village.setText(TranslationManager.translate("build.text.village"));
	street.setText(TranslationManager.translate("build.text.street"));

	wood.setText(TranslationManager.translate("build.materials.wood"));
	wheat.setText(TranslationManager.translate("build.materials.wheat"));
	brick.setText(TranslationManager.translate("build.materials.stone"));
	ore.setText(TranslationManager.translate("build.materials.ore"));
	wool.setText(TranslationManager.translate("build.materials.wool"));

    }

    @Override
    public void updateStreetCosts(EnumMap<Resource, Integer> resources) throws RemoteException {

	for (Entry<Resource, Integer> resource : resources.entrySet()) {
	    Resource res = resource.getKey();
	    String amount = resource.getValue().toString();

	    switch (res) {
	    case BRICK:
		brstreet.setText(amount);
		break;
	    case ORE:
		orstreet.setText(amount);
		break;
	    case WHEAT:
		whstreet.setText(amount);
		break;
	    case WOOD:
		wodstreet.setText(amount);
		break;
	    case WOOL:
		wolstreet.setText(amount);
		break;
	    }

	}

    }

    @Override
    public void updateVillageCosts(EnumMap<Resource, Integer> resources) throws RemoteException {

	for (Entry<Resource, Integer> resource : resources.entrySet()) {
	    Resource res = resource.getKey();
	    String amount = resource.getValue().toString();

	    switch (res) {
	    case BRICK:
		brvillage.setText(amount);
		break;
	    case ORE:
		orvillage.setText(amount);
		break;
	    case WHEAT:
		whvillage.setText(amount);
		break;
	    case WOOD:
		wodvillage.setText(amount);
		break;
	    case WOOL:
		wolvillage.setText(amount);
		break;
	    }
	}

    }

    @Override
    public void updateCityCosts(EnumMap<Resource, Integer> resources) throws RemoteException {

	for (Entry<Resource, Integer> resource : resources.entrySet()) {
	    Resource res = resource.getKey();
	    String amount = resource.getValue().toString();

	    switch (res) {
	    case BRICK:
		this.brcity.setText(amount);
		break;
	    case ORE:
		this.orcity.setText(amount);
		break;
	    case WHEAT:
		whcity.setText(amount);
		break;
	    case WOOD:
		wodcity.setText(amount);
		break;
	    case WOOL:
		wolcity.setText(amount);
		break;
	    }
	}

    }

    @Override
    public void updateCardCosts(EnumMap<Resource, Integer> resources) throws RemoteException {
    }
}