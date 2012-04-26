package info.bytecraft.zones.info;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.avaje.ebean.validation.NotNull;

@Entity
@Table(name="lots_players")
public class LotPlayers {
	
	@Id
	private int id;
	
	@NotNull
	private String lotName;
	
	@NotNull
	private String playerName;
	
	@NotNull
	private String zoneName;
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getLotName(){
		return this.lotName;
	}
	
	public void setLotName(String name){
		this.lotName = name;
	}
	
	public String getPlayerName(){
		return this.playerName;
	}
	
	public void setPlayerName(String name){
		this.playerName = name;
	}
	
	public Player getPlayer(){
		return Bukkit.getPlayer(playerName);
	}
	
	public void setPlayer(Player player){
		this.playerName = player.getName();
	}
	
	public String getZoneName(){
		return this.zoneName;
	}
	
	public void setZoneName(String zone){
		this.zoneName = zone;
	}
}
