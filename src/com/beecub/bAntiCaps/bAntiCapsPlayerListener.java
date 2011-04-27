package com.beecub.bAntiCaps;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;


public class bAntiCapsPlayerListener extends PlayerListener {
	private final bAntiCaps plugin;

	public bAntiCapsPlayerListener(bAntiCaps instance) {
		plugin = instance;
	}
	
	public void onPlayerChat(PlayerChatEvent event) {
		Player player = event.getPlayer();
		String message = event.getMessage();
		String ch;
		
		int countChars = 0;
		int countCharsCaps = 0;
		int countWords = 1;
		
		countChars = message.length();
		
		if(countChars > 0) {
			for(int i = 0; i < countChars; i++) {
				char c = message.charAt(i);
				ch = Character.toString(c);			
				if(ch.matches("[A-Z]")) {
					countCharsCaps++;
				}
				if(c == ' ') {
					countWords++;
				}
			}
			if(countWords >= plugin.wordcount) {
				if(100/countChars*countCharsCaps >= plugin.percentage) {
					if(plugin.tolowercase) {
						message = message.toLowerCase();
						event.setMessage(message);
					}
					if(plugin.message != null || plugin.message != "") {
						bChat.sendMessageToPlayer(player, plugin.message);
					}
					if(plugin.kick){
						player.kickPlayer(plugin.message);
					}
				}
			}
		}
	}
}