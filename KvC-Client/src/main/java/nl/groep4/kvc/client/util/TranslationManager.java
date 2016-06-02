package nl.groep4.kvc.client.util;

import java.io.BufferedReader;
import java.io.IOException;

import jdk.nashorn.api.scripting.URLReader;

public class TranslationManager {

    public static void main(String[] args) {
	setLanguage("en-EN");

    }

    public static void setLanguage(String languageKey) {
	try {
	    BufferedReader br = new BufferedReader(
		    new URLReader(TranslationManager.class.getResource("/lang/" + languageKey + ".yml")));
	    String s = br.readLine();
	    String[] parts = s.split(":");
	    String part1 = parts[0];
	    String part2 = parts[1];

	    String s2 = TranslationManager.translate("lobby.shield.title");
	    if (s2 == part1) {
		System.out.println(part2);
	    }

	    try {
		String x;
		while ((x = br.readLine()) != null) {
		    // printing out each line in the file
		    System.out.println(x);
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	} catch (Exception e) {
	    System.out.println(e);
	    e.printStackTrace();
	}

    }

    public static String translate(String key) {

	return key;

    }

}
