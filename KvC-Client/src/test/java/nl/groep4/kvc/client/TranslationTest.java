package nl.groep4.kvc.client;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import nl.groep4.kvc.client.util.TranslationManager;

public class TranslationTest {

    @Before
    public void setupLanguage() {
	TranslationManager.setLanguage("en-EN");
    }

    @Test
    public void translateTest() {
	assertEquals("Translating the name of the language", "English", TranslationManager.translate("language"));
    }

    @Test
    public void translateNothing() {
	String randomWord = "jemoederisfuckinglelijk";
	assertEquals("Not existing translation", randomWord, TranslationManager.translate(randomWord));
    }

    @Test
    public void translateWithArgs() {
	for (int i = 0; i < 1000; i++) {
	    assertEquals("Args", "Round: " + i, TranslationManager.translate("game.round", i));
	}
    }

}
