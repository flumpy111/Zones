package info.bytecraft.zones.listeners;

import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;

public class PistonCheck implements Listener{
	
	@EventHandler
	public void onPush(BlockPistonExtendEvent event){
		for(Block block: event.getBlocks()){
			if(block.getRelative(0, -1, 0).getState() instanceof Chest){
				event.setCancelled(true);
			}
		}
	}
}
