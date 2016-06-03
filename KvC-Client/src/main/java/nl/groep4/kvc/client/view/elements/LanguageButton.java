package nl.groep4.kvc.client.view.elements;

import javafx.scene.image.Image;
import nl.groep4.kvc.client.util.TranslationManager;

/**
 * Changes language into another when clicked on button
 * 
 * @author unknown
 * @version 1.0
 */
public class LanguageButton extends TexturedButton {

    private static int selectedLanguage = 0;

    /**
     * Checks when button is clicked and performs action in order to change
     * language
     */
    public LanguageButton() {
	registerClick(() -> {
	    selectedLanguage++;
	    selectedLanguage %= TranslationManager.LANGUAGES.length;
	    TranslationManager.setLanguage(TranslationManager.LANGUAGES[selectedLanguage]);
	});
    }

    @Override
    public Image getTexture() {
	return new Image(String.format("img/etc/%s.png", TranslationManager.LANGUAGES[selectedLanguage]));
    }

    @Override
    public Image getHoverTexture() {
	return new Image(String.format("img/etc/%s.png", TranslationManager.LANGUAGES[selectedLanguage]));
    }

    @Override
    public Image getClickTexture() {
	return new Image(String.format("img/etc/%s.png", TranslationManager.LANGUAGES[selectedLanguage]));
    }

}
