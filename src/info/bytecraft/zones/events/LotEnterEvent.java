package info.bytecraft.zones.events;

import info.bytecraft.zones.info.Lot;
import info.bytecraft.zones.info.Zone;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class LotEnterEvent extends Event implements Cancellable{
	private static HandlerList handlers = new HandlerList();
	private Zone zone;
	private Lot lot;
	private Player player;
	private boolean cancelled;
	
	public LotEnterEvent(Player player, Zone zone, Lot lot){
		this.player = player;
		this.zone = zone;
		this.lot = lot;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList(){
		return handlers;
	}
	
	public Zone getZone(){
		return this.zone;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public Lot getLot(){
		return this.lot;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
}
