package com.beecub.bAntiCaps;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.entity.Player;

public class bChat {
	
	static Logger log = Logger.getLogger("Minecraft");
	static List<String> Colors = new LinkedList<String>();
	static Server server;
	
	@SuppressWarnings("static-access")
	public bChat(Server server) {
		Colors.add("&black&");		Colors.add("&darkblue&");	Colors.add("&darkgreen&");
		Colors.add("&darkaqua&");	Colors.add("&darkred&");	Colors.add("&purple&");
		Colors.add("&gold&");		Colors.add("&gray&");		Colors.add("&darkgray&");
		Colors.add("&blue&");		Colors.add("&green&");		Colors.add("&aqua&");
		Colors.add("&red&");		Colors.add("&pink&");		Colors.add("&yellow&");
		Colors.add("&white&");
		Colors.add("&0");	Colors.add("&1");	Colors.add("&2");	Colors.add("&3");	Colors.add("&4");
		Colors.add("&5");	Colors.add("&6");	Colors.add("&7");	Colors.add("&8");	Colors.add("&9");
		Colors.add("&a");	Colors.add("&b");	Colors.add("&c");	Colors.add("&d");	Colors.add("&e");
		Colors.add("&f");
		this.server = server;
	}
	
	static String replaceColorCodes(String line) {
		line = replaceTags(line);
		line = line.replaceAll("(&([a-f0-9]))", "\u00A7$2");
		return line;
	}
	static String replaceTags(String line) {
		line = line.replaceAll("&black&", "&0");
		line = line.replaceAll("&darkblue&", "&1");
		line = line.replaceAll("&darkgreen&", "&2");
		line = line.replaceAll("&darkaqua&", "&3");
		line = line.replaceAll("&darkred&", "&4");
		line = line.replaceAll("&purple&", "&5");
		line = line.replaceAll("&gold&", "&6");
		line = line.replaceAll("&gray&", "&7");
		line = line.replaceAll("&darkgray&", "&8");
		line = line.replaceAll("&blue&", "&9");
		line = line.replaceAll("&green&", "&a");
		line = line.replaceAll("&aqua&", "&b");
		line = line.replaceAll("&red&", "&c");
		line = line.replaceAll("&pink&", "&d");
		line = line.replaceAll("&yellow&", "&e");
		line = line.replaceAll("&white&", "&f");
		return line;
	}

	static String rainbowText(String line) {
		String newline = line;
		for(int i = 1; i<= line.length(); i++) {
			//newline = line.
			newline += (String) line.subSequence(i, i + 1);
			Colors.get(i);
		}
		return newline;
	}
	
	public static void broadcastMessage(String message) {
		message = bChat.replaceColorCodes(message);
		log.info( "[bColoredChat] " + message);
		server.broadcastMessage(message);
	}
	public static void sendMessageToPlayer(Player player, String message) {
		message = bChat.replaceColorCodes(message);
		player.sendMessage(message);
	}
	public static void sendMessageToServer(String message) {
		message = bChat.replaceColorCodes(message);
		log.info(message);
	}
}
