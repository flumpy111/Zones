package info.bytecraft.zones.events;

import org.bukkit.entity.Player;

import info.bytecraft.zones.info.Zone;

public class ZonePvPEvent extends ZoneEvent{
	private Player player;
	private Player target;
	private float damage;

	public ZonePvPEvent(Player player, Zone zone, Player target, float damage) {
		super(zone);
		this.player = player;
		this.target = target;
		this.damage = damage;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public Player getTargetPlayer(){
		return this.target;
	}
	
	public float getDamage(){
		return this.damage;
	}
	
	public void setDamage(float damage){
		this.damage = damage;
	}

}
