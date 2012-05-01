package info.bytecraft.zones.info;

import info.bytecraft.zones.ZoneNotFoundException;
import info.bytecraft.zones.Zones;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import com.avaje.ebean.validation.NotNull;

/**
 * This represents a <b>Lot</b>, a lot is like a smaller {@link Zone} within a {@link Zone}, that allows
 * certain players to build, even if the {@link Zone} is white-list-build.
 * @author Sabersamus <rcatron10@gmail.com>
 * @see {@link Zone}
 */
@Entity()
@Table(name="zones_lots")
public class Lot {
	
	@Id
	private int id;
	
	@NotNull
	private String zoneName;
	
	@NotNull
	private String lotName;
	
	@NotNull
	private String worldName;
	
	@NotNull
	private int x1;
	
	@NotNull
	private int x2;
	
	@NotNull
	private int y1;
	
	@NotNull
	private int y2;	
	
	@NotNull
	private int z1;
	
	@NotNull
	private int z2;
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public String getZoneName(){
		return this.zoneName;
	}
	
	public void setZoneName(String zone){
		this.zoneName = zone;
	}
	
	public String getLotName(){
		return this.lotName;
	}
	
	public void setLotName(String lot){
		this.lotName = lot;
	}
	
	public int getX1(){
		return this.x1;
	}
	
	public void setX1(int x1){
		this.x1 = x1;
	}
	
	public int getY1(){
		return this.y1;
	}
	
	public void setY1(int y1){
		this.y1 = y1;
	}
	
	public int getZ1(){
		return this.z1;
	}
	
	public void setZ1(int z1){
		this.z1 = z1;
	}
	
	public int getX2(){
		return this.x2;
	}
	
	public void setX2(int x2){
		this.x2 = x2;
	}
	
	public int getY2(){
		return this.y2;
	}
	
	public void setY2(int y2){
		this.y2 = y2;
	}
	
	public int getZ2(){
		return this.z2;
	}
	
	public void setZ2(int z2){
		this.z2 = z2;
	}
	
	public String getWorldName(){
		return this.worldName;
	}
	
	public void setWorldName(String name){
		this.worldName = name;
	}
	
	public World getWorld(){
		return Bukkit.getWorld(worldName);
	}
	
	public void setWorld(World world){
		this.worldName = world.getName();
	}
	
	public Location getBorder1(){
		return new Location(getWorld(),x1,0,z1);
	}
	
	public Location getBorder2(){
		return new Location(getWorld(),x2,0,z2);
	}
	
	public void setBorder1(Location loc){
		this.setWorld(loc.getWorld());
		this.setX1(loc.getBlockX());
		this.setZ1(loc.getBlockZ());
	}
	
	public void setBorder2(Location loc){
		this.setWorld(loc.getWorld());
		this.setX2(loc.getBlockX());
		this.setZ2(loc.getBlockZ());
	}
	
	public boolean contains(ZoneVector vector){
	    ZoneVector min = new ZoneVector(Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2));
	    ZoneVector max = new ZoneVector(Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2));
		return vector.isIn(min, max);
	}
	
	public Zone getZone() throws ZoneNotFoundException{
		Zone zone = Zones.getZone(this.zoneName);
		return ((zone == null) ? null: zone);
	}
}
