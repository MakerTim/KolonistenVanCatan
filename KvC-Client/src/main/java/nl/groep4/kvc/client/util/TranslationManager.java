package nl.groep4.kvc.client.util;

public class TranslationManager {

    public static String translate(String key, Object... args) {
	return String.format(key, args);
    }

}
