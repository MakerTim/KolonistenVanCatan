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

    private Text prices = new Text(TranslationManager.translate("build.text.prices"));
    private Text city = new Text(TranslationManager.translate("build.text.city"));
    private Text village = new Text(TranslationManager.translate("build.text.village"));
    private Text street = new Text(TranslationManager.translate("build.text.street"));

    private Text brstreet = new Text("0");
    private Text orstreet = new Text("0");
    private Text whstreet = new Text("0");
    private Text wolstreet = new Text("0");
    private Text wodstreet = new Text("0");

    private Text brvillage = new Text("0");
    private Text orvillage = new Text("0");
    private Text whvillage = new Text("0");
    private Text wolvillage = new Text("0");
    private Text wodvillage = new Text("0");

    private Text brcity = new Text("0");
    private Text orcity = new Text("0");
    private Text whcity = new Text("0");
    private Text wolcity = new Text("0");
    private Text wodcity = new Text("0");

    private HBox hboxprices;
    private HBox hboxbuttons;
    private VBox vboxprices;
    private VBox vboxwheat;
    private VBox vboxwood;
    private VBox vboxwool;
    private VBox vboxbrick;
    private VBox vboxore;

    private Text wood = new Text(TranslationManager.translate("build.materials.wood"));
    private Text wheat = new Text(TranslationManager.translate("build.materials.wheat"));
    private Text wool = new Text(TranslationManager.translate("build.materials.wool"));
    private Text brick = new Text(TranslationManager.translate("build.materials.stone"));
    private Text ore = new Text(TranslationManager.translate("build.materials.ore"));

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
	streetbutton = new MenuButton(425, 500, TranslationManager.translate("build.button.road"));
	villagebutton = new MenuButton(425, 500, TranslationManager.translate("build.button.village"));
	citybutton = new MenuButton(425, 500, TranslationManager.translate("build.button.city"));
	backbutton = new MenuButton(425, 500, TranslationManager.translate("build.button.back"));

	Font FONT = new Font("Impact", 25);

	wheat.setFont(FONT);
	wood.setFont(FONT);
	wool.setFont(FONT);
	brick.setFont(FONT);
	ore.setFont(FONT);
	prices.setFont(FONT);
	city.setFont(FONT);
	village.setFont(FONT);
	street.setFont(FONT);
	brvillage.setFont(FONT);
	orvillage.setFont(FONT);
	whvillage.setFont(FONT);
	wodvillage.setFont(FONT);
	wolvillage.setFont(FONT);
	brcity.setFont(FONT);
	orcity.setFont(FONT);
	whcity.setFont(FONT);
	wodcity.setFont(FONT);
	wolcity.setFont(FONT);
	brstreet.setFont(FONT);
	orstreet.setFont(FONT);
	whstreet.setFont(FONT);
	wodstreet.setFont(FONT);
	wolstreet.setFont(FONT);

	wheat.setFill(Color.WHITE);
	wood.setFill(Color.WHITE);
	wool.setFill(Color.WHITE);
	brick.setFill(Color.WHITE);
	ore.setFill(Color.WHITE);
	prices.setFill(Color.WHITE);
	city.setFill(Color.WHITE);
	village.setFill(Color.WHITE);
	street.setFill(Color.WHITE);
	brvillage.setFill(Color.WHITE);
	orvillage.setFill(Color.WHITE);
	whvillage.setFill(Color.WHITE);
	wodvillage.setFill(Color.WHITE);
	wolvillage.setFill(Color.WHITE);
	brcity.setFill(Color.WHITE);
	orcity.setFill(Color.WHITE);
	whcity.setFill(Color.WHITE);
	wodcity.setFill(Color.WHITE);
	wolcity.setFill(Color.WHITE);
	brstreet.setFill(Color.WHITE);
	orstreet.setFill(Color.WHITE);
	whstreet.setFill(Color.WHITE);
	wodstreet.setFill(Color.WHITE);
	wolstreet.setFill(Color.WHITE);

	wheat.setStroke(Color.BLACK);
	wood.setStroke(Color.BLACK);
	wool.setStroke(Color.BLACK);
	brick.setStroke(Color.BLACK);
	ore.setStroke(Color.BLACK);
	prices.setStroke(Color.BLACK);
	city.setStroke(Color.BLACK);
	village.setStroke(Color.BLACK);
	street.setStroke(Color.BLACK);
	brvillage.setStroke(Color.BLACK);
	orvillage.setStroke(Color.BLACK);
	whvillage.setStroke(Color.BLACK);
	wodvillage.setStroke(Color.BLACK);
	wolvillage.setStroke(Color.BLACK);
	brcity.setStroke(Color.BLACK);
	orcity.setStroke(Color.BLACK);
	whcity.setStroke(Color.BLACK);
	wodcity.setStroke(Color.BLACK);
	wolcity.setStroke(Color.BLACK);
	brstreet.setStroke(Color.BLACK);
	orstreet.setStroke(Color.BLACK);
	whstreet.setStroke(Color.BLACK);
	wodstreet.setStroke(Color.BLACK);
	wolstreet.setStroke(Color.BLACK);

	VBox vbox = new VBox();
	vbox.setPrefWidth(100);
	vbox.setPrefHeight(380);
	vbox.setAlignment(Pos.CENTER);

	GridPane gp = new GridPane();
	gp.setHgap(10);
	gp.setVgap(10);
	gp.setPadding(new Insets(50, 50, 50, 50));

	StackPane Build = new StackPane();
	Build.setAlignment(Pos.CENTER);
	Build.setMinSize(1366, 768);

	// Prices tonen in pane
	vboxprices = new VBox();
	vboxwheat = new VBox();
	vboxwood = new VBox();
	vboxwool = new VBox();
	vboxbrick = new VBox();
	vboxore = new VBox();

	hboxprices = new HBox();
	hboxbuttons = new HBox();

	vboxprices.getChildren().addAll(prices, city, village, street);
	vboxwheat.getChildren().addAll(wheat, whstreet, whvillage, whcity);
	vboxwood.getChildren().addAll(wood, wodstreet, wodvillage, wodcity);
	vboxwool.getChildren().addAll(wool, wolstreet, wolvillage, wolcity);
	vboxbrick.getChildren().addAll(brick, brstreet, brvillage, brcity);
	vboxore.getChildren().addAll(ore, orstreet, orvillage, orcity);

	vboxprices.setSpacing(15);
	vboxwheat.setSpacing(10);
	vboxwood.setSpacing(10);
	vboxwool.setSpacing(10);
	vboxbrick.setSpacing(10);
	vboxore.setSpacing(10);

	hboxprices.getChildren().addAll(vboxprices, vboxwheat, vboxwood, vboxwool, vboxbrick, vboxore);
	hboxprices.setAlignment(Pos.CENTER);
	hboxprices.setSpacing(20);

	hboxbuttons.setAlignment(Pos.CENTER);
	hboxbuttons.setSpacing(10);
	hboxbuttons.setPadding(new Insets(30, 0, 80, 0));

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
	backbutton.registerClick(() -> {
	    sceneMap.closeOverlay();
	});
	hboxbuttons.getChildren().addAll(streetbutton, villagebutton, citybutton);
	vbox.getChildren().addAll(hboxprices, hboxbuttons, backbutton);
	Build.getChildren().addAll(SceneUtil.getGamePane(), vbox);

	return Build;

    }

    @Override
    public void updateTranslation() {
	streetbutton.updateText(TranslationManager.translate("build.button.road"));
	villagebutton.updateText(TranslationManager.translate("build.button.village"));

	citybutton.updateText(TranslationManager.translate("build.button.city"));

	prices.setText(TranslationManager.translate("build.text.prices"));
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