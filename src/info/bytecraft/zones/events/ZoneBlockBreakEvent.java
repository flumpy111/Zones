package info.bytecraft.zones.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

import info.bytecraft.zones.info.Zone;

public class ZoneBlockBreakEvent extends ZoneEvent implements Cancellable{
	private Player player;
	private Block block;
	private boolean cancelled;
	
	public ZoneBlockBreakEvent(Player player, Zone zone, Block block) {
		super(zone);
		this.player = player;
		this.block = block;
	}

	public Zone getZone() {
		return zone;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public Block getBlock(){
		return this.block;
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
