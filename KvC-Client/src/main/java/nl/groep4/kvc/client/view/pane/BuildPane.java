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
    MenuButton road = new MenuButton(425, 500, TranslationManager.translate("build.button.road"));
    MenuButton village = new MenuButton(425, 500, TranslationManager.translate("build.button.village"));
    MenuButton city = new MenuButton(425, 500, TranslationManager.translate("build.button.city"));

    private Text brStreet;
    private Text orStreet;
    private Text whStreet;
    private Text wolStreet;
    private Text wodStreet;

    private Text brVillage;
    private Text orVillage;
    private Text whVillage;
    private Text wolVillage;
    private Text wodVillage;

    private Text brCity;
    private Text orCity;
    private Text whCity;
    private Text wolCity;
    private Text wodCity;

    private HBox hboxPrices;
    private VBox vboxPrices;
    private VBox vboxWheat;
    private VBox vboxWood;
    private VBox vboxWool;
    private VBox vboxBrick;
    private VBox vboxOre;

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

    String wod = (TranslationManager.translate("materials.wood"));
    String whe = (TranslationManager.translate("materials.wheat"));
    String wol = (TranslationManager.translate("materials.wool"));
    String sto = (TranslationManager.translate("materials.stone"));
    String ore = (TranslationManager.translate("materials.ore"));
    String prices = ((TranslationManager.translate("build.materials.row.prices:")));

    private SceneMap sceneMap;

    public BuildPane(SceneMap sceneMap) {
	this.sceneMap = sceneMap;
    }

    Text Prices = new Text((TranslationManager.translate("materials.row") + " " + whe + wod + wol + sto + ore));
    Text RoadPrices = new Text(TranslationManager.translate("road.row"));
    Text VillagePrices = new Text(TranslationManager.translate("village.row"));
    Text CityPrices = new Text(TranslationManager.translate("city.row"));

    @Override
    public Pane getPane() {
	MenuButton road = new MenuButton(425, 500, TranslationManager.translate("build.button.road"));
	MenuButton village = new MenuButton(425, 500, TranslationManager.translate("build.button.village"));
	MenuButton city = new MenuButton(425, 500, TranslationManager.translate("build.button.city"));

	brStreet = new Text("0");
	orStreet = new Text("0");
	whStreet = new Text("0");
	wodStreet = new Text("0");
	wolStreet = new Text("0");

	brVillage = new Text("0");
	orVillage = new Text("0");
	whVillage = new Text("0");
	wodVillage = new Text("0");
	wolVillage = new Text("0");

	brCity = new Text("0");
	orCity = new Text("0");
	whCity = new Text("0");
	wodCity = new Text("0");
	wolCity = new Text("0");

	VBox vbox = new VBox(8);
	vbox.setPrefWidth(300);
	vbox.setPrefHeight(450);
	vbox.setAlignment(Pos.CENTER);

	GridPane gp = new GridPane();
	gp.setHgap(10);
	gp.setVgap(10);
	gp.setPadding(new Insets(50, 50, 50, 50));

	Text wheat = new Text("Wheats");
	wheat.setFill(Color.WHITE);
	wheat.setStroke(Color.BLACK);
	wheat.setFont(ViewMaster.FONT);
	gp.add(wheat, 14, 0);

	StackPane Build = new StackPane();
	Build.setAlignment(Pos.CENTER);
	Build.setMinSize(1366, 768);
	Text Empty2 = new Text("");
	Text Empty = new Text("");
	Text Empty3 = new Text("");
	Text Empty4 = new Text("");

	// Prices tonen in pane
	vboxPrices = new VBox();
	vboxWheat = new VBox();
	vboxWood = new VBox();
	vboxWool = new VBox();
	vboxBrick = new VBox();
	vboxOre = new VBox();

	hboxPrices = new HBox();

	hboxPrices.getChildren().addAll(vboxPrices, vboxWheat, vboxWood, vboxWool, vboxBrick, vboxOre);
	hboxPrices.setAlignment(Pos.CENTER);
	hboxPrices.setPadding(new Insets(200, 0, 0, 0));
	hboxPrices.setSpacing(20);
	Font BuildFont = new Font("Impact", 22);
	Prices.setFont(BuildFont);
	RoadPrices.setFont(BuildFont);
	RoadPrices.setFill(Color.WHITE);
	RoadPrices.setStroke(Color.BLACK);
	VillagePrices.setFont(BuildFont);
	VillagePrices.setFill(Color.WHITE);
	VillagePrices.setStroke(Color.BLACK);
	CityPrices.setFont(BuildFont);
	CityPrices.setFill(Color.WHITE);
	CityPrices.setStroke(Color.BLACK);
	Prices.setFill(Color.WHITE);
	Prices.setStroke(Color.BLACK);
	road.setFont(ViewMaster.FONT);
	village.setFont(ViewMaster.FONT);
	city.setFont(ViewMaster.FONT);

	vbox.getChildren().addAll(Empty, Empty2, RoadPrices, road, village, city);
	Build.getChildren().addAll(SceneUtil.getGamePane(), vbox);

	return Build;

    }

    @Override
    public void updateTranslation() {
	road.updateText(TranslationManager.translate("build.button.road"));
	village.updateText(TranslationManager.translate("build.button.village"));
	prices = (TranslationManager.translate("materials.row") + " " + whe + wod + wol + sto + ore);
	city.updateText(TranslationManager.translate("build.button.city"));

	wod = (TranslationManager.translate("materials.wood"));
	whe = (TranslationManager.translate("materials.wheat"));
	sto = (TranslationManager.translate("materials.stone"));
	ore = (TranslationManager.translate("materials.ore"));
	wol = (TranslationManager.translate("materials.wol"));

	wod = (TranslationManager.translate("build.materials.wood"));
	whe = (TranslationManager.translate("build.materials.wheat"));
	sto = (TranslationManager.translate("build.materials.stone"));
	ore = (TranslationManager.translate("build.materials.ore"));
	wol = (TranslationManager.translate("build.materials.wool"));

	wod = (TranslationManager.translate("materials.wood"));
	whe = (TranslationManager.translate("materials.wheat"));
	sto = (TranslationManager.translate("materials.stone"));
	ore = (TranslationManager.translate("materials.ore"));
	wol = (TranslationManager.translate("materials.wol"));

	wod = (TranslationManager.translate("materials.wood"));
	whe = (TranslationManager.translate("materials.wheat"));
	sto = (TranslationManager.translate("materials.stone"));
	ore = (TranslationManager.translate("materials.ore"));
	wol = (TranslationManager.translate("materials.wol"));

	wod = (TranslationManager.translate("materials.wood"));
	whe = (TranslationManager.translate("materials.wheat"));
	sto = (TranslationManager.translate("materials.stone"));
	ore = (TranslationManager.translate("materials.ore"));
	wol = (TranslationManager.translate("materials.wol"));

	RoadPrices.setText(TranslationManager.translate("build.road.row") + " " + brStreet + orStreet + whStreet
		+ wodStreet + wolStreet);
	VillagePrices.setText(TranslationManager.translate("build.village.row") + " " + brVillage + orVillage
		+ whVillage + wodVillage + wolVillage);
	CityPrices.setText(
		TranslationManager.translate("build.city.row") + " " + brCity + orCity + whCity + wodCity + wolCity);

    }

    @Override
    public void updateStreetCosts(EnumMap<Resource, Integer> resources) throws RemoteException {

	for (Entry<Resource, Integer> resource : resources.entrySet()) {
	    Resource res = resource.getKey();
	    String amount = resource.getValue().toString();

	    switch (res) {
	    case BRICK:
		brStreet.setText(amount);
		break;
	    case ORE:
		orStreet.setText(amount);
		break;
	    case WHEAT:
		whStreet.setText(amount);
		break;
	    case WOOD:
		wodStreet.setText(amount);
		break;
	    case WOOL:
		wolStreet.setText(amount);
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
		brVillage.setText(amount);
		break;
	    case ORE:
		orVillage.setText(amount);
		break;
	    case WHEAT:
		whVillage.setText(amount);
		break;
	    case WOOD:
		wodVillage.setText(amount);
		break;
	    case WOOL:
		wolVillage.setText(amount);
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
		this.brCity.setText(amount);
		break;
	    case ORE:
		this.orCity.setText(amount);
		break;
	    case WHEAT:
		whCity.setText(amount);
		break;
	    case WOOD:
		wodCity.setText(amount);
		break;
	    case WOOL:
		wolCity.setText(amount);
		break;
	    }
	}

    }

    @Override
    public void updateCardCosts(EnumMap<Resource, Integer> resources) throws RemoteException {
    }
}