package nl.groep4.kvc.client.util;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jdk.nashorn.api.scripting.URLReader;

public class TranslationManager {

    private static final List<String> currentLanguage = new ArrayList<>();

    static {
	setLanguage("en-EN");
    }

    public static void setLanguage(String languageKey) {
	currentLanguage.clear();
	BufferedReader fileReader = new BufferedReader(
		new URLReader(TranslationManager.class.getResource("/lang/" + languageKey + ".yml")));

	Scanner scanner = new Scanner(fileReader);
	while (scanner.hasNextLine()) {
	    String line = scanner.nextLine();
	    if (line.trim().isEmpty() || line.startsWith("#") || !line.contains(":")) {
		continue;
	    }
	    currentLanguage.add(line);
	}
	scanner.close();
    }

    public static String translate(String key, Object... args) {
	for (String translation : currentLanguage) {
	    if (translation.startsWith(key)) {
		return String.format(translation.split(":", 2)[1], args);
	    }

	}
	return key;
    }
}
