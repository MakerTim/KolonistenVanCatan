package nl.groep4.kvc.client;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import nl.groep4.kvc.common.KvCStatcis;

public class RegexTester {

    @Test
    public void ipRegex() {
	// TODO: TEST -> KvCStatcis.REGEX_IP
	assertTrue("localhost".matches(KvCStatcis.REGEX_IP));
	assertTrue("192.101.130.111".matches(KvCStatcis.REGEX_IP));
	assertTrue("1.1.1.1".matches(KvCStatcis.REGEX_IP));
	assertTrue("20.20.20.20".matches(KvCStatcis.REGEX_IP));
	assertTrue("150.15.1.5".matches(KvCStatcis.REGEX_IP));
	// assertTrue("Localhost".matches(KvCStatcis.REGEX_IP));
	assertTrue(!" . . . ".matches(KvCStatcis.REGEX_IP));
	assertTrue("....".matches(KvCStatcis.REGEX_IP));
	assertTrue("1000.1000.1000.1000".matches(KvCStatcis.REGEX_IP));
	assertTrue("google.com".matches(KvCStatcis.REGEX_IP));
	assertTrue("www.google.com".matches(KvCStatcis.REGEX_IP));
	assertTrue("----".matches(KvCStatcis.REGEX_IP));
	assertTrue("localhost".matches(KvCStatcis.REGEX_IP));
	assertTrue("localhost".matches(KvCStatcis.REGEX_IP));
	assertTrue("localhost".matches(KvCStatcis.REGEX_IP));
	assertTrue("localhost".matches(KvCStatcis.REGEX_IP));
	assertTrue("localhost".matches(KvCStatcis.REGEX_IP));
	assertTrue("localhost".matches(KvCStatcis.REGEX_IP));
	assertTrue("localhost".matches(KvCStatcis.REGEX_IP));
	assertTrue("localhost".matches(KvCStatcis.REGEX_IP));
    }

}
