package info.bytecraft.zones;

public enum Rank {
	
	ALLOWED("allowed"),
	BANNED("banned"),
	MAKER("maker"),
	OWNER("owner");
	
	private String type;
	
	Rank(String rank){
		type = rank;
	}
	
	public String getType(){
		return type;
	}
	
	public Rank getByName(String name){
		if(this.type == name){
			return Rank.valueOf(type);
		}
		return null;
	}
	
}
