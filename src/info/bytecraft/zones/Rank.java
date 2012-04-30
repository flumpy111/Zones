package info.bytecraft.zones;

/**
 * <b>Represents all the possible ranks of a zone.</b>
 * @author Sabersamus <rcatron10@gmail.com>
 * @see Zone
 */
public enum Rank {
	
	/**
	 * This Rank is not yet used.
	 */
	GUEST("guest"),
	/** This rank allows players to enter the zone even if the zone is white listed */
	ALLOWED("allowed"),
	/** This rank makes it so that the player can not enter the zone */ 
	BANNED("banned"),
	/** This rank means they can build even if the zone is white list-build */
	MAKER("maker"),
	/**
	 * This rank allows players to change settings in a zone.
	 * They have total control of the zone.
	 */
	OWNER("owner"),
	/**
	 * This rank is not yet used.
	 */
	ADMIN("admin");
	
	
	private String type;
	
	private Rank(String rank){
		type = rank;
	}
	
	public String getType(){
		return type;
	}
	
	public static Rank getByName(String name){
		for(Rank rank: Rank.values()){
			return (rank.type.equalsIgnoreCase(name) ? Rank.valueOf(name): Rank.GUEST);
		}
		return null;
	}
	
}
