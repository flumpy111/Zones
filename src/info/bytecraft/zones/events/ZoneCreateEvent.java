package info.bytecraft.zones.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

import info.bytecraft.zones.info.Zone;

public class ZoneCreateEvent extends ZoneEvent implements Cancellable{
	private Player player;
	public ZoneCreateEvent(Player player, Zone zone) {
		super(zone);
		this.player = player;
	}
	
	private boolean cancelled;

	@Override
	public Zone getZone() {
		return this.zone;
	}
	
	public Player getPlayer(){
		return this.player;
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
