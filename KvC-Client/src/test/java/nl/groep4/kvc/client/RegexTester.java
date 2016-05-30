package nl.groep4.kvc.client;

import static org.junit.Assert.*;

import org.junit.Test;

import nl.groep4.kvc.common.KvCStatcis;

public class RegexTester {

    @Test
    public void ipRegex() {
	// TODO: TEST -> KvCStatcis.REGEX_IP
	assertTrue("localhost".matches(KvCStatcis.REGEX_IP));
    }

}
