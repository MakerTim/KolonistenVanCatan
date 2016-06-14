package nl.groep4.kvc.client.view.pane;

import java.rmi.RemoteException;
import java.util.EnumMap;

import javafx.geometry.Pos;
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

    Text wod = new Text(TranslationManager.translate("materials.wood"));
    Text whe = new Text(TranslationManager.translate("materials.wheat"));
    Text wol = new Text(TranslationManager.translate("materials.wool"));
    Text sto = new Text(TranslationManager.translate("materials.stone"));
    Text ore = new Text(TranslationManager.translate("materials.ore"));
    Text prices = new Text((TranslationManager.translate("materials.row.prices:")));

    Text Prices = new Text((TranslationManager.translate("materials.row") + " " + whe + wod + wol + sto + ore));
    Text RoadPrices = new Text(TranslationManager.translate("road.row"));
    Text VillagePrices = new Text(TranslationManager.translate("village.row"));
    Text CityPrices = new Text(TranslationManager.translate("city.row"));

    @Override
    public Pane getPane() {
	MenuButton road = new MenuButton(425, 500, TranslationManager.translate("build.button.road"));
	MenuButton village = new MenuButton(425, 500, TranslationManager.translate("build.button.village"));
	MenuButton city = new MenuButton(425, 500, TranslationManager.translate("build.button.city"));

	VBox vbox = new VBox(8);
	vbox.setPrefWidth(300);
	vbox.setPrefHeight(450);
	vbox.setAlignment(Pos.CENTER);

	StackPane Build = new StackPane();
	Build.setAlignment(Pos.CENTER);
	Build.setMinSize(1366, 768);
	Text Empty2 = new Text("");
	Text Empty = new Text("");
	Text Empty3 = new Text("");
	Text Empty4 = new Text("");

	Text Prices = new Text((TranslationManager.translate("materials.row") + " " + whe + wod + wol + sto + ore));
	Text RoadPrices = new Text(TranslationManager.translate("road.row") + " " + brStreet + orStreet + whStreet
		+ wodStreet + wolStreet);
	Text VillagePrices = new Text(TranslationManager.translate("road.row") + " " + brVillage + orVillage + whVillage
		+ wodVillage + wolVillage);
	Text CityPrices = new Text(
		TranslationManager.translate("city.row") + " " + brCity + orCity + whCity + wodCity + wolCity);

	Font BuildFont = new Font("Impact", 14);
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
	Build.getChildren().addAll(SceneUtil.getBuildMenuBackground(), vbox);

	return Build;

    }

    @Override
    public void updateTranslation() {
	road.updateText(TranslationManager.translate("build.button.road"));
	village.updateText(TranslationManager.translate("build.button.village"));
	prices.setText(TranslationManager.translate("materials.row") + " " + whe + wod + wol + sto + ore);
	city.updateText(TranslationManager.translate("build.button.city"));
	wod.setText(TranslationManager.translate("materials.wood"));
	whe.setText(TranslationManager.translate("materials.wheat"));
	sto.setText(TranslationManager.translate("materials.stone"));
	ore.setText(TranslationManager.translate("materials.ore"));
	wol.setText(TranslationManager.translate("materials.wol"));
	RoadPrices.setText(TranslationManager.translate("road.row") + " " + brStreet + orStreet + whStreet + wodStreet
		+ wolStreet);
	VillagePrices.setText(TranslationManager.translate("village.row") + " " + brVillage + orVillage + whVillage
		+ wodVillage + wolVillage);
	CityPrices
		.setText(TranslationManager.translate("city.row") + " " + brCity + orCity + whCity + wodCity + wolCity);

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
}