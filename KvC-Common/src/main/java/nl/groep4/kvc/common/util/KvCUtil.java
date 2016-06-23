package nl.groep4.kvc.common.util;

import java.rmi.RemoteException;

import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Updatable;

/**
 * Retrieves speed of ping with the server.
 * 
 * @author Tim
 * @version 1.0
 */
public class KvCUtil {

    /**
     * Generates ping for selected player in milliseconds.
     * 
     * @param pl
     *            The player which the ping belongs.
     * @return Current ping time in millisecond.
     */
    public static long ping(Player pl) {
	long time = System.currentTimeMillis();
	try {
	    if (pl != null) {
		Updatable<?> updateable = pl.getUpdateable();
		updateable.testConnection();
	    } else {
		return -1;
	    }
	} catch (Exception ex) {
	    try {
		pl.registerUpdateable(null);
	    } catch (RemoteException rex) {
		rex.printStackTrace();
	    }
	    System.err.println("KvCUtil.ping => " + ex);
	}
	return System.currentTimeMillis() - time;
    }

}
