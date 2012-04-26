package info.bytecraft.zones.info;

import info.bytecraft.zones.Rank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.avaje.ebean.validation.NotNull;

@Entity()
@Table(name="zones_players")
public class ZonePlayers {
	
	@Id
	private int id;
	
	@NotNull
	private String zoneName;
	
	@NotNull
	private String playerName;
	
	public Rank rank;
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getZoneName(){
		return this.zoneName;
	}
	
	public void setZoneName(String name){
		this.zoneName = name;
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
		
	public Rank getRank(){
		return this.rank;
	}
	
	public void setRank(Rank rank){
		this.rank = rank;
	}
}
