package info.bytecraft.zones.info;

import java.util.List;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;

import com.avaje.ebean.validation.Length;
import com.avaje.ebean.validation.NotNull;

/**
 * This represents a <b>Zone</b>
 * A <b>Zone</b> is an area that has been selected to be protected, or an area that allows players to fight each other.
 * @author Sabersamus <rcatron10@gmail.com>
 */

@Entity()
@Table(name="bytecraft_zones")
public class Zone {

	@Id
	private int id;
	
	@Length(max=30)
	@NotNull
	private String name;
	
	@NotNull
	private String worldName;
	
	@NotNull
	private int x1;
	
	@NotNull
	private int y1;
	
	@NotNull
	private int z1;
	
	@NotNull
	private int x2;
	
	@NotNull
	private int y2;	
	
	@NotNull
	private int z2;
	
	private String enterMessage;
	
	private String exitMessage;
	
	@NotNull
	private boolean pvpAllowed;
	
	@NotNull
	private boolean hostilesAllowed;
	
	private boolean freePlace;
	
	private boolean freeBreak;
	
	private boolean whiteListed;
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	/**
	 * This gets the name of the <b>Zone</b>
	 * @return - the name of the zone.
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Sets the name of the <b>Zone</b>.
	 * @param name - the new name of the zone, should only be used if creating a new zone.
	 */
	public void setName(String name){
		this.name = name;
	}
	
	public String getWorldName(){
		return this.worldName;
	}
	
	public void setWorldName(String worldName){
		this.worldName = worldName;
	}
	
	public World getWorld(){
		return Bukkit.getWorld(worldName);
	}
	
	public void setWorld(World world){
		this.worldName = world.getName();
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
	
	public Location getBorder1(){
		return new Location(getWorld(),x1,y1,z1);
	}
	
	public void setBorder1(Location loc){
		this.setWorld(loc.getWorld());
		this.setX1(loc.getBlockX());
		this.setY1(loc.getBlockY());
		this.setZ1(loc.getBlockZ());
	}
	
	public Location getBorder2(){
		return new Location(getWorld(),x2,y2,z2);
	}
	
	public void setBorder2(Location loc){
		this.setWorld(loc.getWorld());
		this.setX2(loc.getBlockX());
		this.setY2(loc.getBlockY());
		this.setZ2(loc.getBlockZ());
	}
	
	/**
	 * This gets the enter message defined in the database
	 * @return - if no enter-message has been specified, returns <code>"Welcome to " + zone.getName();</code> Otherwise it returns
	 * the enter-message of the zone.
	 */
	public String getEnterMessage(){
		return(this.enterMessage == null ? ChatColor.RED + "Welcome to " + this.getName(): ChatColor.RED + this.enterMessage);
	}
	
	public String getExitMessage(){
		return(this.exitMessage == null ? ChatColor.RED + "You have left " + this.getName(): ChatColor.RED + this.exitMessage);
	}
	
	public void setEnterMessage(String message){
		this.enterMessage = message;
	}
	
	public void setExitMessage(String message){
		this.exitMessage = message;
	}
	
	public boolean isPvpAllowed(){
		return this.pvpAllowed;
	}
	
	public void setPvpAllowed(boolean pvp){
		this.pvpAllowed = pvp;
	}
	
	public boolean isHostilesAllowed(){
		return this.hostilesAllowed;
	}
	
	public void setHostilesAllowed(boolean hostiles){
		this.hostilesAllowed = hostiles;
	}
	
	public boolean isWhiteListed(){
		return this.whiteListed;
	}
	
	public boolean isFreePlace(){
		return this.freePlace;
	}
	
	public boolean isFreeBreak(){
		return this.freeBreak;
	}
	
	public void setWhiteListed(boolean whitelist){
		this.whiteListed = whitelist;
	}
	
	public void setFreePlace(boolean place){
		this.freePlace = place;
	}
	
	public void setFreeBreak(boolean canBreak){
		this.freeBreak = canBreak;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj == null)return false;
		return (Zone)obj == this;
	}
	
	/**
	 * This checks to see if the zone contains a {@link ZoneVector}
	 * @param vector - the vector to check
	 * @return - if the zone contains the point, returns true, else false.
	 * @see {@link ZoneVector}
	 */
	public boolean contains(ZoneVector vector)
	{
	    ZoneVector min = new ZoneVector(Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2));

	    ZoneVector max = new ZoneVector(Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2));
		return vector.isIn(min, max);
	}
	
	@Override
	public String toString(){
		Random random = new Random(1000);
		return "{Zone@"+random.nextLong()+"}";
	}
	
	/**
	 * Finds all the lots inside the zone
	 * @return - a List of all the lots of the current zone
	 */
	public List<Lot> getLots(){
		return Bukkit.getPluginManager().getPlugin("Zones").getDatabase().find(Lot.class).where().ieq("zoneName", getName()).findList();
	}
}
