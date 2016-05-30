package nl.groep4.kvc.common;

public class KvCStatcis {

    /**
     * REGEX: ipv4 adress or a (subd)domain name *including case or 'localhost'
     */
    public static final String REGEX_IP = "((?:[0-9]{1,3}\\.){3}[0-9]{1,3})|(([a-zA-Z0-9|-]+\\.)*[a-zA-Z0-9|-]+\\.[a-zA-Z]+)|(localhost)";

}
