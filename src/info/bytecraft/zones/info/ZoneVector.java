package info.bytecraft.zones.info;

public class ZoneVector {
	  private int x;
	  private int y;
	  private int z;

	  public ZoneVector()
	  {
	    this.x = 0;
	    this.y = 0;
	    this.z = 0;
	  }

	  public ZoneVector(int x, int y, int z)
	  {
	    this.x = x;
	    this.y = y;
	    this.z = z;
	  }

	  public boolean isIn(ZoneVector min, ZoneVector max)
	  {
	    return (this.x >= min.x) && (this.x <= max.x) && (this.z >= min.z) && (this.z <= max.z);
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
}
