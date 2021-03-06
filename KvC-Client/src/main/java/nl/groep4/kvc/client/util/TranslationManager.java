package nl.groep4.kvc.client.util;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jdk.nashorn.api.scripting.URLReader;
import nl.groep4.kvc.client.view.ExceptionDialog;

/**
 * Filters the right translation and updates all strings that need a
 * translation.
 * 
 * @author Bachir
 * @version 1.1
 */
public class TranslationManager {

    public static final String[] LANGUAGES;
    private static String current;
    private static final List<String> CURRENT_LANGUAGES = new ArrayList<>();

    static {
	LANGUAGES = new String[] { "en-EN", "nl-NL", "fr-FR", "de-DE" };
	setLanguage(LANGUAGES[0]);
    }

    /**
     * Makes List empty. There will be searched for the language. All the lines
     * in that language will be put in the array.
     * 
     * @param languageKey
     *            Language to translate.
     */
    public static void setLanguage(String languageKey) {
	current = languageKey;
	CURRENT_LANGUAGES.clear();
	try {
	    BufferedReader fileReader = new BufferedReader(
		    new URLReader(TranslationManager.class.getResource("/lang/" + languageKey + ".yml")));

	    Scanner scanner = new Scanner(fileReader);
	    while (scanner.hasNextLine()) {
		String line = scanner.nextLine();
		if (line.trim().isEmpty() || line.startsWith("#") || !line.contains(":")) {
		    continue;
		}
		CURRENT_LANGUAGES.add(line);
	    }
	    scanner.close();
	} catch (Exception ex) {
	    ExceptionDialog.warning("error.translation.notfound");
	}
    }

    /**
     * Gets current language.
     * 
     * @return The current language.
     */
    public static String getCurrentLanguage() {
	return current;
    }

    /**
     * Reading the file and filter out the correct translation.
     * 
     * @param key
     *            String which contains a language.
     * @param args
     *            Command-line arguments to execute.
     * @return The correct translation.
     */
    public static String translate(String key, Object... args) {
	for (String translation : CURRENT_LANGUAGES) {
	    String[] translationSplit = translation.split(":", 2);
	    if (translationSplit[0].equals(key)) {
		return String.format(translationSplit[1], args);
	    }
	}
	System.err.println(key);
	return key;
    }
}
