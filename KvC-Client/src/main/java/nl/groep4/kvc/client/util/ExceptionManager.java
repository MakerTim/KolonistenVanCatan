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

public class ExceptionManager {

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
	ViewMaster.setScene(new SceneLogin());
    }
}
