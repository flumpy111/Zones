package info.bytecraft.zones.events;

import info.bytecraft.zones.info.Zone;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class ZoneEvent extends Event {
	protected Zone zone;
	private static HandlerList handlers = new HandlerList();
	
	public ZoneEvent(Zone zone){
		this.zone = zone;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList(){
		return handlers;
	}
	
	public abstract Zone getZone();

}
