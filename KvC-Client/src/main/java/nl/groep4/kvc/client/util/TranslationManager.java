package nl.groep4.kvc.client.util;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jdk.nashorn.api.scripting.URLReader;

public class TranslationManager {

    private static final List<String> CURRENT_LANGUAGES = new ArrayList<>();

    static {
	setLanguage("en-EN");
    }

    public static void setLanguage(String languageKey) {
	CURRENT_LANGUAGES.clear();
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
    }

    public static String translate(String key, Object... args) {
	for (String translation : CURRENT_LANGUAGES) {
	    String[] translationSplit = translation.split(":", 2);
	    if (translationSplit[0].equals(key)) {
		return String.format(translationSplit[1], args);
	    }
	}
	return key;
    }
}
