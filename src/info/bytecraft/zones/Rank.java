package info.bytecraft.zones;

import java.util.Map;

import com.google.common.collect.Maps;

public enum Rank {
	
	GUEST("guest"),
	ALLOWED("allowed"),
	BANNED("banned"),
	MAKER("maker"),
	OWNER("owner"),
	ADMIN("admin");
	
	
	private String type;
	private Map<String, Rank> BY_TYPE = Maps.newHashMap();
	
	private Rank(String rank){
		type = rank;
	}
	
	public String getType(){
		return type;
	}
	
	public Rank getByName(String name){
		if(BY_TYPE.containsKey(name)){
		return BY_TYPE.get(name);
		}
		return null;
	}
	
}
