package nl.groep4.kvc.client.util;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jdk.nashorn.api.scripting.URLReader;
import nl.groep4.kvc.client.view.ExceptionDialog;

/**
 * 
 * @author Bachir
 * @version 1.0
 */
public class TranslationManager {

    public static final String[] LANGUAGES;
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
     */
    public static void setLanguage(String languageKey) {
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
     * Reading the file and filter out the correct translation.
     * 
     * @param key
     * @param args
     * @return the correct translation
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
