package nl.groep4.kvc.client.util;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import jdk.nashorn.api.scripting.URLReader;

public class TranslationManager {

    private static final List currentLanguage = new ArrayList();
    BufferedReader br = new BufferedReader(new URLReader(TranslationManager.class.getResource("/lang/en-EN.yml")));
    String line;
    // while ((line = reader.readLine()) != null) {

    public static void main(String[] args) {
	setLanguage("en-EN");

    }

    public static void setLanguage(String languageKey) {

	BufferedReader br = new BufferedReader(
		new URLReader(TranslationManager.class.getResource("/lang/" + languageKey + ".yml")));
	currentLanguage.clear();
    }

    public static String translate(String key, Object... args) {
	return key;

    }
}
