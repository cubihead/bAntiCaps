package com.beecub.bAntiCaps;

import java.io.File;

import org.bukkit.util.config.Configuration;

public class bConfigManager {
	
	protected static bAntiCaps bAntiCaps;
    protected static Configuration conf;
    protected File confFile;
	
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
            conf.setProperty("capslock.condition.wordcount", 3);
            conf.setProperty("capslock.condition.percentage", 50);
            
            conf.save();
        }
        
    }    
    
	static void load() {
    	conf.load();
    	bAntiCaps.percentage = conf.getInt("capslock.condition.percentage", 50);
    	bAntiCaps.wordcount = conf.getInt("capslock.condition.wordcount", 3);
    	bAntiCaps.kick = conf.getBoolean("capslock.reaction.kick", false);
    	bAntiCaps.tolowercase = conf.getBoolean("capslock.reaction.tolowercase", true);
    	bAntiCaps.message = conf.getString("capslock.reaction.message", "&6Do not write capslock text!");
    }
	
	static void reload() {
		load();
	}
}
