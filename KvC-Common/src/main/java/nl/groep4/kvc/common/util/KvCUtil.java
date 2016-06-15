package nl.groep4.kvc.common.util;

import java.rmi.RemoteException;

import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.common.interfaces.Updatable;

public class KvCUtil {

    public static long ping(Player pl) {
	long time = System.currentTimeMillis();
	try {
	    if (pl != null) {
		Updatable<?> updateable = pl.getUpdateable();
		updateable.testConnection();
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
