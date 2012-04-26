package info.bytecraft.zones.listeners;

import info.bytecraft.zones.Zones;
import info.bytecraft.zones.info.Zone;
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
import org.bukkit.inventory.ItemStack;

public class Selector implements Listener {
	private final Zones plugin;
	public Selector(Zones instance){
		plugin = instance;
	}

	public static HashMap<Player, Location> border1 = new HashMap<Player, Location>();
	public static HashMap<Player, Location> border2 = new HashMap<Player, Location>();

	@EventHandler
	public void onSelect(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		ItemStack item = player.getItemInHand();
		if (item.getType() != Material.STICK) return;
		if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR)return;
		Location loc = event.getClickedBlock().getLocation();
		ZoneVector vector = new ZoneVector(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		List<Zone> zones = plugin.getDatabase().find(Zone.class).where().ieq("worldName", player.getWorld().getName()).findList();
		if(zones.isEmpty()){
			if (player.hasPermission("bytecraft.zone.select")) {
				if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
					if (border1.containsKey(player)) {
						border1.remove(player);
						border1.put(player, loc);
					} else {
						border1.put(player, loc);
					}
					player.sendMessage(ChatColor.AQUA + "Position one selected");
				} else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if (border2.containsKey(player)) {
						border2.remove(player);
						border2.put(player, loc);
					} else {
						border2.put(player, loc);
					}
					player.sendMessage(ChatColor.AQUA + "Position two selected");
				} else {
					return;
				}
			}
		}else{
		for(Zone zone: zones){
			if(!zone.contains(vector)){
		if (player.hasPermission("bytecraft.zone.select")) {
			if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
				if (border1.containsKey(player)) {
					border1.remove(player);
					border1.put(player, loc);
				} else {
					border1.put(player, loc);
				}
				player.sendMessage(ChatColor.AQUA + "Position one selected");
			} else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (border2.containsKey(player)) {
					border2.remove(player);
					border2.put(player, loc);
				} else {
					border2.put(player, loc);
				}
				player.sendMessage(ChatColor.AQUA + "Position two selected");
			} else {
				return;
			}
		}
	}
		}
}
		}
}
