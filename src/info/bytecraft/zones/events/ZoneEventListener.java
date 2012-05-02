package info.bytecraft.zones.events;

import info.bytecraft.zones.Zones;
import info.bytecraft.zones.info.Zone;
import info.bytecraft.zones.info.ZoneVector;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
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
	
	@EventHandler
	public void onBreak(BlockBreakEvent event){
		Block block = event.getBlock(); Location loc = block.getLocation();
		Player player = event.getPlayer();
		ZoneVector vector = new ZoneVector(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		for(Zone zone: Zones.getZones()){
			if(zone.contains(vector)){
				plugin.getServer().getPluginManager().callEvent(new ZoneBlockBreakEvent(player, zone, block));
			}
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event){
		Block block = event.getBlock(); Location loc = block.getLocation();
		Player player = event.getPlayer();
		ZoneVector vector = new ZoneVector(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		for(Zone zone: Zones.getZones()){
			if(zone.contains(vector)){
				plugin.getServer().getPluginManager().callEvent(new ZoneBlockPlaceEvent(player, zone, block));
			}
		}
	}
}
