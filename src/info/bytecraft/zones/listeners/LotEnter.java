package info.bytecraft.zones.listeners;

import java.util.List;

import info.bytecraft.zones.Zones;
import info.bytecraft.zones.info.Lot;
import info.bytecraft.zones.info.Zone;
import info.bytecraft.zones.info.ZoneVector;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class LotEnter implements Listener{
	private final Zones plugin;
	public LotEnter(Zones instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onEnter(PlayerMoveEvent event){
		Player player = event.getPlayer();
		Location a = event.getFrom();
		Location b = event.getTo();
		ZoneVector to = new ZoneVector((int)a.getX(), (int)a.getY(), (int)a.getZ());
		ZoneVector from = new ZoneVector((int)b.getX(), (int)b.getY(), (int)b.getZ());
		if(to != from){
			List<Zone> zones = plugin.getDatabase().find(Zone.class).where().ieq("worldName", a.getWorld().getName()).findList();
			for(Zone zone: zones){
				if(zone.contains(to)){
				List<Lot> lots = plugin.getDatabase().find(Lot.class).where().ieq("zoneName", zone.getName()).findList();
					for(Lot lot: lots){
						if(lot.contains(to)/* && !lot.contains(from)*/){
							player.sendMessage(ChatColor.AQUA + "Welcome to " + ChatColor.GOLD + lot.getLotName());
						}/*else if(lot.contains(from) && !lot.contains(to)){
							player.sendMessage(ChatColor.AQUA + "You have left " + ChatColor.GOLD + lot.getLotName());
						}*/
					}
				}
			}
		}
	}

}
