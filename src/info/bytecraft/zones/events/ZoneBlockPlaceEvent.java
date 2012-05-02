package info.bytecraft.zones.events;

import info.bytecraft.zones.info.Zone;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

public class ZoneBlockPlaceEvent extends ZoneEvent implements Cancellable {
	private Player player;
	private Block block;
	private boolean cancelled;

	public ZoneBlockPlaceEvent(Player player, Zone zone, Block block) {
		super(zone);
		this.player = player;
		this.block = block;
	}

	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public Block getBlock(){
		return this.block;
	}

}
