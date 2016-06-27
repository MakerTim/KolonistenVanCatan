package nl.groep4.kvc.common;

/**
 * This class shows the input that is being used in the lobby.
 * 
 * @author Tim version: 1.0
 *
 */
public class KvCStatics {
    /**
     * 
     * 
     */
    public static final String LOBBY_KEY = "KvCLobby";

    /**
     * REGEX: ipv4 address or a domain name (with sub) or a dynamic name.
     */
    public static final String REGEX_IP = "((?:[0-9]{1,3}\\.){3}[0-9]{1,3})|(([a-zA-Z0-9|-]+\\.)*[a-zA-Z0-9|-]+\\.[a-zA-Z]+)|(([a-zA-Z\\-])+)";

    /**
     * REGEX: only numbers
     * 
     */
    public static final String NUMERIC = "[0-9]";

    /**
     * REGEX: only characters, capital characters or numbers.
     * 
     */
    public static final String USERNAME = "[a-zA-Z0-9]";

}
