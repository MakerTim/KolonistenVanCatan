package nl.groep4.kvc.client.view.elements;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.controller.MapController;
import nl.groep4.kvc.client.view.scene.SceneMap;
import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.Point;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.Tile;
import nl.groep4.kvc.common.map.TileLand;
import nl.groep4.kvc.common.map.TileResource;
import nl.groep4.kvc.common.map.TileSea;
import nl.groep4.kvc.common.util.CollectionUtil;

public class ClientTile extends StackPane {

    public enum SelectState {
	STREET, BUILDING, TILE
    }

    private static final Map<String, Image> CACHE = new HashMap<>();

    private MapController controller;
    private Tile tile;
    private Coordinate coord;

    private Line[] lines = new Line[6];
    private ImageView[] houses = new ImageView[2];
    private ImageView image;
    private ImageView fiche;
    private Text number;

    public ClientTile(MapController controller, Coordinate coord) {
	this.coord = coord;
	Pane overlayPane = new Pane();
	image = new ImageView();
	fiche = new ImageView("img/tiles/fiche.png");
	number = new Text();

	image.setFitWidth(1 * SceneMap.scale);
	image.setFitHeight(0.86 * SceneMap.scale);
	fiche.setFitWidth(0.35 * SceneMap.scale);
	fiche.setFitHeight(0.35 * SceneMap.scale);
	number.setFont(new Font("Impact", (0.2 * SceneMap.scale)));
	number.setStroke(Color.BLACK);
	number.setStrokeWidth(0.01 * SceneMap.scale);
	number.setFill(Color.WHITE);

	// Offset fix for fxpanes?
	double xFix = 0.5;
	double yFix = 0.5;
	{
	    double offset = 1.05;
	    double xyOffset = 27;
	    for (int i = 0; i < lines.length; i++) {
		int j = i;
		Coordinate a = CollectionUtil.getInRange(Point.values(), i - 1).realOffset().multiply(offset);
		Coordinate b = CollectionUtil.getInRange(Point.values(), i).realOffset().multiply(offset);
		lines[i] = new Line(a.getX() * SceneMap.scale + xyOffset, -a.getY() * SceneMap.scale + xyOffset,
			b.getX() * SceneMap.scale + xyOffset, -b.getY() * SceneMap.scale + xyOffset);
		lines[i].setStroke(new Color(Math.random(), 0, 0, 1));
		lines[i].setStrokeWidth(10);
		lines[i].setOnMouseClicked(click -> {
		    System.out.println("street");
		    onStreetClick(coord.add(Direction.values()[j].offset(coord).multiply(0.5)));
		});
		overlayPane.getChildren().add(lines[i]);
	    }
	}

	for (int i = 0; i < houses.length; i++) {
	    int j = i;
	    Coordinate offset = CollectionUtil.getInRange(Point.values(), i + 4).realOffset().multiply(SceneMap.scale);
	    houses[i] = new ImageView(cacheImage("img/buildings/house_RED.png"));
	    houses[i].setLayoutX(offset.getX());
	    houses[i].setLayoutY(-offset.getY());
	    houses[i].setOnMouseClicked(click -> {
		onBuildingClick(Point.values()[j + 4].addTo(coord));
	    });
	    overlayPane.getChildren().add(houses[i]);
	}
	overlayPane.setTranslateX(xFix * SceneMap.scale * 0.81);
	overlayPane.setTranslateY(yFix * SceneMap.scale * 0.7);

	renderTile();
	getChildren().addAll(image, fiche, number, overlayPane);
    }

    public void setTile(Tile tile) {
	this.tile = tile;
	renderTile();
    }

    public Coordinate getPosition() {
	return this.coord;
    }

    private Image cacheImage(String url) {
	if (!CACHE.containsKey(url)) {
	    CACHE.put(url, new Image(url));
	}
	return CACHE.get(url);
    }

    public void setController(MapController controller) {
	this.controller = controller;
    }

    private void renderTile() {
	if (tile == null) {
	    image.setImage(cacheImage("img/tiles/tile_null.png"));
	    return;
	}
	image.setImage(cacheImage("img/tiles/tile_" + tile.getType() + ".png"));
	if (tile instanceof TileLand) {
	    TileLand landTile = (TileLand) tile;
	    if (landTile.hasRover()) {
		fiche.setImage(cacheImage("img/tiles/fiche_rover.png"));
		number.setText("");
	    } else {
		fiche.setImage(cacheImage("img/tiles/fiche.png"));
		if (landTile instanceof TileResource) {
		    number.setText(Integer.toString(((TileResource) landTile).getNumber()));
		} else {
		    number.setText("");
		}
	    }
	} else if (tile instanceof TileSea) {
	    fiche.setImage(null);
	}
	for (Direction direction : Direction.values()) {
	    Street street = tile.getStreet(direction);
	    Line line = lines[direction.ordinal()];
	    if (street != null && street.getOwner() != null) {
		try {
		    line.setStroke(street.getOwner().getColor().getColor());
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    } else {
		line.setStroke(new Color(0, 0, 0, 0));
	    }
	}
	for (int i = 4; i < Point.values().length; i++) {
	    Building building = tile.getBuilding(Point.values()[i]);
	    ImageView house = houses[i - 4];
	    if (building != null && building.getOwner() != null) {
		try {
		    house.setImage(cacheImage("img/buildings/house_" + building.getOwner().getColor() + ".png"));
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    } else {
		house.setImage(cacheImage("img/buildings/house_null.png"));
	    }
	}
    }

    public void setSelectState(SelectState select) {
	switch (select) {
	case BUILDING:
	    setBuildClickable(true);
	    setStreetClickable(false);
	    setTileClickable(false);
	    break;
	case STREET:
	    setBuildClickable(false);
	    setStreetClickable(true);
	    setTileClickable(false);
	    break;
	case TILE:
	    setBuildClickable(false);
	    setStreetClickable(false);
	    setTileClickable(true);
	    break;
	}
    }

    private void setTileClickable(boolean clickAble) {
	image.setMouseTransparent(!clickAble);
	fiche.setMouseTransparent(!clickAble);
	number.setMouseTransparent(!clickAble);
    }

    private void setBuildClickable(boolean clickAble) {
	for (ImageView house : houses) {
	    house.setMouseTransparent(!clickAble);
	}
    }

    private void setStreetClickable(boolean clickAble) {
	for (Line street : lines) {
	    street.setMouseTransparent(!clickAble);
	}
    }

    private void onStreetClick(Coordinate coord) {
	controller.placeStreet(coord);
    }

    private void onBuildingClick(Coordinate coord) {
	controller.placeBuilding(coord);
    }
}
