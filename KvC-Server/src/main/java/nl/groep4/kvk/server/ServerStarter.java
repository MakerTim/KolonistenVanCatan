package nl.groep4.kvk.server;

import nl.groep4.kvk.server.rmi.RmiServer;

public class ServerStarter {

	public static void main(String[] args) {
		int port = 1099;
		if (args.length >= 1 && args[0].matches("%d")) {
			port = Integer.parseInt(args[0]);
		}
		RmiServer.init(port);
	}

}
