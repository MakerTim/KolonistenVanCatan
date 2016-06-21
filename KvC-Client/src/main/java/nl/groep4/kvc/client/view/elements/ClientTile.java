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
import nl.groep4.kvc.common.enumeration.BuildingType;
import nl.groep4.kvc.common.enumeration.Direction;
import nl.groep4.kvc.common.enumeration.Point;
import nl.groep4.kvc.common.enumeration.SelectState;
import nl.groep4.kvc.common.map.Building;
import nl.groep4.kvc.common.map.Coordinate;
import nl.groep4.kvc.common.map.Street;
import nl.groep4.kvc.common.map.Tile;
import nl.groep4.kvc.common.map.TileLand;
import nl.groep4.kvc.common.map.TileResource;
import nl.groep4.kvc.common.map.TileSea;
import nl.groep4.kvc.common.util.CollectionUtil;

public class ClientTile extends StackPane {

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
		Line line = new Line(a.getX() * SceneMap.scale + xyOffset, -a.getY() * SceneMap.scale + xyOffset,
			b.getX() * SceneMap.scale + xyOffset, -b.getY() * SceneMap.scale + xyOffset);
		line.setStroke(new Color(Math.random(), 0, 0, 1));
		line.setStrokeWidth(10);
		line.setOnMouseClicked(click -> {
		    if (line.getStroke().isOpaque()) {
			onStreetClick(coord.add(Direction.values()[j].offset(coord).multiply(0.5)));
		    }
		});
		overlayPane.getChildren().add((lines[i] = line));
	    }
	}

	for (int i = 0; i < houses.length; i++) {
	    int j = i;
	    Coordinate offset = CollectionUtil.getInRange(Point.values(), i + 4).realOffset().multiply(SceneMap.scale);
	    ImageView house = new ImageView(cacheImage("img/buildings/house_RED.png"));
	    house.setLayoutX(offset.getX());
	    house.setLayoutY(-offset.getY());
	    house.setOnMouseClicked(click -> {
		onBuildingClick(Point.values()[j + 4].addTo(coord));
	    });
	    overlayPane.getChildren().add((houses[i] = house));
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

    public Tile getTile() {
	return tile;
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
	    highLightStreet(direction, false);
	}
	for (Point point : Point.values()) {
	    highLightBuilding(point, BuildingType.EMPTY);
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
	case BANDIT:
	    setBuildClickable(false);
	    setStreetClickable(false);
	    setTileClickable(true);
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

    public void highLightStreet(Direction direction, boolean doesHighlight) {
	if (doesHighlight) {
	    Line line = lines[direction.ordinal()];
	    line.setStroke(Color.BLACK);
	} else {
	    Line line = lines[direction.ordinal()];
	    Street street = tile.getStreet(direction);
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
    }

    public void highLightBuilding(Point point, BuildingType type) {
	if (point == Point.WEST || point == Point.NORTH_WEST) {
	    int i = point.ordinal();
	    Building building = tile.getBuilding(Point.values()[i]);
	    ImageView house = houses[i - 4];
	    if (building == null) {
		house.setImage(cacheImage("img/buildings/house_null.png"));
		return;
	    }
	    if (building.getOwner() != null) {
		try {
		    switch (building.getBuildingType()) {
		    case CITY:
		    case EMPTY:
			house.setImage(cacheImage("img/buildings/city_" + building.getOwner().getColor() + ".png"));
			break;
		    case VILLAGE:
			house.setImage(cacheImage("img/buildings/house_" + building.getOwner().getColor() + ".png"));
			break;
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    } else {
		switch (type) {
		case EMPTY:
		    house.setImage(cacheImage("img/buildings/house_null.png"));
		    break;
		case CITY:
		    house.setImage(cacheImage("img/buildings/city_highlight.png"));
		    break;
		case VILLAGE:
		    house.setImage(cacheImage("img/buildings/house_highlight.png"));
		    break;
		}
	    }
	}
    }
}
