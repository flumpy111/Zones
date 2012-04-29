package info.bytecraft.zones;

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
