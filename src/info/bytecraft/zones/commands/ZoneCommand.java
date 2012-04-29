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
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("zone") && cs instanceof Player){
			Player player = (Player)cs;
				if(args.length == 2){
					if(args[0].equalsIgnoreCase("create") && player.hasPermission("bytecraft.zones.create")){
						ZoneCreator.createZone(args[1], player);
						return true;
					}
				}
		}
		return false;
	}

}
