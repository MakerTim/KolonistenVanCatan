package nl.groep4.kvc.common.util;

import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Updatable;

public class KvCUtil {

    public static long ping(Player pl) {
	long time = System.currentTimeMillis();
	try {
	    Updatable<?> updateable = pl.getUpdateable();
	    updateable.testConnection();
	} catch (Exception ex) {
	    System.err.println("KvCUtil.ping => " + ex);
	}
	return System.currentTimeMillis() - time;
    }

}
