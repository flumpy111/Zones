package info.bytecraft.zones.commands;

import java.util.List;

import info.bytecraft.zones.Rank;
import info.bytecraft.zones.ZoneCreator;
import info.bytecraft.zones.Zones;
import info.bytecraft.zones.info.Zone;
import info.bytecraft.zones.info.ZonePlayers;
import info.bytecraft.zones.info.ZoneVector;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TownCommand implements CommandExecutor{
	private static Zones plugin;
	public TownCommand(Zones instance){
		plugin = instance;
	}

	private ChatColor r = ChatColor.RED;
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("town")){
			if(cs instanceof Player){
				Player player = (Player)cs;
				Location loc = player.getLocation();
				ZoneVector vector = new ZoneVector((int)loc.getX(), (int)loc.getY(), (int)loc.getZ());
				List<Zone> zones = plugin.getDatabase().find(Zone.class).where().ieq("worldName", player.getWorld().getName()).findList();
				if(!zones.isEmpty()){
					for(Zone zone: zones){
						if(zone.contains(vector)){
							if(args.length == 1){
								if(args[0].equalsIgnoreCase("delete")){
									if(player.hasPermission("bytecraft.zones.delete")){
										plugin.getDatabase().delete(zone);
										player.sendMessage(r+"<"+args[1]+">" + " Zone deleted successfully");
									}
								}
							}else if(args.length == 2){
								if(args[0].equalsIgnoreCase("deluser")){ //town deluser[0] player[1]
									Player target = Bukkit.getPlayer(args[1]);
									if(target != null){
										ZonePlayers players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("zoneName", zone.getName()).ieq("playerName", player.getName()).findUnique();
										if(players != null){
											plugin.getDatabase().delete(players);
											target.sendMessage(r+"<"+zone.getName()+">"+ " You have been removed from " + zone.getName());
											player.sendMessage(r+"<"+zone.getName()+">"+" You have deleted " + target.getDisplayName() +r+ " from " + zone.getName());
										}
									}
								}else if(args[0].equalsIgnoreCase("whitelist")){
									ZonePlayers players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("zoneName",zone.getName()).ieq("playerName", player.getName()).findUnique();
									if(players != null && (players.getRank() == Rank.OWNER || player.hasPermission("bytecraft.zones.whitelist"))){
										boolean value = Boolean.parseBoolean(args[2]);
										ZoneCreator.setWhiteListed(zone.getName(), value);
										player.sendMessage(r+"<"+zone.getName()+">"+ "Toggled whitelist to " + String.valueOf(value) + " in " +zone.getName());
										return true;
									}
								}else if(args[0].equalsIgnoreCase("pvp")){
									if(player.hasPermission("bytecraft.zones.pvp")){
										boolean value = Boolean.parseBoolean(args[2]);
										ZoneCreator.setPvp(zone.getName(), value);
										player.sendMessage(r+"<"+zone.getName()+">"+" Toggled pvp to " + String.valueOf(value) + " in "+zone.getName());
									}
								}else if(args[0].equalsIgnoreCase("enter")){
									ZonePlayers players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("zoneName",zone.getName()).ieq("playerName", player.getName()).findUnique();
									if(players != null && (players.getRank() == Rank.OWNER || player.hasPermission("bytecraft.zones.messages"))){
										try{
											throw new UnsupportedOperationException(r+"This feature is not yet working");
										}catch(UnsupportedOperationException ex){
											player.sendMessage(ex.getMessage());
										}
										//zone.setEnterMessage(args[1]);
										//plugin.getDatabase().update(zone);
									}
								}else if(args[0].equalsIgnoreCase("exit")){
									ZonePlayers players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("zoneName",zone.getName()).ieq("playerName", player.getName()).findUnique();
									if(players != null && (players.getRank() == Rank.OWNER || player.hasPermission("bytecraft.zones.messages"))){
										try{
											throw new UnsupportedOperationException(r+"This feature is not yet working");
										}catch(UnsupportedOperationException ex){
											player.sendMessage(ex.getMessage());
										}
										//zone.setExitMessage(args[1]);
										//plugin.getDatabase().update(zone);
									}
								}
							}else if(args.length == 3){
								if(args[0].equalsIgnoreCase("adduser")){
									Player target = Bukkit.getPlayer(args[1]);
									if(target != null){
										Rank rank = Rank.getByName(args[2]);
										if(rank != null){
										ZoneCreator.addMember(zone.getName(), target, rank);
										target.sendMessage(r+"<"+args[1]+">" + " You have been added as a(n) " + rank.getType() + " in " + args[1]);
										player.sendMessage(r+"<"+args[1]+">" + " You have added " + target.getDisplayName() + r + " as a(n) " + rank.getType() + " in " + args[1]);
										}
									}
								}
							}else if(args.length > 3){
								
							}
						}
					}
				}
			}
		}
		return true;
	}
}
