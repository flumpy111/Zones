package info.bytecraft.zones.listeners;

import java.util.List;

import info.bytecraft.zones.Zones;
import info.bytecraft.zones.info.Zone;
import info.bytecraft.zones.info.ZoneVector;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ZonePlayerFight implements Listener{
	private static Zones plugin;
	public ZonePlayerFight(Zones instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event){
		if(event.getDamager() instanceof Player){
			Player damager = (Player)event.getDamager();
			if(event.getEntity() instanceof Player){
				Player hurt = (Player)event.getEntity();
				Location loc = hurt.getLocation();
				ZoneVector vector = new ZoneVector(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
				List<Zone> zones = plugin.getDatabase().find(Zone.class).where().ieq("worldName", loc.getWorld().getName()).findList();
				for(Zone zone: zones){
					if(zone.contains(vector)){
						if(!zone.isPvpAllowed()){
							event.setCancelled(true);
							damager.sendMessage(ChatColor.RED + "This is not a pvp zone!");
						}
					}else{
						event.setCancelled(true);
						damager.sendMessage(ChatColor.RED + "This is not a pvp zone!");
					}
				}
			}
		}
	}
	
}
