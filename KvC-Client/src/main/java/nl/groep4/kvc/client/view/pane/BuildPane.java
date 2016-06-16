package nl.groep4.kvc.client.view.pane;

import java.rmi.RemoteException;
import java.util.EnumMap;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
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

    int brStreet;
    int orStreet;
    int whStreet;
    int wodStreet;
    int wolStreet;

    int brVillage;
    int orVillage;
    int whVillage;
    int wodVillage;
    int wolVillage;

    int brCity;
    int orCity;
    int whCity;
    int wodCity;
    int wolCity;

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

    String wod = (TranslationManager.translate("build.materials.wood"));
    String whe = (TranslationManager.translate("build.materials.wheat"));
    String wol = (TranslationManager.translate("build.materials.wool"));
    String sto = (TranslationManager.translate("build.materials.stone"));
    String ore = (TranslationManager.translate("build.materials.ore"));
    String prices = ((TranslationManager.translate("build.materials.row.prices")));

    Text Prices = new Text((TranslationManager.translate("build.materials.row") + " " + whe + wod + wol + sto + ore));
    Text RoadPrices = new Text(TranslationManager.translate("build.road.row"));
    Text VillagePrices = new Text(TranslationManager.translate("build.village.row"));
    Text CityPrices = new Text(TranslationManager.translate("build.city.row"));

    public BuildPane(SceneMap sceneMap) {
	// TODO Auto-generated constructor stub
    }

    @Override
    public Pane getPane() {
	MenuButton road = new MenuButton(425, 500, TranslationManager.translate("build.button.road"));
	MenuButton village = new MenuButton(425, 500, TranslationManager.translate("build.button.village"));
	MenuButton city = new MenuButton(425, 500, TranslationManager.translate("build.button.city"));

	VBox vbox = new VBox(8);
	vbox.setPrefWidth(300);
	vbox.setPrefHeight(450);
	vbox.setAlignment(Pos.CENTER);

	GridPane gp = new GridPane();
	gp.setHgap(10);
	gp.setVgap(10);
	gp.setPadding(new Insets(50, 50, 50, 50));

	Text wheat = new Text("Wheat");
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

	Text Prices = new Text(
		(TranslationManager.translate("build.materials.row") + " " + whe + wod + wol + sto + ore));
	Text RoadPrices = new Text(TranslationManager.translate("build.road.row") + " " + brStreet + orStreet + whStreet
		+ wodStreet + wolStreet);
	Text VillagePrices = new Text(TranslationManager.translate("build.village.row") + " " + brVillage + orVillage
		+ whVillage + wodVillage + wolVillage);
	Text CityPrices = new Text(
		TranslationManager.translate("build.city.row") + " " + brCity + orCity + whCity + wodCity + wolCity);

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

	vbox.getChildren().addAll(Prices, RoadPrices, VillagePrices, CityPrices, Empty, Empty2, Empty3, Empty4, road,
		village, city);
	Build.getChildren().addAll(SceneUtil.getGamePane(), vbox);

	return Build;

    }

    @Override
    public void updateTranslation() {
	road.updateText(TranslationManager.translate("build.button.road"));
	village.updateText(TranslationManager.translate("build.button.village"));
	prices = (TranslationManager.translate("build.materials.row") + " " + whe + wod + wol + sto + ore);
	city.updateText(TranslationManager.translate("build.button.city"));
	wod = (TranslationManager.translate("build.materials.wood"));
	whe = (TranslationManager.translate("build.materials.wheat"));
	sto = (TranslationManager.translate("build.materials.stone"));
	ore = (TranslationManager.translate("build.materials.ore"));
	wol = (TranslationManager.translate("build.materials.wool"));
	RoadPrices.setText(TranslationManager.translate("build.road.row") + " " + brStreet + orStreet + whStreet
		+ wodStreet + wolStreet);
	VillagePrices.setText(TranslationManager.translate("build.village.row") + " " + brVillage + orVillage
		+ whVillage + wodVillage + wolVillage);
	CityPrices.setText(
		TranslationManager.translate("build.city.row") + " " + brCity + orCity + whCity + wodCity + wolCity);

    }

    @Override
    public void updateStreetCosts(EnumMap<Resource, Integer> resources) throws RemoteException {
	this.brStreet = resources.get(Resource.BRICK);
	this.orStreet = resources.get(Resource.ORE);
	this.whStreet = resources.get(Resource.WHEAT);
	this.wodStreet = resources.get(Resource.WOOD);
	this.wolStreet = resources.get(Resource.WOOL);

    }

    @Override
    public void updateVillageCosts(EnumMap<Resource, Integer> resources) throws RemoteException {
	this.brVillage = resources.get(Resource.BRICK);
	this.orVillage = resources.get(Resource.ORE);
	this.whVillage = resources.get(Resource.WHEAT);
	this.wodVillage = resources.get(Resource.WOOD);
	this.wolVillage = resources.get(Resource.WOOL);
    }

    @Override
    public void updateCityCosts(EnumMap<Resource, Integer> resources) throws RemoteException {
	this.brCity = resources.get(Resource.BRICK);
	this.orCity = resources.get(Resource.ORE);
	this.whCity = resources.get(Resource.WHEAT);
	this.wodCity = resources.get(Resource.WOOD);
	this.wolCity = resources.get(Resource.WOOL);
    }

    @Override
    public void updateCardCosts(EnumMap<Resource, Integer> resources) throws RemoteException {
    }
}