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

    // TODO: Bachir: Vertalingen achteraf werken niet
    // TODO: Bachir: zorg er voor dat je kosten kan updaten vanaf de server
    // TODO: Haal overbodige todo's weg

    MenuButton road = new MenuButton(425, 500, TranslationManager.translate("build.button.road"));
    MenuButton village = new MenuButton(425, 500, TranslationManager.translate("build.button.village"));
    MenuButton city = new MenuButton(425, 500, TranslationManager.translate("build.button.city"));

    @Override
    public Pane getPane() {
	// TODO Auto-generated method stub

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

	Text Prices = new Text("   Materials.row:  \t\t\t Wht \t wod \t Wol \t Sto \t Ore");
	Text RoadPrices = new Text("Road.row:\t\t\t\t 0 \t\t 1 \t\t 0 \t\t 1 \t\t 0");
	Text VillagePrices = new Text("Village.row:\t\t\t 1 \t\t 1 \t\t 1 \t\t 1 \t\t 1");
	Text CityPrices = new Text("City.row:\t\t\t\t 3 \t\t 0 \t\t 0 \t\t 0 \t\t 2");

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
	Build.getChildren().addAll(SceneUtil.getGamePane(), vbox);

	return Build;

    }

    @Override
    public void updateTranslation() {
	// TODO Auto-generated method stub

	road.updateText(TranslationManager.translate("build.button.road"));
	village.updateText(TranslationManager.translate("build.button.village"));
	city.updateText(TranslationManager.translate("build.button.city"));

    }

    @Override
    public void updateStreetCosts(EnumMap<Resource, Integer> resources) throws RemoteException {
    }

    @Override
    public void updateVillageCosts(EnumMap<Resource, Integer> resources) throws RemoteException {
    }

    @Override
    public void updateCityCosts(EnumMap<Resource, Integer> resources) throws RemoteException {
    }
}