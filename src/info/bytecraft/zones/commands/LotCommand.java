package info.bytecraft.zones.commands;

import info.bytecraft.zones.Rank;
import info.bytecraft.zones.Zones;
import info.bytecraft.zones.info.Lot;
import info.bytecraft.zones.info.LotPlayers;
import info.bytecraft.zones.info.Zone;
import info.bytecraft.zones.info.ZonePlayers;
import info.bytecraft.zones.info.ZoneVector;
import info.bytecraft.zones.listeners.LotSelect;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This command is used to manage lots inside a zone.
 * @author Sabersamus <rcatron10@gmail.com>
 *
 */
public class LotCommand implements CommandExecutor{
	private final Zones plugin;
	public LotCommand(Zones instance){
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("lot") && cs instanceof Player){
			Player player = (Player)cs;
			Location loc = player.getLocation();
			ZoneVector vector = new ZoneVector(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
			List<Zone> zones = plugin.getDatabase().find(Zone.class).where().ieq("worldName", loc.getWorld().getName()).findList();
			if(!zones.isEmpty()){
				for(Zone zone: zones){
					if(zone.contains(vector)){
						ZonePlayers players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("zoneName", zone.getName()).ieq("playerName", player.getName()).findUnique();
						if((players != null && players.getRank() == Rank.OWNER) || player.hasPermission("bytecraft.zones.lot")){
						if(args.length == 3){ //lot create[0] name[1] player[2] //lot adduser[0] name[1] player[2]
							if(args[0].equalsIgnoreCase("create")){
								if(!LotSelect.border1.containsKey(player) || !LotSelect.border2.containsKey(player)){
									player.sendMessage(ChatColor.RED + "Please select both borders first!");
									return true;
								}
								String name = args[1];
								Player target = Bukkit.getPlayer(args[2]);
								Lot lot = plugin.getDatabase().find(Lot.class).where().ieq("zoneName", zone.getName()).ieq("lotName", name).findUnique();
								if(lot == null){
									lot = new Lot();
									lot.setLotName(name); lot.setZoneName(zone.getName());
									lot.setBorder1(LotSelect.border1.get(player));
									lot.setBorder2(LotSelect.border2.get(player)); plugin.getDatabase().save(lot);
									LotPlayers lots = new LotPlayers(); lots.setPlayer(target); lots.setLotName(name); lots.setZoneName(zone.getName());
									plugin.getDatabase().save(lots);
									player.sendMessage(ChatColor.RED+"<"+zone.getName()+">" + " Created the lot " + name + " for " + target.getDisplayName());
								}
							}else if(args[0].equalsIgnoreCase("adduser")){
								Lot lot = plugin.getDatabase().find(Lot.class).where().ieq("name", args[1]).findUnique();
								if(lot != null){
									Player target = Bukkit.getPlayer(args[2]);
									if(target != null){
										LotPlayers other = plugin.getDatabase().find(LotPlayers.class).where().ieq("zoneName", zone.getName()).ieq("lotName", lot.getLotName()).findUnique();
										if(other == null){
											other = new LotPlayers();
											other.setLotName(lot.getLotName());
											other.setPlayer(target);
											other.setZoneName(zone.getName());
											player.sendMessage(ChatColor.RED+"<"+zone.getName()+">" + " Added " + target.getDisplayName() + ChatColor.RED + " to " + lot.getLotName());
											return true;
										}
									}
								}
							}else if(args[0].equalsIgnoreCase("deluser")){
								Lot lot = plugin.getDatabase().find(Lot.class).where().ieq("name", args[1]).findUnique();
								if(lot != null){
									Player target = Bukkit.getPlayer(args[2]);
									if(target != null){
										LotPlayers other = plugin.getDatabase().find(LotPlayers.class).where().ieq("zoneName", zone.getName()).ieq("lotName", lot.getLotName()).findUnique();
										if(other != null){
											plugin.getDatabase().delete(other);
											player.sendMessage(ChatColor.RED+"<"+zone.getName()+">" + "Deleted " + target.getDisplayName() + ChatColor.RED + " from " + lot.getLotName());
											return true;
										}
									}
								}
							}
						}else if(args.length == 2){ //lot delete[0] name[1]
							if(args[0].equalsIgnoreCase("delete")){
								Lot lot = plugin.getDatabase().find(Lot.class).where().ieq("lotName", args[1]).ieq("zoneName", zone.getName()).findUnique();
								if(lot != null){
									List<LotPlayers> lots = plugin.getDatabase().find(LotPlayers.class).where().ieq("zoneName", zone.getName()).ieq("lotName", lot.getLotName()).findList();
									plugin.getDatabase().delete(lot);
									for(LotPlayers other: lots){
										plugin.getDatabase().delete(other);
									}
									player.sendMessage(ChatColor.RED+"<"+zone.getName()+">" + " Deleted the lot " + args[1]);
									return true;
								}
							}
						}
					}
				}
			}
		}
	}
		return true;
	}

}
