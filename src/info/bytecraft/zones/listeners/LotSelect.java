package info.bytecraft.zones.listeners;

import info.bytecraft.zones.Rank;
import info.bytecraft.zones.Zones;
import info.bytecraft.zones.info.Zone;
import info.bytecraft.zones.info.ZonePlayers;
import info.bytecraft.zones.info.ZoneVector;

import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class LotSelect implements Listener{
	private final Zones plugin;
	public LotSelect(Zones instance){
		plugin = instance;
	}
	
	public static HashMap<Player,Location> border1 = new HashMap<Player,Location>();
	public static HashMap<Player,Location> border2 = new HashMap<Player,Location>();
	
	@EventHandler
	public void onSelect(PlayerInteractEvent event){
		Player player = event.getPlayer();
		if(event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(player.getItemInHand().getType() == Material.WOOD_SWORD){
			Location loc = event.getClickedBlock().getLocation();
			ZoneVector vector = new ZoneVector(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
			List<Zone> zones = plugin.getDatabase().find(Zone.class).where().ieq("worldName", loc.getWorld().getName()).findList();
			for(Zone zone: zones){
				if(zone.contains(vector)){
					List<ZonePlayers> players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("zoneName", zone.getName()).findList();
					for(ZonePlayers other: players){
						if(other != null && other.getRank() == Rank.OWNER){
					if(event.getAction() == Action.LEFT_CLICK_BLOCK){
						if(!border1.containsKey(player)){
							border1.put(player, loc);
							player.sendMessage(ChatColor.AQUA + "Selected first point for a new lot");
						}else{
							border1.remove(player);
							border1.put(player, loc);
							player.sendMessage(ChatColor.AQUA + "Selected first point for a new lot");
						}
					}else if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
						if(!border2.containsKey(player)){
							border2.put(player, loc);
							player.sendMessage(ChatColor.AQUA + "Selected second point for a new lot");
								}else{
									border2.remove(player);
									border2.put(player, loc);
									player.sendMessage(ChatColor.AQUA + "Selected second point for a new lot");
								}
							}
						}
					}
				}
			}
		}
	}
	}
}
