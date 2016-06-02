package nl.groep4.kvc.client.util;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jdk.nashorn.api.scripting.URLReader;

public class TranslationManager {

    private static final List currentLanguage = new ArrayList();

    public static void main(String[] args) {
	setLanguage("en-EN");

    }

    public static void setLanguage(String languageKey) {

	BufferedReader br = new BufferedReader(
		new URLReader(TranslationManager.class.getResource("/lang/" + languageKey + ".yml")));

	Scanner s;
	s = new Scanner(br);
	while (s.hasNext()) {
	    currentLanguage.add(s.next());
	}
	currentLanguage.clear();
    }

    public static String translate(String key, Object... args) {
	return key;

    }
}
