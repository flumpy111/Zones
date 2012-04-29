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
					if(args[0].equalsIgnoreCase("create") && player.hasPermission("bytecraft.zones.create")){
						if(!Zone.exists(args[1])){
							ZoneCreator.createZone(args[1], player);
							player.sendMessage(da +"Created a new zone "+a+ args[1]+da+"successfully");
						}
						return true;
					}else if(args[0].equalsIgnoreCase("delete") && player.hasPermission("bytecraft.zones.delete")){
						if(Zone.exists(args[1])){
							ZoneCreator.deleteZone(args[1]);
							player.sendMessage(r+"<"+args[1]+">" + " deleted successfully");
						}
					}
				}else if(args.length == 4){
					
				}
			}
		return true;
	}

}
