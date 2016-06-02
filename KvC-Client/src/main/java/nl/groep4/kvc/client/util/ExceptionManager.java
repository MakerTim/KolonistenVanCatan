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

public class ExceptionManager {

    public static void handleRemoteException(RemoteException rex) {
	if (rex instanceof ConnectException || rex instanceof ConnectIOException) {
	    ExceptionDialog.warning(TranslationManager.translate("error.connect.notfound.title"),
		    TranslationManager.translate("error.connect.notfound.explanation"),
		    TranslationManager.translate("error.connect.notfound.details"));
	} else if (rex instanceof MarshalException || rex instanceof NoSuchObjectException
		|| rex instanceof StubNotFoundException || rex instanceof AccessException) {
	    ExceptionDialog.warning(TranslationManager.translate("error.version.diffrence.title"),
		    TranslationManager.translate("error.version.diffrence.explanation"),
		    TranslationManager.translate("error.version.diffrence.details"));
	} else if (rex instanceof ServerError || rex instanceof ServerException || rex instanceof UnexpectedException) {
	    ExceptionDialog.warning(TranslationManager.translate("error.server.error.title"),
		    TranslationManager.translate("error.server.error.explanation"),
		    TranslationManager.translate("error.server.error.details"));
	} else {
	    ExceptionDialog.warning(TranslationManager.translate("error.remote.unkown.title"),
		    TranslationManager.translate("error.remote.unkown.explanation"),
		    TranslationManager.translate("error.remote.unkown.details"));
	}
    }

}
