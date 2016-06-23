package nl.groep4.kvc.server.console;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import nl.groep4.kvc.common.KvCStatics;
import nl.groep4.kvc.common.enumeration.Resource;
import nl.groep4.kvc.common.interfaces.KolonistenVanCatan;
import nl.groep4.kvc.common.interfaces.Player;
import nl.groep4.kvc.server.ServerStarter;
import nl.groep4.kvc.server.util.ConnectionUtil;

public class ArgumentParser {

    private String cmd;
    private String[] args;

    public ArgumentParser(String cmd, String[] args) throws Throwable {
	this.cmd = cmd;
	this.args = args;
    }

    public void parse() throws Throwable {
	switch (cmd.toLowerCase()) {
	case "exit":
	    exit();
	    break;
	case "":
	    System.out.println("No command given.");
	    break;
	case "list":
	    list();
	    break;
	case "clean":
	    clean();
	    break;
	case "kick":
	    kick();
	    break;
	case "give":
	    give();
	    break;
	case "trigger":
	    trigger();
	    break;
	}
    }

    private void give() throws RemoteException {
	if (args.length != 3) {
	    System.err.println("not enough arguments, needed 3");
	    return;
	}
	Player pl = null;
	for (Player player : ServerStarter.getLobby().getGame().getPlayers()) {
	    if (player.getUsername().equalsIgnoreCase(args[0])) {
		pl = player;
		break;
	    }
	}
	if (pl == null) {
	    System.err.printf("Player '%s' not found.\n", args[0]);
	    return;
	}
	Resource resource = null;
	for (Resource res : Resource.values()) {
	    if (res.name().equalsIgnoreCase(args[1])) {
		resource = res;
		break;
	    }
	}
	if (resource == null) {
	    System.err.printf("Resource '%s' not found.\n", args[1]);
	    return;
	}
	if (!args[2].matches(KvCStatics.NUMERIC + "+")) {
	    System.err.printf("Argument 3 needs to be a number, not '%s'.\n", args[2]);
	    return;
	}
	int amount = Integer.parseInt(args[2]);
	if (amount < 0) {
	    System.err.println("Cant give negative resources");
	    return;
	}
	KolonistenVanCatan kvc = ServerStarter.getLobby().getGame();
	pl.giveResource(resource, amount);
	kvc.updateResources();
	System.out.printf("Gave player '%s' %s %d.\n", pl.getUsername(), resource.name(), amount);
    }

    private void trigger() throws RemoteException {
	ServerStarter.getLobby().getGame().throwDices();
    }

    private void exit() throws RemoteException {
	clean();
	for (Player pl : new ArrayList<>(ServerStarter.getLobby().getPlayers())) {
	    pl.getUpdateable().close("closed");
	    ServerStarter.getLobby().disconnect(pl);
	}
	System.exit(0);
    }

    private void list() throws RemoteException {
	System.out.printf("Current players online: [%d]\n\t%s\n", ServerStarter.getLobby().getPlayers().size(),
		ServerStarter.getLobby().getPlayers());
    }

    private void clean() throws RemoteException {
	ConnectionUtil.cleanup(ServerStarter.getLobby().getPlayers(), pl -> {
	});
	System.out.println("Cleaned the disconnected players.");
    }

    private void kick() throws RemoteException {
	if (args.length == 0) {
	    System.out.println("Need a playername to be kicked");
	    return;
	}
	for (Player pl : ServerStarter.getLobby().getPlayers()) {
	    if (pl.getUsername().equals(args[0])) {
		pl.getUpdateable().close("");
		ServerStarter.getLobby().disconnect(pl);
	    }
	}
	System.out.println("Kicked player with the username '" + args[0] + "'");
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
		new ArgumentParser(cmd, args).parse();
	    } catch (Throwable thr) {
		System.err.printf("There wass a exception with the command %s %s\n\t%s\n", cmd, Arrays.toString(args),
			thr.toString());
	    }
	}
	scanner.close();
    }

}
