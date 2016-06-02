package nl.groep4.kvc.client.view.elements;

import javafx.scene.image.Image;

public class LanguageButton extends TexturedButton {

    private static final String[] LANGUAGES = { "en-EN", "nl-NL" };
    private static int selectedLanguage = 0;

    public LanguageButton() {
	registerClick(() -> {
	    selectedLanguage++;
	    selectedLanguage %= LANGUAGES.length;
	});
    }

    @Override
    public Image getTexture() {
	return new Image(String.format("img/etc/%s.png", LANGUAGES[selectedLanguage]));
    }

    @Override
    public Image getHoverTexture() {
	return new Image(String.format("img/etc/%s.png", LANGUAGES[selectedLanguage]));
    }

    @Override
    public Image getClickTexture() {
	return new Image(String.format("img/etc/%s.png", LANGUAGES[selectedLanguage]));
    }

}