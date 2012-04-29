package info.bytecraft.zones.commands;

import java.util.List;

import info.bytecraft.zones.Rank;
import info.bytecraft.zones.ZoneCreator;
import info.bytecraft.zones.Zones;
import info.bytecraft.zones.info.Zone;
import info.bytecraft.zones.info.ZonePlayers;
import info.bytecraft.zones.listeners.Selector;

import org.bukkit.Bukkit;
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
	private ChatColor da = ChatColor.DARK_AQUA;
	private ChatColor a = ChatColor.AQUA;
	private ChatColor r = ChatColor.RED;
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("zone") && cs instanceof Player){
			Player player = (Player)cs;
				if(args.length == 2){ //zone create[0] name[1] //zone delete[0] name[1] //zone adduser[0] name[1] player[2] rank[3]
					plugin.getLogger().info("line 33");
					if(args[0].equalsIgnoreCase("create") && player.hasPermission("bytecraft.zones.create")){
						plugin.getLogger().info("line 35");
						if(plugin.getDatabase().find(Zone.class).where().ieq("name", args[1]).findUnique() == null){
							plugin.getLogger().info("line 37");
							if(!Selector.border1.containsKey(player) || !Selector.border2.containsKey(player)){
								player.sendMessage(ChatColor.RED + "You must select both borders first!");
								return true;
							}
							ZoneCreator.createZone(args[1], player);
							player.sendMessage(da +"Created a new zone "+a+ args[1]+da+" successfully");
						}
						return true;
					}else if(args[0].equalsIgnoreCase("delete") && player.hasPermission("bytecraft.zones.delete")){
						if(plugin.getDatabase().find(Zone.class).where().ieq("name", args[1]).findUnique() != null){
							ZoneCreator.deleteZone(args[1]);
							player.sendMessage(r+"<"+args[1]+">" + " deleted successfully");
							return true;
						}
					}
				}else if(args.length == 4){
					if(args[0].equalsIgnoreCase("adduser")){
						ZonePlayers players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("zoneName", args[1]).ieq("playerName", player.getName()).findUnique();
						if(players != null && (players.getRank() == Rank.OWNER || player.hasPermission("bytecraft.zones.admin"))){
						Player target = Bukkit.getPlayer(args[2]);
						if(target != null){
							Rank rank = Rank.getByName(args[3].toUpperCase());
							if(rank != null){
								if(plugin.getDatabase().find(Zone.class).where().ieq("name", args[1]).findUnique() != null){
									ZoneCreator.addMember(args[1], target, rank);
									target.sendMessage(r+"<"+args[1]+">" + " You have been added as a" + rank.getType() + " in " + args[1]);
									player.sendMessage(r+"<"+args[1]+">" + " You have added " + target.getDisplayName() + r + " as a " + rank.getType() + " in " + args[1]);
									return true;
								}
							}
						}
					}
				}
				}else if(args.length == 3){
					if(args[0].equalsIgnoreCase("deluser")){
						ZonePlayers players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("zoneName", args[1]).ieq("playerName", player.getName()).findUnique();
						if(players != null && (players.getRank() == Rank.OWNER || player.hasPermission("bytecraft.zones.admin"))){
						Player target = Bukkit.getPlayer(args[2]);
						if(target != null){
							if(plugin.getDatabase().find(Zone.class).where().ieq("name", args[1]).findUnique() != null){
								ZoneCreator.deleteMember(args[1], target);
								player.sendMessage(r+"<"+args[1]+">" + " You have removed " + target.getDisplayName() + r + " from " + args[1]);
								target.sendMessage(r+"<"+args[1]+">"+ " You have been removed from " + args[1]);
								return true;
							}
						}
					}
				}
			}
		}
		return true;
	}

}
