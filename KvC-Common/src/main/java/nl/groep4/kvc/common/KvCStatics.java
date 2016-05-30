package nl.groep4.kvc.common;

public class KvCStatics {

    /**
     * REGEX: ipv4 adress or a (subd)domain name *including case or 'localhost'
     */
    public static final String REGEX_IP = "((?:[0-9]{1,3}\\.){3}[0-9]{1,3})|(([a-zA-Z0-9|-]+\\.)*[a-zA-Z0-9|-]+\\.[a-zA-Z]+)|(localhost)";

    /** REGEX: only numbers */
    public static final String NUMERIC = "[0-9]";

    /** REGEX: only characters, capital charcaters or numbers */
    public static final String USERNAME = "[a-zA-Z0-9]";
}
