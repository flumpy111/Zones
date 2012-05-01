package info.bytecraft.zones.events;

import info.bytecraft.zones.info.Zone;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class ZoneExitEvent extends ZoneEvent{
	private static HandlerList handlers = new HandlerList();
	private Player player;
	private Zone zone;
	
	public ZoneExitEvent(Player player, Zone zone){
		super(zone);
		this.player = player;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList(){
		return handlers;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public Zone getZone(){
		return zone;
	}

}
