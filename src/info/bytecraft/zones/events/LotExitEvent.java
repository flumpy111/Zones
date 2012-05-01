package info.bytecraft.zones.events;

import info.bytecraft.zones.info.Lot;
import info.bytecraft.zones.info.Zone;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class LotExitEvent extends Event {
	private static HandlerList handlers = new HandlerList();
	protected Zone zone;
	protected Lot lot;
	protected Player player;
	
	public LotExitEvent(Player player, Zone zone, Lot lot){
		this.player = player;
		this.zone = zone;
		this.lot = lot;
	}

	@Override
	public HandlerList getHandlers() {
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
}
