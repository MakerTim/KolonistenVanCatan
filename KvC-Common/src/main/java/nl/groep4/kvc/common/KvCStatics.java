package nl.groep4.kvc.common;

public class KvCStatics {
    /**
     * Name of lobby stored in LOBBY_KEY to connect with
     */
    public static final String LOBBY_KEY = "KvCLobby";

    /**
     * REGEX: ipv4 adress or a domain name (with sub) or a dynamic name
     */
    public static final String REGEX_IP = "((?:[0-9]{1,3}\\.){3}[0-9]{1,3})|(([a-zA-Z0-9|-]+\\.)*[a-zA-Z0-9|-]+\\.[a-zA-Z]+)|(([a-zA-Z\\-])+)";

    /** REGEX: only numbers */
    public static final String NUMERIC = "[0-9]";

    /** REGEX: only characters, capital charcaters or numbers */
    public static final String USERNAME = "[a-zA-Z0-9]";
}
