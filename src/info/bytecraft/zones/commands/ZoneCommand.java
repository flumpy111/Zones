package info.bytecraft.zones.commands;

import java.util.List;

import info.bytecraft.zones.Rank;
import info.bytecraft.zones.ZoneNotFoundException;
import info.bytecraft.zones.Zones;
import info.bytecraft.zones.info.Zone;
import info.bytecraft.zones.info.ZonePlayers;
import info.bytecraft.zones.listeners.Selector;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ZoneCommand implements CommandExecutor{
	private static Zones plugin;
	public ZoneCommand(Zones instance){
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("zone") && args.length > 0){
			if(cs instanceof Player){
				Player player = (Player)cs;
				if(args[0].equalsIgnoreCase("create")){
					if(player.hasPermission("bytecraft.zones.create")){
					if(args.length == 2){
						if(!Selector.border1.containsKey(player) || !Selector.border2.containsKey(player)){
							player.sendMessage(ChatColor.RED + "You must select both points first");
							return true;
						}else{
						String name = args[1];
						Zone zone = plugin.getDatabase().find(Zone.class).where().ieq("name", name).findUnique();
						if(zone == null){
							zone = new Zone();
							ZonePlayers var = new ZonePlayers();
							var.setPlayer(player);
							var.setZoneName(name);
							var.setRank(Rank.OWNER);
							zone.setName(name);
							zone.setHostilesAllowed(true);
							zone.setPvpAllowed(false);
							zone.setBorder1(Selector.border1.get(player));
							zone.setBorder2(Selector.border2.get(player));
							plugin.getDatabase().save(zone);
							plugin.getDatabase().save(var);
							player.sendMessage(ChatColor.DARK_AQUA + "The zone " + name + " has been successfully created");
							return true;
								}else{
									player.sendMessage(ChatColor.RED + "The zone " + name + " already exists");
									return true;
								}
							}
						}
					}
				}else if(args[0].equalsIgnoreCase("delete")){
					if(args.length == 2){
					if(player.hasPermission("bytecraft.zones.delete")){
					String name = args[1];
					Zone zone = plugin.getDatabase().find(Zone.class).where().ieq("name", name).findUnique();
					if(zone == null){
						player.sendMessage(ChatColor.RED + "No zone found by the name of " + name);
						return true;
					}else{
						plugin.getDatabase().delete(zone);
						List<ZonePlayers> players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("zoneName", zone.getName()).findList();
						for(ZonePlayers other: players){
							plugin.getDatabase().delete(other);
						}
						player.sendMessage(ChatColor.RED + "Deleted the zone " + name + " successfully");
						return true;
						}
					}
				}
				}else if(args[0].equalsIgnoreCase("adduser")){
					Zone zone = plugin.getDatabase().find(Zone.class).where().ieq("name", args[1]).findUnique();
					if(zone == null){
						try{
							throw new ZoneNotFoundException(ChatColor.RED + "No zone found by the name of " + args[1]);
						}catch(ZoneNotFoundException ex){
							player.sendMessage(ex.getMessage());
						}
						return true;
					}
					
					ZonePlayers players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("zoneName", zone.getName()).ieq("playerName", player.getName()).findUnique();
					if(players == null){
						return true;
					}else{
						if(players.getRank() != Rank.OWNER || !player.hasPermission("bytecraft.zones.admin")){
							return true;
						}else{
							
						}
					}
					//Zone zone = plugin.getDatabase().find(Zone.class).where().ieq("name", args[1]).findUnique();
					//Player target = Bukkit.getPlayer(args[2]);
					return true;
				}else if(args[0].equalsIgnoreCase("deluser")){
					ZonePlayers players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("zoneName", args[1]).ieq("playerName", player.getName()).findUnique();
					if(players == null){
						return true;
					}else{
						if(players.getRank() != Rank.OWNER || !player.hasPermission("bytecraft.zones.admin")){
							return true;
						}else{
							
						}
					}
					//Zone zone = plugin.getDatabase().find(Zone.class).where().ieq("name", args[1]).findUnique();
					//Player target = Bukkit.getPlayer(args[2]);
					return true;
				}else{
					Zone zone = plugin.getDatabase().find(Zone.class).where().ieq("name", args[0]).findUnique();
					if(zone != null){
						ZonePlayers players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("zoneName", zone.getName()).ieq("playerName", player.getName()).findUnique();
						if(players == null || players.getRank() != Rank.OWNER){
							return true;
						}else{
							if(args.length >= 4){
								if(args[1].equalsIgnoreCase("set")){
								if(args[2].equalsIgnoreCase("pvp")){
									if(args[3].equalsIgnoreCase("true")){
										zone.setPvpAllowed(true);
										plugin.getDatabase().save(zone);
										return true;
									}else if(args[3].equalsIgnoreCase("false")){
										zone.setPvpAllowed(false);
										plugin.getDatabase().save(zone);
										return true;
									}else{
										return true;
									}
								}else if(args[2].equalsIgnoreCase("enter")){
									if(args[3].equalsIgnoreCase("true")){
										zone.setWhiteListed(true);
										plugin.getDatabase().save(zone);
										return true;
									}else if(args[3].equalsIgnoreCase("false")){
										zone.setWhiteListed(false);
										plugin.getDatabase().save(zone);
										return true;
									}else{
										int i=3; int para=args.length; String s="";
										while(i<para){
											s=s+" "+args[i];
											i++;
										}
										zone.setEnterMessage(s);
										player.sendMessage(ChatColor.DARK_GREEN + zone.getName() + "'s enter message set to:" + s);
										plugin.getDatabase().save(zone);
										return true;
									}
								}else if(args[2].equalsIgnoreCase("place")){
									if(args[3].equalsIgnoreCase("true")){
										zone.setFreePlace(true);
										plugin.getDatabase().save(zone);
										return true;
									}else if(args[3].equalsIgnoreCase("false")){
										zone.setFreePlace(false);
										plugin.getDatabase().save(zone);
										return true;
									}else{
										return true;
									}
								}else if(args[2].equalsIgnoreCase("break")){
									if(args[3].equalsIgnoreCase("true")){
										zone.setFreeBreak(true);
										plugin.getDatabase().save(zone);
										return true;
									}else if(args[3].equalsIgnoreCase("false")){
										zone.setFreeBreak(true);
										plugin.getDatabase().save(zone);
										return true;
									}else{
										return true;
									}
								}else if(args[2].equalsIgnoreCase("exit")){
									int i=3; int para=args.length;
									String s="";
									while(i<para){
										s=s+" "+args[i];
										i++;
									}
									zone.setExitMessage(s);
									player.sendMessage(ChatColor.WHITE + zone.getName() + " exit message set to:" + s);
									plugin.getDatabase().save(zone);
									return true;
								}else{
									return true;
									}
								}
							}
						}
					}else{
						try{
							throw new ZoneNotFoundException(ChatColor.RED + "No zone found by the name of " + args[0]);
						}catch(ZoneNotFoundException ex){
							player.sendMessage(ex.getMessage());
						}
						return true;
					}
				}
			}
		}
		return false;
	}

}
