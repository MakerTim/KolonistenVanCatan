package nl.groep4.kvc.client;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import nl.groep4.kvc.common.KvCStatics;

public class RegexTester {

    @Test
    public void ipRegex() {
	// TODO: TEST -> KvCStatics.REGEX_IP
	assertTrue("localhost".matches(KvCStatics.REGEX_IP));
	assertTrue("192.101.130.111".matches(KvCStatics.REGEX_IP));
	assertTrue("1.1.1.1".matches(KvCStatics.REGEX_IP));
	assertTrue("20.20.20.20".matches(KvCStatics.REGEX_IP));
	assertTrue("150.15.1.5".matches(KvCStatics.REGEX_IP));
	// assertTrue("Localhost".matches(KvCStatics.REGEX_IP));
	assertTrue(!" . . . ".matches(KvCStatics.REGEX_IP));
	assertTrue(!"....".matches(KvCStatics.REGEX_IP));
	assertTrue(!"1000.1000.1000.1000".matches(KvCStatics.REGEX_IP));
	assertTrue("google.com".matches(KvCStatics.REGEX_IP));
	assertTrue("www.google.com".matches(KvCStatics.REGEX_IP));
	assertTrue(!"http://www.google.com".matches(KvCStatics.REGEX_IP));
	assertTrue(!"".matches(KvCStatics.REGEX_IP));
	assertTrue("localhost".matches(KvCStatics.REGEX_IP));
	assertTrue("localhost".matches(KvCStatics.REGEX_IP));
	assertTrue("localhost".matches(KvCStatics.REGEX_IP));
	assertTrue("localhost".matches(KvCStatics.REGEX_IP));
	assertTrue("localhost".matches(KvCStatics.REGEX_IP));
	assertTrue("localhost".matches(KvCStatics.REGEX_IP));
	assertTrue("localhost".matches(KvCStatics.REGEX_IP));
    }

}
