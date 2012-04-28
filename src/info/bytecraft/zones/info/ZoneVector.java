package info.bytecraft.zones.info;

import org.bukkit.World;

public class ZoneVector {
	  private int x;
	  private int y;
	  private int z;
	  private World world;

	  public ZoneVector()
	  {
	    this.x = 0;
	    this.y = 0;
	    this.z = 0;
	    this.world = null;
	  }

	  public ZoneVector(int x, int y, int z)
	  {
	    this.x = x;
	    this.y = y;
	    this.z = z;
	    this.world = null;
	  }
	  
	  public ZoneVector(World world, int x, int y, int z){
		  this(x, y, z);
		  this.world = world;
	  }

	  public boolean isIn(ZoneVector min, ZoneVector max)
	  {
		  if(max.world == null || max.world == null){
	    return (this.x >= min.x) && (this.x <= max.x) && (this.z >= min.z) && (this.z <= max.z);
		  }
		  /*
		   * I'm not sure if this is right, at all :3
		   */
		  return ((max.world == null) ? null: max.world == world) && ((min.world == null) ? null: min.world == world) && (this.x >= min.x) && (this.x <= max.x) && (this.z >= min.z) && (this.z <= max.z);
	  }
	  
	  public World getWorld(){
		  return this.world;
	  }

	  public void setWorld(World world){
		  this.world = world;
	  }
	  
	  public int getX()
	  {
	    return this.x;
	  }

	  public void setX(int x)
	  {
	    this.x = x;
	  }

	  public int getY()
	  {
	    return this.y;
	  }

	  public void setY(int y)
	  {
	    this.y = y;
	  }

	  public int getZ()
	  {
	    return this.z;
	  }

	  public void setZ(int z)
	  {
	    this.z = z;
	  }
	  
	  @Override
	  public boolean equals(Object obj){
		  if(obj == null)return false;
		  return (ZoneVector)obj == this;
	  }
}
