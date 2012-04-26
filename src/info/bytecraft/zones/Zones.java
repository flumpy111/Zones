package info.bytecraft.zones;

import info.bytecraft.zones.commands.*;
import info.bytecraft.zones.info.*;
import info.bytecraft.zones.listeners.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Zones extends JavaPlugin{
	
	
	@Override
	public void onEnable(){
		registerEvents();
		registerCommands();
		setupZoneDatabase();
		setupLotsDatabase();
		setupLotsPlayersDatabase();
		setupPlayersDatabase();
	}
	
	private void registerEvents(){
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Selector(this), this);
		pm.registerEvents(new ZoneEnter(this), this);
		pm.registerEvents(new ZoneBuild(this), this);
		pm.registerEvents(new ZonePlayerFight(this), this);
		pm.registerEvents(new ZoneMobSpawn(this), this);
		pm.registerEvents(new LotEnter(this), this);
		pm.registerEvents(new LotSelect(this), this);
		pm.registerEvents(new LotBuild(this), this);
	}
	
	private void registerCommands(){
		getCommand("zone").setExecutor(new ZoneCommand(this));
		getCommand("town").setExecutor(new TownCommand(this));
		getCommand("lot").setExecutor(new LotCommand(this));
	}
	
	private void setupZoneDatabase(){
		try{
			getDatabase().find(Zone.class).findRowCount();
		}catch(PersistenceException e){
			installDDL();
		}
	}
	
	private void setupPlayersDatabase(){
		try{
			getDatabase().find(ZonePlayers.class).findRowCount();
		}catch(PersistenceException e){
			installDDL();
		}
	}
	
	private void setupLotsDatabase(){
		try{
			getDatabase().find(Lot.class).findRowCount();
		}catch(PersistenceException e){
			installDDL();
		}
	}
	
	private void setupLotsPlayersDatabase(){
		try{
			getDatabase().find(LotPlayers.class).findRowCount();
		}catch(PersistenceException e){
			installDDL();
		}
	}
	
	@Override
	public List<Class<?>> getDatabaseClasses(){
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(Zone.class);
		list.add(Lot.class);
		list.add(LotPlayers.class);
		list.add(ZonePlayers.class);
		return list;
	}
}