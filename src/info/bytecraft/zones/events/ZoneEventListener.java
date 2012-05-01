package info.bytecraft.zones.events;

import info.bytecraft.zones.Zones;
import info.bytecraft.zones.info.Zone;
import info.bytecraft.zones.info.ZoneVector;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class ZoneEventListener implements Listener {
	private static Zones plugin;
	public ZoneEventListener(Zones instance){
		plugin = instance;
	}
	
	
	@EventHandler
	public void onMove(PlayerMoveEvent event){
		Player player = event.getPlayer();
		Location a = event.getTo();
		Location b = event.getFrom();
		ZoneVector to = new ZoneVector(a.getBlockX(), a.getBlockY(), a.getBlockZ());
		ZoneVector from = new ZoneVector(b.getBlockX(), b.getBlockY(), b.getBlockZ());
		for(Zone zone: plugin.getDatabase().find(Zone.class).where().findList()){
			if(zone.contains(to) && !zone.contains(from)){
				if(!event.isCancelled()){
				plugin.getServer().getPluginManager().callEvent(new ZoneEnterEvent(player,zone));
				}
			}else if(zone.contains(from) && !zone.contains(to)){
				if(!event.isCancelled()){
				plugin.getServer().getPluginManager().callEvent(new ZoneExitEvent(player,zone));
				}
			}
		}
	}
}
