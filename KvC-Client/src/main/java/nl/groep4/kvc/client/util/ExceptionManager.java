package nl.groep4.kvc.client.util;

import java.rmi.AccessException;
import java.rmi.ConnectException;
import java.rmi.ConnectIOException;
import java.rmi.MarshalException;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.ServerError;
import java.rmi.ServerException;
import java.rmi.StubNotFoundException;
import java.rmi.UnexpectedException;

import nl.groep4.kvc.client.view.ExceptionDialog;
import nl.groep4.kvc.client.view.ViewMaster;
import nl.groep4.kvc.client.view.scene.SceneLogin;

/**
 * Manages errors. Displays an exception stack trace.
 * 
 * @author Tim
 * @version 1.1
 */
public class ExceptionManager {

    /**
     * Gives a warning and an exception stack trace.
     * 
     * @param rex
     *            The RemoteException.
     */
    public static void handleRemoteException(RemoteException rex) {
	if (rex instanceof ConnectException || rex instanceof ConnectIOException) {
	    ExceptionDialog.warning("error.connect.notfound");
	} else if (rex instanceof MarshalException || rex instanceof NoSuchObjectException
		|| rex instanceof StubNotFoundException || rex instanceof AccessException) {
	    ExceptionDialog.warning("error.version.diffrence");
	} else if (rex instanceof ServerError || rex instanceof ServerException || rex instanceof UnexpectedException) {
	    ExceptionDialog.warning("error.server.error");
	} else {
	    ExceptionDialog.warning("error.remote.unkown");
	}
	rex.printStackTrace();
	ViewMaster.setScene(new SceneLogin());
    }
}
