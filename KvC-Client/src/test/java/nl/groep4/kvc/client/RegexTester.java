package nl.groep4.kvc.client;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import nl.groep4.kvc.common.KvCStatics;

public class RegexTester {

    @Test
    public void ipRegex() {
	// TODO: TEST -> KvCStatics.REGEX_IP
	assertTrue("localhost".matches(KvCStatics.REGEX_IP));
    }

}
