package com.beecub.bAntiCaps;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.util.config.Configuration;

public class bConfigManager {
	
	protected static bAntiCaps bAntiCaps;
    protected static Configuration conf;
    protected File confFile;
    static List<String> toChat = new LinkedList<String>();
	
	@SuppressWarnings("static-access")
	public bConfigManager(bAntiCaps bAntiCaps) {
    	this.bAntiCaps = bAntiCaps;

    	File f = new File(bAntiCaps.getDataFolder(), "config.yml");
    	conf = null;

        if (f.exists())
        {
        	conf = new Configuration(f);
        	conf.load();
        }
        else {
        	this.confFile = new File(bAntiCaps.getDataFolder(), "config.yml");
            this.conf = new Configuration(confFile);
            conf.setProperty("capslock.reaction.message", "&6Do not write capslock text!");
            conf.setProperty("capslock.reaction.tolowercase", true);
            conf.setProperty("capslock.reaction.kick", false);
            conf.setProperty("capslock.condition.wordcount", 0);
            conf.setProperty("capslock.condition.charactercount", 6);
            conf.setProperty("capslock.condition.percentage", 40);
            toChat.add("/l");
            toChat.add("/g");
            conf.setProperty("capslock.condition.checkcommands", toChat);
            conf.save();
        }
    }
    
	static void load() {
    	conf.load();
    	bAntiCaps.percentage = conf.getInt("capslock.condition.percentage", 40);
    	bAntiCaps.wordcount = conf.getInt("capslock.condition.wordcount", 0);
    	bAntiCaps.kick = conf.getBoolean("capslock.reaction.kick", false);
    	bAntiCaps.tolowercase = conf.getBoolean("capslock.reaction.tolowercase", true);
    	bAntiCaps.message = conf.getString("capslock.reaction.message", "&6Do not write capslock text!");
    	bAntiCaps.charactercount = conf.getInt("capslock.condition.charactercount", 6);
    	toChat();
    }
	
	static void reload() {
		load();
	}
	
	private static void toChat() {
		toChat.clear();
		toChat = conf.getStringList("capslock.condition.checkcommands", toChat);
	}
	
	public static boolean isToChat(String message) {
		if(toChat.contains(message)) {
			return true;
		}
		return false;
	}
}
