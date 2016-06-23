package nl.groep4.kvc.client.view.elements;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.controller.ClientRefrence;
import nl.groep4.kvc.client.controller.MapController;
import nl.groep4.kvc.client.util.ConfirmDialog;
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

/**
 * Generates map by setting tile coordinates. Adds the use of buildings and
 * streets.
 * 
 * @author Tim
 * @version 1.0
 */
public class ClientTile extends StackPane {

    private static final Map<String, Image> CACHE = new HashMap<>();

    private MapController controller;
    private Tile tile;
    private Coordinate coord;

    private Line[] lines = new Line[6];
    private ImageView[] houses = new ImageView[2];
    private ImageView image;
    private ImageView fiche;
    private Pane overlayPane;
    private Text number;

    /**
     * Arranges the width, coordinates and layout settings for fiches and
     * numbers that will be places upon the fiches.
     * 
     * @param coord
     *            gives the coordinate where the fiche should be places
     */
    public ClientTile(Coordinate coord) {
	this.coord = coord;
	overlayPane = new Pane();
	image = new ImageView();
	fiche = new ImageView("img/tiles/fiche.png");
	number = new Text();

	number.setMouseTransparent(true);
	fiche.setMouseTransparent(true);
	image.setFitWidth(1 * SceneMap.scale);
	image.setFitHeight(0.86 * SceneMap.scale);
	fiche.setFitWidth(0.35 * SceneMap.scale);
	fiche.setFitHeight(0.35 * SceneMap.scale);
	number.setFont(new Font("Impact", (0.2 * SceneMap.scale)));
	number.setStroke(Color.BLACK);
	number.setStrokeWidth(0.01 * SceneMap.scale);
	number.setFill(Color.WHITE);

	image.setOnMouseClicked(click -> {
	    if (fiche.getEffect() != null) {
		if (ConfirmDialog.confirm("rovermove")) {
		    onFicheClick();
		}
	    } else if (controller.isMovingRover()) {
		if (ConfirmDialog.confirm("roverplace")) {
		    onTileClick();
		}
	    }
	});

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
			if (ConfirmDialog.confirm("placestreet")) {
			    onStreetClick(coord.add(Direction.values()[j].offset(coord).multiply(0.5)));
			}
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
		if (ConfirmDialog.confirm("placebuilding")) {
		    onBuildingClick(Point.values()[j + 4].addTo(coord), house.getEffect() != null);
		}
	    });
	    overlayPane.getChildren().add((houses[i] = house));
	}
	overlayPane.setTranslateX(xFix * SceneMap.scale * 0.81);
	overlayPane.setTranslateY(yFix * SceneMap.scale * 0.7);

	renderTile();
	getChildren().addAll(image, fiche, number, overlayPane);
    }

    /**
     * renders and sets tile
     * 
     * @param tile
     *            current tile settings
     */
    public void setTile(Tile tile) {
	this.tile = tile;
	renderTile();
    }

    /**
     * gets tile
     * 
     * @return tile
     */
    public Tile getTile() {
	return tile;
    }

    /**
     * gets coordinate
     * 
     * @return current coordinate
     */
    public Coordinate getPosition() {
	return this.coord;
    }

    private Image cacheImage(String url) {
	if (!CACHE.containsKey(url)) {
	    CACHE.put(url, new Image(url));
	}
	return CACHE.get(url);
    }

    /**
     * sets controller
     * 
     * @param controller
     *            controller for the map
     */
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

    /**
     * Sets given variable in select to true
     * 
     * @param select
     *            contains building, street, bandit or tile
     */
    public void setSelectState(SelectState select) {
	switch (select) {
	case BUILDING:
	    setBuildClickable(true);
	    setStreetClickable(false);
	    overlayPane.setMouseTransparent(false);
	    setTileClickable(false);
	    setFicheClickable(false);
	    break;
	case STREET:
	    setBuildClickable(false);
	    setStreetClickable(true);
	    overlayPane.setMouseTransparent(false);
	    setTileClickable(false);
	    setFicheClickable(false);
	    break;
	case BANDIT:
	    setBuildClickable(false);
	    setStreetClickable(false);
	    overlayPane.setMouseTransparent(true);
	    setTileClickable(true);
	    setFicheClickable(true);
	    break;
	case TILE:
	    setBuildClickable(false);
	    setStreetClickable(false);
	    overlayPane.setMouseTransparent(true);
	    setTileClickable(true);
	    setFicheClickable(false);
	    break;
	}
    }

    private Effect shadow() {
	DropShadow shadow = new DropShadow();
	shadow.setBlurType(BlurType.GAUSSIAN);
	shadow.setRadius(127);
	shadow.setWidth(50);
	shadow.setHeight(50);
	shadow.setSpread(0.5);
	shadow.setColor(Color.WHITE);
	return shadow;
    }

    private void setFicheClickable(boolean clickAble) {
	if (clickAble) {
	    if (tile instanceof TileLand && ((TileLand) tile).hasRover() || tile == null) {
		fiche.setEffect(shadow());
	    }
	} else {
	    fiche.setEffect(null);
	}
    }

    private void setTileClickable(boolean clickAble) {
	image.setMouseTransparent(!clickAble);
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
	System.out.println("street click");
	controller.placeStreet(coord);
    }

    private void onBuildingClick(Coordinate coord, boolean city) {
	System.out.println("building click");
	controller.placeBuilding(coord, city ? BuildingType.CITY : BuildingType.VILLAGE);
    }

    private void onFicheClick() {
	System.out.println("fiche click");
	controller.moveFromRover(tile.getPosition());
    }

    private void onTileClick() {
	System.out.println("tile click");
	controller.moveToRover(tile.getPosition());
    }

    /**
     * Highlights streets
     * 
     * @param direction
     *            the direction to highlight
     * @param doesHighlight
     *            highlights streets when doesHiglight is true
     */
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

    /**
     * Highlights buildings
     * 
     * @param point
     *            location to highlight
     * @param type
     *            kind of building
     */
    public void highLightBuilding(Point point, BuildingType type) {
	if (point == Point.WEST || point == Point.NORTH_WEST) {
	    int i = point.ordinal();
	    Building building = tile.getBuilding(Point.values()[i]);
	    ImageView house = houses[i - 4];
	    if (building == null) {
		house.setImage(cacheImage("img/buildings/house_null.png"));
		return;
	    }
	    house.setEffect(null);
	    if (building.getOwner() != null) {
		try {
		    switch (building.getBuildingType()) {
		    case CITY:
			house.setImage(cacheImage("img/buildings/city_" + building.getOwner().getColor() + ".png"));
			break;
		    case EMPTY:
			house.setImage(cacheImage("img/buildings/house_null.png"));
			break;
		    case VILLAGE:
			if (type == BuildingType.CITY && building.getOwner().equals(ClientRefrence.getThePlayer())) {
			    house.setImage(cacheImage("img/buildings/city_hightlight.png"));
			    house.setEffect(new DropShadow());
			} else {
			    house.setImage(
				    cacheImage("img/buildings/house_" + building.getOwner().getColor() + ".png"));
			}
			break;
		    }
		} catch (Exception ex) {
		    ex.printStackTrace();
		}
	    } else {
		switch (type) {
		case EMPTY:
		case CITY:
		    house.setImage(cacheImage("img/buildings/house_null.png"));
		    break;
		case VILLAGE:
		    house.setImage(cacheImage("img/buildings/house_highlight.png"));
		    break;
		}
	    }
	}
    }
}
