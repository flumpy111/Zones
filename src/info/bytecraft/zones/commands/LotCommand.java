package info.bytecraft.zones.commands;

import info.bytecraft.zones.Rank;
import info.bytecraft.zones.ZoneAlreadyExistsException;
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
			List<Zone> zones = plugin.getDatabase().find(Zone.class).where().ieq("worldName", player.getWorld().getName()).findList();
			for(Zone zone: zones){
				if(zone.contains(vector)){
				List<ZonePlayers> players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("zoneName", zone.getName()).findList();
				for(ZonePlayers other: players){
					if(other != null && other.getRank() == Rank.OWNER){
						if(args.length == 3){
							if(args[0].equalsIgnoreCase("create")){
							String name = args[1];
							Player target = Bukkit.getPlayer(args[2]);
							if(target != null){
								if(!LotSelect.border1.containsKey(player) || !LotSelect.border2.containsKey(player)){
									player.sendMessage(ChatColor.RED + "Please select both borders of the lot first");
									return true;
								}else{
								Lot lot = plugin.getDatabase().find(Lot.class).where().ieq("zoneName", zone.getName()).ieq("lotName", name).findUnique();
								if(lot == null){
									lot = new Lot();
									lot.setZoneName(zone.getName());
									lot.setLotName(name);
									lot.setBorder1(LotSelect.border1.get(player));
									lot.setBorder2(LotSelect.border2.get(player));
									plugin.getDatabase().save(lot);
									LotPlayers rawr = new LotPlayers();
									rawr.setLotName(name);
									rawr.setPlayer(target);
									rawr.setZoneName(zone.getName());
									plugin.getDatabase().save(rawr);
									player.sendMessage(ChatColor.AQUA + "A new lot, " + name + ", has been created for " + target.getDisplayName());
									return true;
								}else{
									try{
										throw new ZoneAlreadyExistsException("The zone " + name + " already exists");
									}catch(ZoneAlreadyExistsException ex){
										player.sendMessage(ChatColor.RED + ex.getMessage());
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
		}
		return false;
	}

}
