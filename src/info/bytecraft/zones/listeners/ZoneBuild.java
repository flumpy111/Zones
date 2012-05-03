package info.bytecraft.zones.listeners;


import info.bytecraft.zones.Rank;
import info.bytecraft.zones.Zones;
import info.bytecraft.zones.events.ZoneBlockPlaceEvent;
import info.bytecraft.zones.info.Zone;
import info.bytecraft.zones.info.ZonePlayers;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class ZoneBuild implements Listener{
	private static Zones plugin;
	public ZoneBuild(Zones instance){
		plugin = instance;
	}
	
	/*@EventHandler
	public void onBreak(BlockBreakEvent event){
		Player player = event.getPlayer();
		Location loc = event.getBlock().getLocation();
		ZoneVector vector = new ZoneVector(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		List<Zone> zones = plugin.getDatabase().find(Zone.class).where().ieq("worldName", loc.getWorld().getName()).findList();
		for(Zone zone: zones){
			if(zone.contains(vector)){
				ZonePlayers players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("playerName", player.getName()).findUnique();
			if(players != null){
				if(zone.getName().equalsIgnoreCase(players.getZoneName())){
				if(players.getRank() != Rank.OWNER){
					if(players.getRank() != Rank.MAKER){
						if(!player.hasPermission("bytecraft.admin") || !player.isOp()){
					event.setCancelled(true);
					player.sendMessage(ChatColor.RED+"<"+zone.getName()+"> " + "You are not allowed to break blocks in " + zone.getName());
					player.setFireTicks(100);
							}
						}
					}
				}
				}else{
					if(!zone.isFreeBreak()){
						if(!player.hasPermission("bytecraft.admin")){
						event.setCancelled(true);
						player.sendMessage(ChatColor.RED+"<"+zone.getName()+"> " + "You are not allowed to break blocks in " + zone.getName());
						player.setFireTicks(100);
						}
					}
				}
			}
		}
	}*/
	
	/*@EventHandler
	public void onPlace(BlockPlaceEvent event){
		Player player = event.getPlayer();
		Location loc = event.getBlock().getLocation();
		ZoneVector vector = new ZoneVector(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		List<Zone> zones = plugin.getDatabase().find(Zone.class).where().ieq("worldName", loc.getWorld().getName()).findList();
		for(Zone zone: zones){
			ZonePlayers players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("playerName", player.getName()).findUnique();
			if(zone.contains(vector)){
				if(players != null){
					if(players.getZoneName().equalsIgnoreCase(zone.getName())){
					if(players.getRank() != Rank.OWNER){
						if(players.getRank() != Rank.MAKER){
							if(!player.hasPermission("bytecraft.admin") || !player.isOp()){
						event.setCancelled(true);
						player.sendMessage(ChatColor.RED+"<"+zone.getName()+"> " + "You are not allowed to place blocks in " + zone.getName());
						player.setFireTicks(100);
							}
						}
					}
				}
				}else{
					if(!zone.isFreePlace()){
						if(!player.hasPermission("bytecraft.admin")){
						event.setCancelled(true);
						player.sendMessage(ChatColor.RED+"<"+zone.getName()+"> " + "You are not allowed to place blocks in " + zone.getName());
						player.setFireTicks(100);
						}
					}
				}
			}
		}
	}*/
	
	@EventHandler
	public void onPlace(ZoneBlockPlaceEvent event){
		Player player = event.getPlayer();
		Zone zone = event.getZone();
		plugin.getLogger().info(event.getEventName());
		if(!zone.isFreePlace()){
			ZonePlayers players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("zoneName", zone.getName()).ieq("playerName", player.getName()).findUnique();
			if(players != null){
				if((players.getRank() != Rank.MAKER) || (players.getRank() != Rank.OWNER)){
					event.setCancelled(true);
					player.sendMessage(ChatColor.RED+"<"+zone.getName()+"> " + "You are not allowed to place blocks in " + zone.getName());
					player.setFireTicks(60);
				}
			}else{
				if(!player.hasPermission("byteraft.zones.admin")){
					event.setCancelled(true);
					player.sendMessage(ChatColor.RED+"<"+zone.getName()+"> " + "You are not allowed to place blocks in " + zone.getName());
					player.setFireTicks(60);
				}
			}
		}		
		if(event.isCancelled()){
			event.getBlock().breakNaturally();
		}
		plugin.getLogger().info(event.isCancelled()+ "");
	}
}
