package info.bytecraft.zones.listeners;

import java.util.List;

import info.bytecraft.zones.Zones;
import info.bytecraft.zones.info.Zone;
import info.bytecraft.zones.info.ZoneVector;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ZoneMobSpawn implements Listener{
	private static Zones plugin;
	public ZoneMobSpawn(Zones instance){
		plugin = instance;
	}
	
	@EventHandler
	public void onSpawn(CreatureSpawnEvent event){
		Location loc = event.getLocation();
		EntityType type = event.getEntityType();
		ZoneVector vector = new ZoneVector((int)loc.getX(),(int)loc.getY(),(int)loc.getZ());
		List<Zone> zones = plugin.getDatabase().find(Zone.class).where().ieq("worldName", loc.getWorld().getName()).findList();
		for(Zone zone: zones){
			if(zone.contains(vector)){
				if(!zone.isHostilesAllowed()){
					if(type == EntityType.CAVE_SPIDER || type == EntityType.CREEPER || type == EntityType.ENDER_DRAGON || type == EntityType.ENDERMAN
					|| type == EntityType.GHAST || type == EntityType.MAGMA_CUBE || type == EntityType.SILVERFISH || type == EntityType.SKELETON
					|| type == EntityType.SPIDER || type == EntityType.ZOMBIE){
						event.setCancelled(true);
					}
				}
			}
		}
	}	
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event){
		Location loc = event.getDamager().getLocation();
		EntityType type = event.getDamager().getType();
		ZoneVector vector = new ZoneVector((int)loc.getX(),(int)loc.getY(),(int)loc.getZ());
		List<Zone> zones = plugin.getDatabase().find(Zone.class).where().ieq("worldName", loc.getWorld().getName()).findList();
		for(Zone zone: zones){
			if(zone.contains(vector)){
				if(!zone.isHostilesAllowed()){
					if(type == EntityType.CAVE_SPIDER || type == EntityType.CREEPER || type == EntityType.ENDER_DRAGON || type == EntityType.ENDERMAN
					|| type == EntityType.GHAST || type == EntityType.MAGMA_CUBE || type == EntityType.SILVERFISH || type == EntityType.SKELETON
					|| type == EntityType.SPIDER || type == EntityType.ZOMBIE){
						if(type == EntityType.PLAYER){
							if(!zone.isPvpAllowed()){
								event.setCancelled(true);
							}
						}else{
						event.setCancelled(true);
						}
					}
				}
			}
		}
	}
}
