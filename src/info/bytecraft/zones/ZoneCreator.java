package info.bytecraft.zones;

import java.util.List;

import info.bytecraft.zones.info.Zone;
import info.bytecraft.zones.info.ZonePlayers;
import info.bytecraft.zones.listeners.ZoneSelector;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ZoneCreator {
	private static Zones plugin = (Zones)Bukkit.getPluginManager().getPlugin("Zones");
	
	public static void createZone(String name, Player player){
		if(plugin.getDatabase().find(Zone.class).where().ieq("name", name).findUnique() != null)return;
		Zone zone = new Zone();
		zone.setBorder1(ZoneSelector.border1.get(player));
		zone.setBorder2(ZoneSelector.border2.get(player));
		zone.setPvpAllowed(false);
		zone.setHostilesAllowed(true);
		zone.setFreeBreak(true);
		zone.setFreePlace(true);
		zone.setName(name);
		plugin.getDatabase().save(zone);
		ZonePlayers players = new ZonePlayers();
		players.setZoneName(name);
		players.setPlayer(player);
		players.setRank(Rank.OWNER);
		plugin.getDatabase().save(players);
	}
	
	public static void deleteZone(String name){
		Zone zone = plugin.getDatabase().find(Zone.class).where().ieq("name", name).findUnique();
		List<ZonePlayers> players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("zoneName", zone.getName()).findList();
		for(ZonePlayers other: players){
			plugin.getDatabase().delete(other);
		}
		if(zone != null){
		plugin.getDatabase().delete(zone);
		}
	}
	
	public static void addMember(String zone, Player player, Rank rank){
		if(plugin.getDatabase().find(Zone.class).where().ieq("name", zone).findUnique() == null)return;
		ZonePlayers players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("zoneName", zone).ieq("playerName", player.getName()).findUnique();
		if(players == null){
			players = new ZonePlayers();
			players.setPlayer(player);
			players.setZoneName(zone);
		}
		players.setRank(rank);
		plugin.getDatabase().save(players);
	}
	
	public static void deleteMember(String zone, Player player){
		if(plugin.getDatabase().find(Zone.class).where().ieq("name", zone).findUnique() == null)return;
		ZonePlayers players = plugin.getDatabase().find(ZonePlayers.class).where().ieq("zoneName", zone).ieq("playerName", player.getName()).findUnique();
		if(players == null)return;
		plugin.getDatabase().delete(players);
	}
	
	public static void setWhiteListed(String name, boolean value){
		Zone zone = plugin.getDatabase().find(Zone.class).where().ieq("name", name).findUnique();
		zone.setWhiteListed(value);
		plugin.getDatabase().save(zone);
	}
	
	public static void setPvp(String name, boolean value){
		Zone zone = plugin.getDatabase().find(Zone.class).where().ieq("name", name).findUnique();
		zone.setPvpAllowed(value);
		plugin.getDatabase().save(zone);
	}
}
