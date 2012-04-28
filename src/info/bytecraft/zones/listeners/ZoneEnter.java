package info.bytecraft.zones.listeners;

import info.bytecraft.zones.Rank;
import info.bytecraft.zones.Zones;
import info.bytecraft.zones.info.Zone;
import info.bytecraft.zones.info.ZonePlayers;
import info.bytecraft.zones.info.ZoneVector;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class ZoneEnter implements Listener{
	private static Zones plugin;
	public ZoneEnter(Zones instance){
		plugin = instance;
	}
	

	@EventHandler
	public void onEnter(PlayerMoveEvent event){
		Player player = event.getPlayer();
		String world = event.getTo().getWorld().getName();
		Location eTo = event.getTo();
		Location eFrom = event.getFrom();
		ZoneVector to = new ZoneVector((int)eTo.getX(), (int)eTo.getY(), (int)eTo.getZ());
		ZoneVector from = new ZoneVector(eFrom.getBlockX(), eFrom.getBlockY(), eFrom.getBlockZ());
		if(to != from){
		for(Zone zone: plugin.getDatabase().find(Zone.class).where().ieq("worldName", world).findList()){
			ZonePlayers players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("zoneName", zone.getName()).ieq("playerName", player.getName()).findUnique();
			if(players != null){
				if(players.getRank() == Rank.BANNED){
					if(zone.contains(to) && !zone.contains(from)){
						if(!player.hasPermission("bytecraft.admin")){
					player.teleport(event.getFrom());
					player.sendMessage(ChatColor.RED+"<"+zone.getName()+"> " + "You are not allowed in " + zone.getName());
					event.setCancelled(true);
						}
					}
				}else{
					if(zone.contains(to) && !zone.contains(from)){
						if(!zone.isWhiteListed()){
						player.sendMessage(zone.getEnterMessage());
						}else{
							if(players.getRank() != Rank.ALLOWED || players.getRank() != Rank.MAKER || players.getRank() != Rank.OWNER){
								if(!player.hasPermission("bytecraft.admin")){
								player.sendMessage(ChatColor.RED+"<"+zone.getName()+"> " + "You are not allowed in " + zone.getName());
								event.setCancelled(true);
								}
							}
						}
					}
				}
			}else{
				if(!zone.isWhiteListed()){
				if(zone.contains(to) && !zone.contains(from)){
					player.sendMessage(zone.getEnterMessage());
						}
					}else{
						if(!player.hasPermission("bytecraft.admin")){
						player.teleport(event.getFrom());
						player.sendMessage(ChatColor.RED+"<"+zone.getName()+"> " +  "You are not allowed in " + zone.getName());
						event.setCancelled(true);
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onExit(PlayerMoveEvent event){
		Player player = event.getPlayer();
		String world = event.getTo().getWorld().getName();
		Location eTo = event.getTo();
		Location eFrom = event.getFrom();
		ZoneVector to = new ZoneVector((int)eTo.getX(), (int)eTo.getY(), (int)eTo.getZ());
		ZoneVector from = new ZoneVector(eFrom.getBlockX(), eFrom.getBlockY(), eFrom.getBlockZ());
		if(to != from){
		for(Zone zone: plugin.getDatabase().find(Zone.class).where().ieq("worldName", world).findList()){
				if(zone.contains(from) && !zone.contains(to)){
					player.sendMessage(zone.getExitMessage());
				}
			}
		}
	}
	
	@EventHandler
	public void onTeleport(PlayerTeleportEvent event){
		Player player = event.getPlayer();
		String world = event.getTo().getWorld().getName();
		Location eTo = event.getTo();
		Location eFrom = event.getFrom();
		ZoneVector to = new ZoneVector((int)eTo.getX(), (int)eTo.getY(), (int)eTo.getZ());
		ZoneVector from = new ZoneVector(eFrom.getBlockX(), eFrom.getBlockY(), eFrom.getBlockZ());
		if(to != from){
			for(Zone zone: plugin.getDatabase().find(Zone.class).where().ieq("worldName", world).findList()){
				if(zone.contains(to) && !zone.contains(from)){
					ZonePlayers players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("zoneName", zone.getName()).ieq("playerName", player.getName()).findUnique();
					if(players != null){
						if(players.getRank() == Rank.BANNED){
							if(zone.contains(to) && !zone.contains(from)){
								if(!player.hasPermission("bytecraft.admin")){
							player.sendMessage(ChatColor.RED+"<"+zone.getName()+"> " + "You are not allowed in " + zone.getName());
							event.setCancelled(true);
								}
							}
						}else{
							if(zone.contains(to) && !zone.contains(from)){
								if(!zone.isWhiteListed()){
								player.sendMessage(zone.getEnterMessage());
								}else{
									if(players.getRank() != Rank.ALLOWED || players.getRank() != Rank.MAKER || players.getRank() != Rank.OWNER){
										if(!player.hasPermission("bytecraft.admin")){
										player.sendMessage(ChatColor.RED+"<"+zone.getName()+"> " + "You are not allowed in " + zone.getName());
										event.setCancelled(true);
										}
									}
								}
							}
						}
					}else{
						if(!zone.isWhiteListed()){
						if(zone.contains(to) && !zone.contains(from)){
							player.sendMessage(zone.getEnterMessage());
								}
							}else{
								if(!player.hasPermission("bytecraft.admin")){
								player.sendMessage(ChatColor.RED+"<"+zone.getName()+"> " + "You are not allowed in " + zone.getName());
								event.setCancelled(true);
								}
							}
						}
				}else if(!zone.contains(to) && zone.contains(from)){
					player.sendMessage(zone.getExitMessage());
				}
			}
		}
	}
	
}