package nl.groep4.kvc.client.view.elements;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import nl.groep4.kvc.client.util.TranslationManager;
import nl.groep4.kvc.client.view.ViewMaster;

public class ResourceCard extends Text {

    private ImageView cardPlank;
    private ImageView oreCard;
    private ImageView stoneCard;
    private ImageView wheatCard;
    private ImageView woodCard;
    private ImageView woolCard;

    private Text amntWood;
    private Text amntOre;
    private Text amntWool;
    private Text amntWheat;
    private Text amntStone;
    private Text nameWood;
    private Text nameOre;
    private Text nameWool;
    private Text nameWheat;
    private Text nameStone;

    public Text getWoodName() {
	nameWood = new Text(TranslationManager.translate("game.resourcename.wood"));
	nameWood.setFont(ViewMaster.FONT);
	nameWood.setFill(Color.WHITE);
	nameWood.setStroke(Color.BLACK);
	return nameWood;
    };

    public Text getStoneName() {
	nameStone = new Text(TranslationManager.translate("game.resourcename.stone"));
	nameStone.setFont(ViewMaster.FONT);
	nameStone.setFill(Color.WHITE);
	nameStone.setStroke(Color.BLACK);
	return nameStone;
    };

    public Text getOreName() {
	nameOre = new Text(TranslationManager.translate("game.resourcename.ore"));
	nameOre.setFont(ViewMaster.FONT);
	nameOre.setFill(Color.WHITE);
	nameOre.setStroke(Color.BLACK);
	return nameOre;
    };

    public Text getWoolName() {
	nameWool = new Text(TranslationManager.translate("game.resourcename.wool"));
	nameWool.setFont(ViewMaster.FONT);
	nameWool.setFill(Color.WHITE);
	nameWool.setStroke(Color.BLACK);
	return nameWool;
    };

    public Text getWheatName() {
	nameWheat = new Text(TranslationManager.translate("game.resourcename.wheat"));
	nameWheat.setFont(ViewMaster.FONT);
	nameWheat.setFill(Color.WHITE);
	nameWheat.setStroke(Color.BLACK);
	return nameWheat;
    };

    public Text getWoodText() {
	amntWood = new Text("0");
	amntWood.setFont(ViewMaster.TITLE_FONT);
	amntWood.setFill(Color.WHITE);
	amntWood.setStroke(Color.BLACK);
	return amntWood;
    }

    public Text getOreText() {
	amntOre = new Text("0");
	amntOre.setFont(ViewMaster.TITLE_FONT);
	amntOre.setFill(Color.WHITE);
	amntOre.setStroke(Color.BLACK);
	return amntOre;
    }

    public Text getWoolText() {
	amntWool = new Text("0");
	amntWool.setFont(ViewMaster.TITLE_FONT);
	amntWool.setFill(Color.WHITE);
	amntWool.setStroke(Color.BLACK);
	return amntWool;
    }

    public Text getWheatText() {
	amntWheat = new Text("0");
	amntWheat.setFont(ViewMaster.TITLE_FONT);
	amntWheat.setFill(Color.WHITE);
	amntWheat.setStroke(Color.BLACK);
	return amntWheat;
    }

    public Text getStoneText() {
	amntStone = new Text("0");
	amntStone.setFont(ViewMaster.TITLE_FONT);
	amntStone.setFill(Color.WHITE);
	amntStone.setStroke(Color.BLACK);
	return amntStone;
    }

    public Node getCardPlank() {
	if (cardPlank == null) {
	    cardPlank = new ImageView("img/game/kaart_plank.png");
	}
	return cardPlank;
    }

    /**
     * Gets the resource for the wood card
     * 
     * @return image of the wood card
     */
    public Node getWoodCard() {
	woodCard = new ImageView("img/cards/kaart_hout.png");
	woodCard.setFitHeight(170);
	woodCard.setFitWidth(113.5);
	return woodCard;
    }

    /**
     * Gets the resource for the stone card
     * 
     * @return image of the stone card
     */
    public Node getStoneCard() {
	stoneCard = new ImageView("img/cards/kaart_steen.png");
	stoneCard.setFitHeight(170);
	stoneCard.setFitWidth(113.5);
	return stoneCard;
    }

    /**
     * Gets the resource for the wool card
     * 
     * @return image of the wool card
     */
    public Node getWoolCard() {
	woolCard = new ImageView("img/cards/kaart_schaap.png");
	woolCard.setFitHeight(170);
	woolCard.setFitWidth(113.5);
	return woolCard;
    }

    /**
     * Gets the resource for the wheat card
     * 
     * @return image of the wheat card
     */
    public Node getWheatCard() {
	wheatCard = new ImageView("img/cards/kaart_graan.png");
	wheatCard.setFitHeight(170);
	wheatCard.setFitWidth(113.5);
	return wheatCard;
    }

    /**
     * Gets the resource for the ore card
     * 
     * @return image of the ore card
     */
    public Node getOreCard() {
	oreCard = new ImageView("img/cards/kaart_erts.png");
	oreCard.setFitHeight(170);
	oreCard.setFitWidth(113.5);
	return oreCard;
    }

    public void updateTranslation() {
	nameOre.setText(TranslationManager.translate("game.resourcename.ore"));
	nameWood.setText(TranslationManager.translate("game.resourcename.wood"));
	nameStone.setText(TranslationManager.translate("game.resourcename.stone"));
	nameWheat.setText(TranslationManager.translate("game.resourcename.wheat"));
	nameWool.setText(TranslationManager.translate("game.resourcename.wool"));

    }

}
