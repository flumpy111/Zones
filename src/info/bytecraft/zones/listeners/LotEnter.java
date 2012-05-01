package info.bytecraft.zones.listeners;

import info.bytecraft.zones.Zones;
import info.bytecraft.zones.events.LotEnterEvent;
import info.bytecraft.zones.events.LotExitEvent;
import info.bytecraft.zones.info.Lot;
import info.bytecraft.zones.info.Zone;
import info.bytecraft.zones.info.ZoneVector;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class LotEnter implements Listener{
	
	@EventHandler
	public void onEnter(PlayerMoveEvent event){
		Player player = event.getPlayer();
		Location a = event.getFrom();
		Location b = event.getTo();
		ZoneVector to = new ZoneVector((int)a.getX(), (int)a.getY(), (int)a.getZ());
		ZoneVector from = new ZoneVector((int)b.getX(), (int)b.getY(), (int)b.getZ());
		if(to != from){
			for(Zone zone: Zones.getZones()){
				if(zone.contains(to)){
					for(Lot lot: zone.getLots()){
						if(lot.contains(to) && !lot.contains(from)){
							player.sendMessage(ChatColor.RED+"<"+zone.getName()+"> "+ChatColor.RED + "You have entered the lot: " + lot.getLotName());
							Bukkit.getPluginManager().callEvent(new LotEnterEvent(player, zone, lot));
						}else if(lot.contains(from) && !lot.contains(to)){
							player.sendMessage(ChatColor.RED+"<"+zone.getName()+"> "+ "You have left " + lot.getLotName());
							Bukkit.getPluginManager().callEvent(new LotExitEvent(player, zone, lot));
						}
					}
				}
			}
		}
	}
}
