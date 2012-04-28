package info.bytecraft.zones.listeners;

import java.util.List;

import info.bytecraft.zones.Zones;
import info.bytecraft.zones.info.Lot;
import info.bytecraft.zones.info.LotPlayers;
import info.bytecraft.zones.info.Zone;
import info.bytecraft.zones.info.ZoneVector;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class LotBuild implements Listener{
	private final Zones plugin;
	public LotBuild(Zones instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event){
		Player player = event.getPlayer();
		Location loc = event.getBlock().getLocation();
		ZoneVector vector = new ZoneVector(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		for(Zone zone: plugin.getDatabase().find(Zone.class).where().ieq("worldName", player.getWorld().getName()).findList()){
			if(zone.contains(vector)){
				for(Lot lot: plugin.getDatabase().find(Lot.class).where().ieq("zoneName", zone.getName()).findList()){
					if(lot.contains(vector)){
						for(LotPlayers players: plugin.getDatabase().find(LotPlayers.class).where().ieq("zoneName", zone.getName()).ieq("lotName", lot.getLotName()).findList()){
							if(players != null){
							if(!players.getPlayerName().equalsIgnoreCase(player.getName())){
								event.setCancelled(true);
								player.sendMessage(ChatColor.RED + "You are not allowed to break blocks in the lot " + lot.getLotName() + " in the zone " + zone.getName());
								player.setFireTicks(100);
								}
							}else{
								event.setCancelled(true);
								player.sendMessage(ChatColor.RED + "You are not allowed to break blocks in the lot " + lot.getLotName() + " in the zone " + zone.getName());
								player.setFireTicks(100);
							}
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onBuild(BlockPlaceEvent event){
		Player player = event.getPlayer();
		Location loc = event.getBlock().getLocation();
		ZoneVector vector = new ZoneVector(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		List<Zone> zones = plugin.getDatabase().find(Zone.class).where().ieq("worldName", loc.getWorld().getName()).findList();
		for(Zone zone: zones){
			if(zone.contains(vector)){
				List<Lot> lots = plugin.getDatabase().find(Lot.class).where().ieq("zoneName", zone.getName()).findList();
				for(Lot lot: lots){
					if(lot.contains(vector)){
						for(LotPlayers players: plugin.getDatabase().find(LotPlayers.class).where().ieq("zoneName", zone.getName()).ieq("lotName", lot.getLotName()).findList()){
							if(players != null){
								if(!player.getName().equalsIgnoreCase(players.getPlayerName())){
									event.setCancelled(true);
									player.sendMessage(ChatColor.RED + "You are not allowed to place blocks in the lot " + lot.getLotName() + " in the zone " + zone.getName());
									player.setFireTicks(100);
									return;
								}
							}else{
								event.setCancelled(true);
								player.sendMessage(ChatColor.RED + "You are not allowed to place blocks in the lot " + lot.getLotName() + " in the zone " + zone.getName());
								player.setFireTicks(100);
								return;
							}
						}
					}
				}
			}
		}
	}
}
