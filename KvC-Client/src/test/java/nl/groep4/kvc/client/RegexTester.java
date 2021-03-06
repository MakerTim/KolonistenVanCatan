package nl.groep4.kvc.client;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import nl.groep4.kvc.common.KvCStatics;

public class RegexTester {

    @Test
    public void ipRegex() {
	assertTrue("localhost".matches(KvCStatics.REGEX_IP));
	assertTrue("192.101.130.111".matches(KvCStatics.REGEX_IP));
	assertTrue("1.1.1.1".matches(KvCStatics.REGEX_IP));
	assertTrue("20.20.20.20".matches(KvCStatics.REGEX_IP));
	assertTrue("150.15.1.5".matches(KvCStatics.REGEX_IP));
	assertTrue(!" . . . ".matches(KvCStatics.REGEX_IP));
	assertTrue(!"....".matches(KvCStatics.REGEX_IP));
	assertTrue(!"1000.1000.1000.1000".matches(KvCStatics.REGEX_IP));
	assertTrue("google.com".matches(KvCStatics.REGEX_IP));
	assertTrue("www.google.com".matches(KvCStatics.REGEX_IP));
	assertTrue(!"http://www.google.com".matches(KvCStatics.REGEX_IP));
	assertTrue(!"".matches(KvCStatics.REGEX_IP));
	assertTrue("LoCaLHoST".matches(KvCStatics.REGEX_IP));
	assertTrue(!"L0c@lH0ST".matches(KvCStatics.REGEX_IP));
	assertTrue("makerpi".matches(KvCStatics.REGEX_IP));
	assertTrue("MakerTim-Laptop".matches(KvCStatics.REGEX_IP));
	assertTrue(!",".matches(KvCStatics.REGEX_IP));
	assertTrue("---".matches(KvCStatics.REGEX_IP));
	assertTrue("makerpie".matches(KvCStatics.REGEX_IP));
	assertTrue("blabla".matches(KvCStatics.REGEX_IP));
    }

    @Test
    public void numericRegex() {
	assertTrue(!"a".matches(KvCStatics.NUMERIC));
	assertTrue(!" ".matches(KvCStatics.NUMERIC));
	assertTrue("1".matches(KvCStatics.NUMERIC));
	assertTrue("2".matches(KvCStatics.NUMERIC));
	assertTrue("3".matches(KvCStatics.NUMERIC));
	assertTrue("4".matches(KvCStatics.NUMERIC));
	assertTrue("5".matches(KvCStatics.NUMERIC));
	assertTrue("6".matches(KvCStatics.NUMERIC));
	assertTrue("7".matches(KvCStatics.NUMERIC));
	assertTrue("8".matches(KvCStatics.NUMERIC));
	assertTrue("9".matches(KvCStatics.NUMERIC));
	assertTrue(!"z".matches(KvCStatics.NUMERIC));
    }

    @Test
    public void usernameRegex() {
	assertTrue("c".matches(KvCStatics.USERNAME));
	assertTrue(!"10".matches(KvCStatics.USERNAME));
	assertTrue("6".matches(KvCStatics.USERNAME));
	assertTrue("f".matches(KvCStatics.USERNAME));
	assertTrue("q".matches(KvCStatics.USERNAME));
	assertTrue("r".matches(KvCStatics.USERNAME));
	assertTrue("p".matches(KvCStatics.USERNAME));
	assertTrue("y".matches(KvCStatics.USERNAME));
	assertTrue(!"-".matches(KvCStatics.USERNAME));
	assertTrue("t".matches(KvCStatics.USERNAME));
    }
}
