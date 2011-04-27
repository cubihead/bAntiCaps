package com.beecub.bAntiCaps;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

import com.beecub.bAntiCaps.bAntiCaps;
import com.beecub.bAntiCaps.bConfigManager;
//import com.nijiko.permissions.PermissionHandler;
//import com.nijikokun.bukkit.Permissions.Permissions;

import java.util.logging.Logger;


public class bAntiCaps extends JavaPlugin {
	private final bAntiCapsPlayerListener playerListener = new bAntiCapsPlayerListener(this);
	public static Logger log = Logger.getLogger("Minecraft");
	public static PluginDescriptionFile pdfFile;
	public static Configuration conf;
	public int percentage;
	public int wordcount;
	public boolean kick;
	public boolean tolowercase;
	public String message;
//	public static PermissionHandler Permissions;
//	public static boolean permissions = false;

	@SuppressWarnings("static-access")
	public void onEnable() {

		pdfFile = this.getDescription();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Event.Priority.Lowest, this);

		bConfigManager bConfigManager = new bConfigManager(this);
		bConfigManager.load();
		
//		if(setupPermissions()){
//		}
		
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info( "[" + pdfFile.getName() + "]" + " version " + pdfFile.getVersion() + " is enabled!" );
		
	}
	public void onDisable() {
		log.info("[" + pdfFile.getName() + "]" + " version " + pdfFile.getVersion() + " disabled!");
	}
	
	
//	// setup permissions
//	private boolean setupPermissions() {
//		Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");
//		if (bAntiCaps.Permissions == null) {
//			if (test != null) {
//				bAntiCaps.Permissions = ((Permissions)test).getHandler();
//				log.info("[bAntiCaps] Permission system found");
//				permissions = true;
//				return true;
//			}
//			else {
//				//log.info("[bAntiCaps] Permission system not detected, plugin disabled");
//				//this.getServer().getPluginManager().disablePlugin(this);
//				permissions = false;
//				return false;
//			}
//		}
//		return false;
//	}
	
}
