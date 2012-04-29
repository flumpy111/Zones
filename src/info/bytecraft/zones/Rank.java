package info.bytecraft.zones;

/**
 * <b>Represents all the possible ranks of a zone.</b>
 * @author Sabersamus <rcatron10@gmail.com>
 * @see {@link Zone}
 */
public enum Rank {
	
	GUEST("guest"),
	ALLOWED("allowed"),
	BANNED("banned"),
	MAKER("maker"),
	OWNER("owner"),
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
			if(rank.type.equalsIgnoreCase(name)){
				return Rank.valueOf(name);
			}
		}
		return null;
	}
	
}
