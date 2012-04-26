package info.bytecraft.zones.commands;

import java.util.List;

import info.bytecraft.zones.Rank;
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

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("town")){
			if(cs instanceof Player){
				Player player = (Player)cs;
				Location loc = player.getLocation();
				List<Zone> zones = plugin.getDatabase().find(Zone.class).where().ieq("worldName", player.getWorld().getName()).findList();
				for(Zone zone: zones){
					ZoneVector vector = new ZoneVector((int)loc.getX(),(int)loc.getY(),(int)loc.getZ());
					if(zone.contains(vector)){
						if(args.length == 0){
							player.sendMessage(ChatColor.RED + "You current zone is " + ChatColor.DARK_AQUA + zone.getName());
							return true;
						}else{
							if(args.length >=3){
								ZonePlayers info = plugin.getDatabase().find(ZonePlayers.class).where().ieq("zoneName", zone.getName()).ieq("playerName", player.getName()).findUnique();
								if(info == null || info.getRank() != Rank.OWNER){
									return true;
								}else{
								if(args[0].equalsIgnoreCase("adduser")){
									Player target = Bukkit.getPlayer(args[1]);
									if(target != null){
										ZonePlayers players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("zoneName", zone.getName()).ieq("playerName", target.getName()).findUnique();
										if(players == null){
											players = new ZonePlayers();
											players.setPlayer(target);
											players.setZoneName(zone.getName());
										}
										if(args[2].equalsIgnoreCase("owner")){
											players.setRank(Rank.OWNER);
										}else if(args[2].equalsIgnoreCase("banned")){
											players.setRank(Rank.BANNED);
										}else if(args[2].equalsIgnoreCase("maker")){
											players.setRank(Rank.MAKER);
										}else if(args[2].equalsIgnoreCase("allowed")){
											players.setRank(Rank.ALLOWED);
										}
										plugin.getDatabase().save(players);
										return true;
									}
								}else if(args[0].equalsIgnoreCase("set")){
									if(args[1].equalsIgnoreCase("pvp")){
										boolean value;
										try{
											value = Boolean.parseBoolean(args[2]);
										}catch(Exception ex){
											return true;
										}
										zone.setPvpAllowed(value);
										plugin.getDatabase().save(zone);
										return true;
									}else if(args[1].equalsIgnoreCase("enter")){
										if(args[2].equalsIgnoreCase("true")){
											zone.setWhiteListed(true);
											plugin.getDatabase().save(zone);
											return true;
										}else if(args[2].equalsIgnoreCase("false")){
											zone.setWhiteListed(false);
											plugin.getDatabase().save(zone);
											return true;
										}else{
											int i=2; int para=args.length; String s="";
											while(i<para){
												s=s+" "+args[i];
												i++;
											}
											zone.setEnterMessage(s);
											player.sendMessage(ChatColor.DARK_GREEN + zone.getName() + "'s enter message set to:" + s);
											plugin.getDatabase().save(zone);
											return true;
										}
									}else if(args[1].equalsIgnoreCase("exit")){
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
										}else if(args[1].equalsIgnoreCase("break")){
											if(args[2].equalsIgnoreCase("true")){
												zone.setFreeBreak(true);
												plugin.getDatabase().save(zone);
												return true;
											}else if(args[2].equalsIgnoreCase("false")){
												zone.setFreeBreak(true);
												plugin.getDatabase().save(zone);
												return true;
											}else{
												return true;
											}
										}else if(args[1].equalsIgnoreCase("place")){
											if(args[2].equalsIgnoreCase("true")){
												zone.setFreePlace(true);
												plugin.getDatabase().save(zone);
												return true;
											}else if(args[2].equalsIgnoreCase("false")){
												zone.setFreePlace(false);
												plugin.getDatabase().save(zone);
												return true;
											}else{
												return true;
											}
										}
									}
								}
							}else if(args.length == 2){
								if(args[0].equalsIgnoreCase("deluser")){
									Player target = Bukkit.getPlayer(args[1]);
									if(target != null){
										ZonePlayers players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("zoneName", zone.getName()).ieq("playerName", target.getName()).findUnique();
										if(players != null){
											plugin.getDatabase().delete(players);
											return true;
										}else{
											return true;
										}
									}
								}
							}
						}
					}else{
						return true;
					}
				}
			}
		}
		return false;
	}

}
