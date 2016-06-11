package nl.groep4.kvc.server.console;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Scanner;

import nl.groep4.kvc.server.ServerStarter;

public class ArgumentParser {

    private String cmd;
    private String[] args;

    public ArgumentParser(String cmd, String[] args) throws Throwable {
	this.cmd = cmd;
	this.args = args;
	parse();
    }

    public void parse() throws Throwable {
	switch (cmd.toLowerCase()) {
	case "exit":
	    System.exit(0);
	    break;
	case "":
	    System.out.println("No command given.");
	    break;
	case "list":
	    list();
	    break;
	case "kick":
	    kick();
	    break;
	}
    }

    private void kick() throws RemoteException {
	if (args.length == 0) {
	    System.out.println("Need a playername to be kicked");
	    return;
	}
	ServerStarter.getLobby().getPlayers().stream().filter(pl -> {
	    try {
		return pl.getUsername().equals(args[0]);
	    } catch (RemoteException e) {
	    }
	    return false;
	}).forEach(pl -> {
	    try {
		ServerStarter.getLobby().disconnect(pl);
	    } catch (RemoteException e) {
	    }
	});
	System.out.println("Kicked player with the username '" + args[0] + "'");
    }

    private void list() throws RemoteException {
	System.out.printf("Current players online: [%d]\n\t%s\n", ServerStarter.getLobby().getPlayers().size(),
		ServerStarter.getLobby().getPlayers());
    }

    public static void startParser() {
	String str;
	Scanner scanner = new Scanner(System.in);
	while ((str = scanner.nextLine()) != null) {
	    String[] args = str.split(" ", 2);
	    String cmd = args[0];
	    if (args.length > 1) {
		args = args[1].split(" ");
	    } else {
		args = new String[0];
	    }
	    try {
		new ArgumentParser(cmd, args);
	    } catch (Throwable thr) {
		System.err.printf("There wass a exception with the command %s %s\n\t%s\n", cmd, Arrays.toString(args),
			thr.toString());
	    }
	}
	scanner.close();
    }

}
