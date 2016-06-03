package nl.groep4.kvc.client.view.elements;

import javafx.scene.image.Image;
import nl.groep4.kvc.client.util.TranslationManager;

public class LanguageButton extends TexturedButton {

    private static int selectedLanguage = 0;

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
