package com.beecub.bAntiCaps;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerListener;

public class bAntiCapsPlayerListener extends PlayerListener {
	private final bAntiCaps plugin;

	public bAntiCapsPlayerListener(bAntiCaps instance) {
		plugin = instance;
	}
	
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if (event.isCancelled()) {
            return;
        }
        String message = event.getMessage();
        Player player = event.getPlayer();
        
        String pre;
        int i = message.indexOf(' ');
        if(i < 0) { i = message.length() - 1; }
        
        pre = (String) message.subSequence(0, i);
        message = (String) message.subSequence(i, message.length());
        
        //bAntiCaps.log.info("[bAntiCaps] 0. "+ pre + message);
        // is chat?
        if (bConfigManager.isToChat(pre)) {
            message = pre + Capslock(player, message);
            event.setMessage(message);
            //bAntiCaps.log.info("[bAntiCaps] 1. " + message);
        }
	}
	
	public void onPlayerChat(PlayerChatEvent event) {
	    if (event.isCancelled()) {
            return;
        }
		Player player = event.getPlayer();
		String message = event.getMessage();
		message = Capslock(player, message);
		event.setMessage(message);
	}
	
	public String Capslock(Player player, String message) {
	    String ch;
        int countChars = 0;
        int countCharsCaps = 0;
        int countWords = 1;
        boolean on = true;
        
        if(bAntiCaps.permissions) {
            if(com.beecub.bAntiCaps.bAntiCaps.Permissions.permission(player, "bAntiCaps.AllowCapslock") || player.isOp()) {
                on = false;
            } else {
                on = true;
            }
        }
        else {
            if(player.isOp()) {
                on = false;
            }
            else {
                on = true;
            }
        }
        
        if(on) {
            countChars = message.length();
            if(countChars > 0) {
                if(countChars > plugin.charactercount) {
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
        return message;
	}
}