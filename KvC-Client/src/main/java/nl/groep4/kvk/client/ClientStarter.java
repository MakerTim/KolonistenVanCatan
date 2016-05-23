package nl.groep4.kvk.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import nl.groep4.kvk.client.rmi.Client;
import nl.groep4.kvk.common.KolonistenVanCatan;

public class ClientStarter {

	public static void main(String[] args) {
		try {
			System.out.println("Getting access to the registry");

			Registry registry = LocateRegistry.getRegistry("127.0.0.1");
			System.out.println("Getting the Calculator stub from registry");
			KolonistenVanCatan c = (KolonistenVanCatan) registry.lookup("kvc");

			System.out.println("Performing arithmetics");

			c.registerClient(new Client());
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}

}
