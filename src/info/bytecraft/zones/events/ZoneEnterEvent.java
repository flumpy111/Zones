package info.bytecraft.zones.events;

import info.bytecraft.zones.info.Zone;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ZoneEnterEvent extends Event implements Cancellable{
	private static HandlerList handlers = new HandlerList();
	private Player player;
	private Zone zone;
	private boolean cancelled;
	public ZoneEnterEvent(Player player, Zone zone){
		this.player = player;
		this.zone = zone;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList(){
		return handlers;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public Zone getZone(){
		return this.zone;
	}

	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}

}
